package services;

import models.EventTicket;
import models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketManager implements services.CloneableService {
    private final List<Ticket> tickets;

    public TicketManager() {
        this.tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public Ticket cloneTicket(Ticket ticket) {
        if (ticket instanceof Cloneable) {
            return ((Ticket) ((EventTicket) ticket).clone());
        }
        throw new UnsupportedOperationException("El ticket no es clonable.");
    }

    public List<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }
}
