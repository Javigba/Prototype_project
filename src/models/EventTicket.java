package models;

public class EventTicket implements models.Ticket, Cloneable {
    private String eventName;
    private String seatCategory;
    private double price;

    public EventTicket(String eventName, String seatCategory, double price) {
        this.eventName = eventName;
        this.seatCategory = seatCategory;
        this.price = price;
    }

    @Override
    public EventTicket clone() {
        try {
            return (EventTicket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clonación no soportada", e);
        }
    }

    @Override
    public String getDetails() {
        return String.format("Evento: %s, Categoría: %s, Precio: $%.2f",
                eventName, seatCategory, price);
    }
}