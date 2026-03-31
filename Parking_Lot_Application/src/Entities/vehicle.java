package Entities;

import java.time.LocalDateTime;
import Enums.vehicle_type;

public class vehicle {

    static int x = 1;

    int id;
    vehicle_type type;
    String licensePlate;   
    LocalDateTime entryTime;

    public vehicle(vehicle_type t, String licensePlate) {
        this.id = x++;
        this.type = t;
        this.licensePlate = licensePlate;
        this.entryTime = LocalDateTime.now();
    }

    public vehicle_type getType() {
        return this.type;
    }

    public String getLicensePlate() { 
        return licensePlate;
    }
}