package Entities;

import java.time.LocalDateTime;
import Enums.ticket_status;

public class Ticket {
    static int x = 1;

    private int id;
    vehicle vehicle;
    ParkingSlot slot;
    LocalDateTime entry_time;
    LocalDateTime exit_time;
    double fee;
    ticket_status status;

    public Ticket() {
        this.id = x++;
    }

    public int getId() {
        return id;
    }

    public vehicle getVehicle() {
        return this.vehicle;
    }

    public ParkingSlot getSlot() {   
        return this.slot;
    }

    public LocalDateTime getEntryTime() {
        return this.entry_time;
    }

    public LocalDateTime getExitTime() {
        return this.exit_time;
    }

    public void setVehicle(vehicle v) {
        this.vehicle = v;
    }

    public void setSlot(ParkingSlot slot2) {
        this.slot = slot2;
    }

    public void setEntryTime(LocalDateTime now) {
        this.entry_time = now;
    }

    public void setStatus(ticket_status status) {
        this.status = status;
    }

    public void setExitTime(LocalDateTime now) {
        this.exit_time = now;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
    public String toString() {
        return "Ticket ID: " + id +
               ", Plate: " + vehicle.getLicensePlate() +
               ", Status: " + status +
               ", Fee: " + fee;
    }
}