package serviceLayer;

import java.time.Duration;

import Entities.Ticket;
import Enums.vehicle_type;
import Repo.IFeeRepo;

public class FeeService {

    private IFeeRepo feeRepo;

    public FeeService(IFeeRepo repo) {
        this.feeRepo = repo;
    }

    public double calculateFee(Ticket t) {
        long minutes = duration(t);
        int hours = roundUpHour(minutes);

        double rate = getRate(t.getVehicle().getType());
        return hours * rate;
    }

    public double getRate(vehicle_type vt) {
        return feeRepo.findRateByType(vt);
    }
    
    private long duration(Ticket t) {
        return Duration.between(t.getEntryTime(), t.getExitTime()).toMinutes();
    }

    private int roundUpHour(long min) {
        return (int) Math.ceil(min / 60.0);
    }
}