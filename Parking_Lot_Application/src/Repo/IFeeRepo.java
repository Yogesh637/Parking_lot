package Repo;

import Enums.vehicle_type;

public interface IFeeRepo {
    double findRateByType(vehicle_type vt);
    void saveRate(vehicle_type vt, double rate);
}