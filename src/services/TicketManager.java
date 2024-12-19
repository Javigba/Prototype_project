package services;

import models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketManager implements CloneableService {
    private final List<Ticket> tickets;

    public TicketManager() {
        this.tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void updateTicket(int index, Ticket updatedTicket) {
        if (index >= 0 && index < tickets.size()) {
            tickets.set(index, updatedTicket);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
    }

    @Override
    public Ticket cloneTicket(Ticket ticket) {
        if (ticket instanceof Cloneable) {
            return ((Ticket) ((models.EventTicket) ticket).clone());
        }
        throw new UnsupportedOperationException("El ticket no es clonable.");
    }

    public List<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }
}
