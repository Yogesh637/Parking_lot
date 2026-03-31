package serviceLayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import Entities.ParkingSlot;
import Entities.vehicle;
import Enums.slot_status;
import Enums.slot_type;
import Enums.vehicle_type;
import Repo.ISlotRepo;

public class SlotAssignmentService {

    private ISlotRepo slotRepo;
    private ReentrantLock lock = new ReentrantLock();

    public SlotAssignmentService(ISlotRepo repo) {
        this.slotRepo = repo;
    }

    public ParkingSlot assignSlot(vehicle v) {
        ParkingSlot slot = findNearest(v.getType());

        if (slot == null) return null;

        boolean assigned = lockAndAssign(slot, v);
        return assigned ? slot : null;
    }

    public void releaseSlot(ParkingSlot s) {
        lock.lock();
        try {
            s.setStatus(slot_status.AVAILABLE);
            s.setVehicle(null);
        } finally {
            lock.unlock();
        }
    }


    private ParkingSlot findNearest(vehicle_type vType) {

        List<ParkingSlot> allSlots = slotRepo.getAllSlots();
        List<ParkingSlot> validSlots = new ArrayList<>();

        for (ParkingSlot s : allSlots) {
            if (s.isAvailable() && canFit(vType, s.getType())) {
                validSlots.add(s);
            }
        }

        if (validSlots.isEmpty()) return null;

        validSlots.sort(Comparator
                .comparingInt((ParkingSlot s) -> s.getFloor().getFloorNumber())
                .thenComparingInt(ParkingSlot::getSlotNumber)
        );

        return validSlots.get(0);
    }
    private boolean canFit(vehicle_type vType, slot_type sType) {

        switch (vType) {
            case TWO_WHEELER:
                return true;

            case FOUR_WHEELER:
                return sType == slot_type.MEDIUM || sType == slot_type.LARGE;

            case HEAVY:
                return sType == slot_type.LARGE;

            default:
                return false;
        }
    }

    private boolean lockAndAssign(ParkingSlot s, vehicle v) {
        lock.lock();
        try {
            if (!s.isAvailable()) return false;

            s.setStatus(slot_status.OCCUPIED);
            s.setVehicle(v);
            return true;
        } finally {
            lock.unlock();
        }
    }
}