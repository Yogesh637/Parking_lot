package serviceLayer;

import java.time.LocalDateTime;

import Entities.ParkingSlot;
import Entities.Ticket;
import Entities.vehicle;
import Enums.ticket_status;
import Repo.ISlotRepo;
import Repo.ITicketRepo;

public class ParkingService {

    private SlotAssignmentService slotService;
    private FeeService feeService;
    private ITicketRepo ticketRepo;
    private ISlotRepo slotRepo;

    public ParkingService(SlotAssignmentService slotService,
                          FeeService feeService,
                          ITicketRepo ticketRepo,
                          ISlotRepo slotRepo) {
        this.slotService = slotService;
        this.feeService = feeService;
        this.ticketRepo = ticketRepo;
        this.slotRepo = slotRepo;
    }

    public Ticket checkIn(vehicle v) {

        var slot = slotService.assignSlot(v);

        if (slot == null) return null;

        Ticket t = new Ticket();
        t.setVehicle(v);
        t.setSlot(slot);
        t.setEntryTime(LocalDateTime.now());
        t.setStatus(ticket_status.ACTIVE);

        ticketRepo.saveTicket(t);
        return t;
    }

    public double checkOut(int ticketId) {

        Ticket t = ticketRepo.findById(ticketId);
        if (t == null) return -1;

        t.setExitTime(LocalDateTime.now()); 

        double fee = feeService.calculateFee(t);

        t.setFee(fee);
        t.setStatus(ticket_status.PAID);

        slotService.releaseSlot(t.getSlot());
        ticketRepo.update(t);

        return fee;
    }

    public AvailabilityInfo getStatus() {

        AvailabilityInfo info = new AvailabilityInfo();

        for (ParkingSlot s : slotRepo.getAllSlots()) {

            if (s.isAvailable()) {
                int floor = s.getFloor().getFloorNumber();
                String type = s.getType().toString();

                info.add(floor, type);
            }
        }

        return info;
    }
    public double checkOutByPlate(String plate) {

        Ticket t = ticketRepo.findByLicensePlate(plate);

        if (t == null) return -1;

        t.setExitTime(LocalDateTime.now());

        double fee = feeService.calculateFee(t);

        t.setFee(fee);
        t.setStatus(ticket_status.PAID);

        slotService.releaseSlot(t.getSlot());
        ticketRepo.update(t);

        return fee;
    }
}