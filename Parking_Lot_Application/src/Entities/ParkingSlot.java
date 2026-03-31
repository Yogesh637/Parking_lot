package Entities;

import Enums.slot_status;
import Enums.slot_type;
import Enums.vehicle_type;

public class ParkingSlot {

    static int x = 1, no = 1;

    private int id;
    int slotNumber;
    private slot_status status;
    vehicle vehicle;
    private slot_type type;

    Floor floor;  
    public ParkingSlot(slot_type t, slot_status s) {
        this.id = x++;
        this.slotNumber = no++;
        this.type = t;
        this.status = s;
    }

    public boolean isAvailable() {
        return status == slot_status.AVAILABLE;
    }

    public int getId() {
        return id;
    }

    public slot_status getStatus() {
        return status;
    }

    public void setStatus(slot_status status) {
        this.status = status;
    }

    public slot_type getType() {
        return type;
    }

    public void setVehicle(vehicle v) {
        this.vehicle = v;
    }

    public void setFloor(Floor f) {
        this.floor = f;
    }

    public Floor getFloor() {
        return this.floor;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

	public void setSlotNumber(int i) {
		this.slotNumber = i;
		
	}
}