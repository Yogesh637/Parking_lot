package RepoImpl;

import java.util.HashMap;
import java.util.Map;

import Entities.Ticket;
import Repo.ITicketRepo;

public class TicketRepoImpl implements ITicketRepo{
	 private Map<Integer, Ticket> db = new HashMap<>();

	    @Override
	    public void saveTicket(Ticket t) {
	        db.put(t.getId(), t);
	    }

	    @Override
	    public Ticket findById(int id) {
	        return db.get(id);
	    }

	    @Override
	    public void update(Ticket t) {
	        db.put(t.getId(), t);
	    }
	    @Override
	    public Ticket findByLicensePlate(String plate) {

	        for (Ticket t : db.values()) {
	            if (t.getVehicle().getLicensePlate().equalsIgnoreCase(plate)
	                    && t.getExitTime() == null) {   // active ticket
	                return t;
	            }
	        }

	        return null;
	    }

}
