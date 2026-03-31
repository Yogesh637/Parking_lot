package RepoImpl;

import java.util.*;

import Enums.vehicle_type;
import Repo.IFeeRepo;

public class FeeRepoImpl implements IFeeRepo {
    Map<vehicle_type,Double> rates = new HashMap<>();
	@Override
	public double findRateByType(vehicle_type vt) {
		 return rates.getOrDefault(vt, 10.0);
	}

	@Override
	public void saveRate(vehicle_type vt, double rate) {
		 rates.put(vt, rate);		
	}

}
