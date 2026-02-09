package lld.lldproblems.carbookingsystem.service;

import lld.lldproblems.carbookingsystem.model.Booking;
import lld.lldproblems.carbookingsystem.model.Branch;
import lld.lldproblems.carbookingsystem.model.Car.Car;
import lld.lldproblems.carbookingsystem.model.Car.CarType;
import lld.lldproblems.carbookingsystem.repository.BookingRepository;
import lld.lldproblems.carbookingsystem.repository.BranchRepository;
import lld.lldproblems.carbookingsystem.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class BookingService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Async
	protected CompletableFuture<List<Car>> findAllCarsAsync() {
		return CompletableFuture.completedFuture(carRepository.findAll());
	}
	
	@Async
	protected CompletableFuture<Optional<Branch>> findBranchByIdAsync(Long id) {
		return CompletableFuture.completedFuture(branchRepository.findById(id));
	}
	
	@Async
	protected CompletableFuture<Optional<Booking>> findBookingByIdAsync(Long id) {
		return CompletableFuture.completedFuture(bookingRepository.findById(id));
	}
	
	@Async
	protected CompletableFuture<Booking> saveBookingAsync(Booking booking) {
		return CompletableFuture.completedFuture(bookingRepository.save(booking));
	}
	
	@Async
	protected CompletableFuture<Car> saveCarAsync(Car car) {
		return CompletableFuture.completedFuture(carRepository.save(car));
	}
	
	@Async("threadPoolTaskExecutor")
	public CompletableFuture<Car> assignCar(String carType, String fromDatetime, String toDatetime) {
		return findAllCarsAsync().thenCompose(cars -> {
			List<CompletableFuture<Optional<Map.Entry<Car, Double>>>> futures = cars.stream()
					.filter(car -> Objects.equals(car.getType(), carType))
					.map(car ->
							checkAvailability(car, fromDatetime, toDatetime)
									.thenCompose((Boolean available) -> {
										if (!available) {
											return CompletableFuture.<Optional<Map.Entry<Car, Double>>>completedFuture(Optional.empty());
										}
										return findBranchByIdAsync(car.getBranchId())
												.thenApply(branch -> {
													if (branch.isEmpty()) {
														return Optional.empty();
													}
													Branch branchObj = branch.get();
													double price;
													if (CarType.SUV.toString().equalsIgnoreCase(carType)) {
														price = branchObj.getSUVPrice();
													} else if (CarType.SEDAN.toString().equalsIgnoreCase(carType)) {
														price = branchObj.getSedanPrice();
													} else if (CarType.HATCHBACK.toString().equalsIgnoreCase(carType)) {
														price = branchObj.getHatchbackPrice();
													} else {
														price = Double.MAX_VALUE;
													}
													return Optional.of(new AbstractMap.SimpleEntry<>(car, price));
												});
									})
					)
					.toList();
			if (futures.isEmpty()) {
				return CompletableFuture.completedFuture(null);
			}
			CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
			return all.thenCompose(ignored -> {
				// Find the min entry (car, price)
				Optional<Map.Entry<Car, Double>> minEntry = futures.stream()
						.map(CompletableFuture::join)
						.filter(Optional::isPresent)
						.map(Optional::get)
						.min(Comparator.comparingDouble(Map.Entry::getValue));
				
				if (minEntry.isEmpty()) {
					return CompletableFuture.completedFuture(null);
				}
				Car assignedCar = minEntry.get().getKey();
				double minPrice = minEntry.get().getValue();
				double totalPrice = minPrice * getHoursDifference(fromDatetime, toDatetime);
				
				Booking booking = new Booking(assignedCar.getId(), totalPrice, fromDatetime, toDatetime);
				
				return saveBookingAsync(booking).thenCompose(savedBooking -> {
					assignedCar.getBookingIds().add(savedBooking.getId());
					return saveCarAsync(assignedCar);
				});
			});
		});
	}
	
	@Async("threadPoolTaskExecutor")
	protected CompletableFuture<Boolean> checkAvailability(Car car, String fromDatetime, String toDatetime) {
		List<Long> bookingIds = car.getBookingIds();
		CompletableFuture<Boolean> result = CompletableFuture.completedFuture(true);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime reqFromDatetime = LocalDateTime.parse(fromDatetime, formatter);
		LocalDateTime reqToDatetime = LocalDateTime.parse(toDatetime, formatter);
		
		for (Long bookingId : bookingIds) {
			CompletableFuture<Optional<Booking>> bookingFuture = findBookingByIdAsync(bookingId);
			result = result.thenCombine(bookingFuture, (available, bookingOpt) -> {
				if (!available) return false;
				if (bookingOpt.isPresent()) {
					Booking booking = bookingOpt.get();
					LocalDateTime bookingFromDatetime = LocalDateTime.parse(booking.getStarDatetime(), formatter);
					LocalDateTime bookingToDatetime = LocalDateTime.parse(booking.getEndDatetime(), formatter);
					return (bookingToDatetime.isBefore(reqFromDatetime) || bookingFromDatetime.isAfter(reqToDatetime));
				}
				return true;
			});
		}
		return result;
	}
	
	public double getHoursDifference(String fromDatetime, String toDatetime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime from = LocalDateTime.parse(fromDatetime, formatter);
		LocalDateTime to = LocalDateTime.parse(toDatetime, formatter);
		return (Duration.between(from, to).toMinutes()/60.0);
	}
}