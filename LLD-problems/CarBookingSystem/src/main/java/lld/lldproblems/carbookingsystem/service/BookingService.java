package interview.lld.carbookingsystem.service;

import interview.lld.carbookingsystem.model.Booking;
import interview.lld.carbookingsystem.model.Branch;
import interview.lld.carbookingsystem.model.Car.Car;
import interview.lld.carbookingsystem.model.Car.CarType;
import interview.lld.carbookingsystem.repository.BookingRepository;
import interview.lld.carbookingsystem.repository.BranchRepository;
import interview.lld.carbookingsystem.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BookingService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	public Car assignCar(String carType, String fromDatetime, String toDatetime) {
		List<Car> cars = carRepository.findAll();
		Double minPrice = Double.MAX_VALUE;
		Car assignedCar = null;
		for(Car car: cars) {
			if(car.getType().toString() != carType || !checkAvailability(car, fromDatetime, toDatetime)) {
				continue;
			}
			Long branchId = car.getBranchId();
			Branch branch = branchRepository.findById(branchId).orElse(null);
			if (branch == null) {
				throw new IllegalArgumentException("Branch not found");
			}
			Double carPrice;
			if (carType.equals(CarType.SUV.toString())) {
				carPrice = branch.getSUVPrice();
			} else if (carType.equals(CarType.SEDAN.toString())) {
				carPrice = branch.getSedanPrice();
			} else if (carType.equals(CarType.HATCHBACK.toString())) {
				carPrice = branch.getHatchbackPrice();
			} else {
				throw new IllegalArgumentException("Invalid car type");
			}
			if (carPrice < minPrice) {
				minPrice = carPrice;
				assignedCar = car;
			} else  if (minPrice.equals(carPrice)) {
				if (assignedCar != null && car.getId() < assignedCar.getId()) {
					assignedCar = car;
				}
			}
		}
		if (assignedCar == null) {
			return null;
		}
		Booking booking = bookingRepository.save(new Booking(assignedCar.getId(), minPrice*getHoursDifference(fromDatetime, toDatetime), fromDatetime, toDatetime));
		assignedCar.getBookingIds().add(booking.getId());
		carRepository.save(assignedCar);
		return assignedCar;
	}
	
	private Boolean checkAvailability(Car car, String fromDatetime, String toDatetime) {
		List<Long> bookingIds = car.getBookingIds();
		for(Long bookingId: bookingIds) {
			Booking booking = bookingRepository.findById(bookingId).orElse(null);
			if (booking == null) {
				System.out.println("Booking not found for id: " + bookingId);
				continue;
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime bookingFromDatetime = LocalDateTime.parse(booking.getStarDatetime(), formatter);
			LocalDateTime bookingToDatetime = LocalDateTime.parse(booking.getEndDatetime(), formatter);
			LocalDateTime reqFromDatetime = LocalDateTime.parse(fromDatetime, formatter);
			LocalDateTime reqToDatetime = LocalDateTime.parse(toDatetime, formatter);
			long bookingFromMillis = bookingFromDatetime.toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
			long reqFromMillis = reqFromDatetime.toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
			long bookingToMillis = bookingToDatetime.toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
			long reqToMillis = reqToDatetime.toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
			if (bookingFromMillis >= reqFromMillis && bookingFromMillis <= reqToMillis) {
				return false;
			} else if (bookingToMillis >= reqFromMillis && bookingToMillis >= reqToMillis) {
				return false;
			} else if (bookingFromMillis <= reqFromMillis && bookingToMillis >= reqToMillis) {
				return false;
			}
		}
		return true;
	}
	
	public double getHoursDifference(String fromDatetime, String toDatetime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime from = LocalDateTime.parse(fromDatetime, formatter);
		LocalDateTime to = LocalDateTime.parse(toDatetime, formatter);
		return (Duration.between(from, to).toMinutes()/60.0);
	}
}
