package Repo;

import Entities.Ticket;

public interface ITicketRepo {
    public void saveTicket(Ticket t);
    public Ticket findById(int id);
    void update(Ticket t);
    Ticket findByLicensePlate(String plate);
}
