package RepoImpl;

import java.util.*;

import Entities.ParkingSlot;
import Enums.slot_status;
import Enums.vehicle_type;
import Repo.ISlotRepo;

public class SlotRepoImpl implements ISlotRepo {

    private Map<Integer, ParkingSlot> db = new HashMap<>();

    @Override
    public void addSlot(ParkingSlot s) {
        db.put(s.getId(), s);
    }
    @Override
    public List<ParkingSlot> getAllSlots() {
        return new ArrayList<>(db.values());
    }

    @Override
    public void updateStatus(int id, slot_status status) {
        db.get(id).setStatus(status);
    }

}