package services;

import models.Ticket;

public interface CloneableService {
    Ticket cloneTicket(Ticket ticket);
}
