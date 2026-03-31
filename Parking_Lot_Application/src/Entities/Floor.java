package Entities;

import java.util.*;

public class Floor {
	static int x = 1;
    int id;
     private int floorNumber;
    private List<ParkingSlot> slots;
    public Floor(int n, List<ParkingSlot> l){
    	this.id = x++;
    	this.setFloorNumber(n);
    	this.setSlots(l);
    }
    
    public List<ParkingSlot> getAvailableSlots(){
    	 List<ParkingSlot> list = new ArrayList<>();
    	for(ParkingSlot slot : getSlots()) {
    		if(slot.isAvailable()) {
    			list.add(slot);
    		}
    	}
    	return list;
    }
    
    public void addSlot(ParkingSlot s) {
        s.setFloor(this);   
        getSlots().add(s);
    }

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public List<ParkingSlot> getSlots() {
		return slots;
	}

	public void setSlots(List<ParkingSlot> slots) {
		this.slots = slots;
	}
}
