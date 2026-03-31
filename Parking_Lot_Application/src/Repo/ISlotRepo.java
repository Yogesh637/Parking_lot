package Repo;

import Enums.slot_status;
import Enums.vehicle_type;
import java.util.List;
import Entities.ParkingSlot;

public interface ISlotRepo {
   // List<ParkingSlot> findFreeByType(vehicle_type vt);
    void updateStatus(int id, slot_status status);
    void addSlot(ParkingSlot s);

    List<ParkingSlot> getAllSlots();
}