package Entities;

import java.util.*;

public class ParkingLot {
    static int x = 1;
    int id;
    String name;
    List<Floor> floors;

    public ParkingLot(String name, List<Floor> floor){
        this.id = x++;   
        this.name = name;
        this.floors = floor;
    }

    public List<Floor> getFloors(){
        return floors;
    }
}