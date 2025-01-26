package ParkingLot;

import ParkingLot.Gate.EntryGate;
import ParkingLot.Gate.Gate;
import ParkingLot.Level.Basement;
import ParkingLot.Level.Ground;
import ParkingLot.Level.Level;
import ParkingLot.ParkingLot.ParkingLot;
import ParkingLot.ParkingLot.ParkingLotBuilder;
import ParkingLot.Vehicle.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class ParkingLotManager {

    private static ParkingLotManager parkingLotManager;
    private ParkingLot parkingLot;
    private final ArrayList<ParkingTicket> parkingTickets;

    public ParkingLotManager() {
        parkingTickets = new ArrayList<>();
    }

    public static ParkingLotManager getInstance() {
        if(parkingLotManager == null) {
            parkingLotManager = new ParkingLotManager();
        }
        return parkingLotManager;
    }

    private void buildParkingLot() {

        ParkingLotBuilder parkingLotBuilder = new ParkingLotBuilder();

        ParkingSpot lowerBasementParkingSpot1 = new ParkingSpot("LB1", VehicleType.MEDIUM);
        ParkingSpot lowerBasementParkingSpot2 = new ParkingSpot("LB2", VehicleType.MEDIUM);
        ParkingSpot lowerBasementParkingSpot3 = new ParkingSpot("LB3", VehicleType.MEDIUM);
        ParkingSpot lowerBasementParkingSpot4 = new ParkingSpot("LB4", VehicleType.MEDIUM);
        ParkingSpot lowerBasementParkingSpot5 = new ParkingSpot("LB5", VehicleType.MEDIUM);
        Level lowerBasement = new Basement("1", 5, new ArrayList<>(Arrays.asList(lowerBasementParkingSpot1, lowerBasementParkingSpot2, lowerBasementParkingSpot3, lowerBasementParkingSpot4, lowerBasementParkingSpot5)));

        ParkingSpot basementParkingSpot1 = new ParkingSpot("B1", VehicleType.SMALL);
        ParkingSpot basementParkingSpot2 = new ParkingSpot("B2", VehicleType.SMALL);
        ParkingSpot basementParkingSpot3 = new ParkingSpot("B3", VehicleType.SMALL);
        ParkingSpot basementParkingSpot4 = new ParkingSpot("B4", VehicleType.MEDIUM);
        ParkingSpot basementParkingSpot5 = new ParkingSpot("B5", VehicleType.MEDIUM);
        Level basement = new Basement("1", 5, new ArrayList<>(Arrays.asList(basementParkingSpot1, basementParkingSpot2, basementParkingSpot3, basementParkingSpot4, basementParkingSpot5)));

        Gate entryGate1 = new EntryGate("1");
        Gate entryGate2 = new EntryGate("2");
        Gate exitGate1 = new EntryGate("3");
        Gate exitGate2 = new EntryGate("4");
        ParkingSpot groundParkingSpot1 = new ParkingSpot("B1", VehicleType.LARGE);
        ParkingSpot groundParkingSpot2 = new ParkingSpot("B2", VehicleType.LARGE);
        ParkingSpot groundParkingSpot3 = new ParkingSpot("B3", VehicleType.LARGE);
        ParkingSpot groundParkingSpot4 = new ParkingSpot("B4", VehicleType.LARGE);
        ParkingSpot groundParkingSpot5 = new ParkingSpot("B5", VehicleType.LARGE);
        Level ground = new Ground("1", 5, new ArrayList<>(Arrays.asList(groundParkingSpot1, groundParkingSpot2, groundParkingSpot3, groundParkingSpot4, groundParkingSpot5)), new ArrayList<>(Arrays.asList(entryGate1, entryGate2)), new ArrayList<>(Arrays.asList(exitGate1, exitGate2)));

        parkingLot = parkingLotBuilder.addLevel(lowerBasement).addLevel(basement).addLevel(ground).build();

        System.out.println("Parking Lot created successfully");
    }

    private ParkingSpot findParkingSpot(VehicleType vehicleType) {
        ArrayList<Level> levels = parkingLot.getLevels();
        ParkingSpot availableParkingSpot = null;
        for(Level level : levels) {
            for(ParkingSpot parkingSpot : level.getParkingSpots()) {
                if(parkingSpot.getVehicleType() == vehicleType && parkingSpot.isEmpty()) {
                    availableParkingSpot = parkingSpot;
                    break;
                }
            }
            if(availableParkingSpot != null) {
                break;
            }
        }
        return availableParkingSpot;
    }

    private synchronized String parkVehicle(Vehicle vehicle) {
        ParkingSpot availableParkingSpot = findParkingSpot(vehicle.getType());
        if (availableParkingSpot == null) {
            System.out.println("No parking spot available, please come back later!");
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket("PT-"+ UUID.randomUUID(), vehicle, System.currentTimeMillis(), availableParkingSpot);
        parkingTickets.add(parkingTicket);
        availableParkingSpot.parkVehicle(vehicle);
        System.out.println("Vehicle parked successfully");
        return parkingTicket.getTicketId();
    }

    private double calculateParkingAmount(ParkingTicket parkingTicket) {
        return parkingTicket.calculateFee();
    }

    private synchronized void unParkVehicle(String ticketId) {
        ParkingTicket parkingTicket = null;
        for(ParkingTicket ticket : parkingTickets) {
            if(ticket.getTicketId().equals(ticketId)) {
                parkingTicket = ticket;
                parkingTickets.remove(ticket);
                break;
            }
        }
        if(parkingTicket == null) {
            System.out.println("Invalid ticket id");
            return;
        }
        parkingTicket.setExitTime(System.currentTimeMillis());
        double amount = calculateParkingAmount(parkingTicket);
        parkingTicket.setAmount(amount);
        parkingTicket.setPaid(true);
        parkingTicket.getParkingSpot().unParkVehicle();
        System.out.println("Vehicle un-parked successfully");
    }

    public static void main(String[] args) {
        ParkingLotManager parkingLotManager = ParkingLotManager.getInstance();
        parkingLotManager.buildParkingLot();
        Vehicle vehicle1 = VehicleFactory.createVehicle(VehicleType.SMALL);
        Vehicle vehicle2 = VehicleFactory.createVehicle(VehicleType.MEDIUM);
        Vehicle vehicle3 = VehicleFactory.createVehicle(VehicleType.LARGE);
        String ticketId1 = parkingLotManager.parkVehicle(vehicle1);
        String ticketId2 = parkingLotManager.parkVehicle(vehicle2);
        String ticketId3 = parkingLotManager.parkVehicle(vehicle3);
        parkingLotManager.unParkVehicle(ticketId1);
        parkingLotManager.unParkVehicle(ticketId2);
        parkingLotManager.unParkVehicle(ticketId3);
    }
}
