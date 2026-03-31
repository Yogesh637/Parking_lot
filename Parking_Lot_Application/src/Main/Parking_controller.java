package Main;

import Enums.*;
import Repo.*;
import RepoImpl.*;
import Entities.*;
import serviceLayer.*;
import java.util.*;

public class Parking_controller {

    public void start() {

        Scanner sc = new Scanner(System.in);

        ISlotRepo slotRepo = new SlotRepoImpl();
        ITicketRepo ticketRepo = new TicketRepoImpl();
        IFeeRepo feeRepo = new FeeRepoImpl();

        feeRepo.saveRate(vehicle_type.TWO_WHEELER, 10);
        feeRepo.saveRate(vehicle_type.FOUR_WHEELER, 20);
        feeRepo.saveRate(vehicle_type.HEAVY, 50);

        System.out.print("Enter number of floors: ");
        int f = sc.nextInt();

        List<Floor> floors = new ArrayList<>();

        for (int i = 1; i <= f; i++) {

            System.out.print("Enter width & height for floor " + i + ": ");
            int w = sc.nextInt();
            int h = sc.nextInt();

            Floor floor = new Floor(i, new ArrayList<>());

            int total = w * h;
            int id = 1;

            int small = 0, medium = 0, large = 0;

            for (int j = 0; j < total; j++) {

                ParkingSlot slot;

                if (j % 3 == 0) {
                    slot = new ParkingSlot(slot_type.SMALL, slot_status.AVAILABLE);
                    small++;
                } else if (j % 3 == 1) {
                    slot = new ParkingSlot(slot_type.MEDIUM, slot_status.AVAILABLE);
                    medium++;
                } else {
                    slot = new ParkingSlot(slot_type.LARGE, slot_status.AVAILABLE);
                    large++;
                }

                slot.setSlotNumber(id++);
                slot.setFloor(floor);

                floor.addSlot(slot);
                slotRepo.addSlot(slot);
            }

            System.out.println("Added Floor " + i + " with " + total +
                    " slots (SMALL=" + small +
                    ", MEDIUM=" + medium +
                    ", LARGE=" + large + ")");

            floors.add(floor);
        }

        ParkingLot lot = new ParkingLot("MyLot", floors);

        SlotAssignmentService slotService = new SlotAssignmentService(slotRepo);
        FeeService feeService = new FeeService(feeRepo);

        ParkingService parkingService =
                new ParkingService(slotService, feeService, ticketRepo, slotRepo);

        while (true) {

            System.out.println("\n===== PARKING SYSTEM =====");
            System.out.println("1. Check In Vehicle");
            System.out.println("2. Check Out Vehicle");
            System.out.println("3. View Status");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("Select Vehicle Type:");
                    System.out.println("1. TWO_WHEELER");
                    System.out.println("2. FOUR_WHEELER");
                    System.out.println("3. HEAVY");

                    int typeChoice = sc.nextInt();

                    vehicle_type type = null;

                    if (typeChoice == 1) type = vehicle_type.TWO_WHEELER;
                    else if (typeChoice == 2) type = vehicle_type.FOUR_WHEELER;
                    else if (typeChoice == 3) type = vehicle_type.HEAVY;
                    else {
                        System.out.println("Invalid type!");
                        break;
                    }

                    System.out.print("Enter License Plate: ");
                    String plate = sc.next();

                    vehicle v = new vehicle(type, plate);

                    Ticket t = parkingService.checkIn(v);

                    if (t == null) {
                        System.out.println("No slots available!");
                    } else {
                        ParkingSlot slot = t.getSlot();
                        System.out.println("Parked Successfully!");
                        System.out.println("Ticket ID: " + t.getId());
                        System.out.println("License Plate: " + v.getLicensePlate());
                        System.out.println("Floor: " + slot.getFloor().getFloorNumber());
                        System.out.println("Slot Number: " + slot.getSlotNumber());
                        System.out.println("Slot Type: " + slot.getType());
                    }

                    break;

                case 2:

                    System.out.print("Enter License Plate: ");
                    String plate1 = sc.next();

                    double fee = parkingService.checkOutByPlate(plate1);

                    if (fee == -1) {
                        System.out.println("Vehicle not found!");
                    } else {
                        System.out.println("Vehicle Exited");
                        System.out.println("Fee: " + fee);
                    }

                    break;

                case 3:

                    System.out.println("Current Status:");
                    System.out.println(parkingService.getStatus());
                    break;

                case 4:

                    System.out.println("Exiting system...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}