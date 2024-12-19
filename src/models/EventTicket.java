package models;

public class EventTicket implements Ticket, Cloneable {
    private String eventName;
    private String seatCategory;
    private double price;

    public EventTicket(String eventName, String seatCategory, double price) {
        this.eventName = eventName;
        this.seatCategory = seatCategory;
        this.price = price;
    }

    // Métodos getters para acceder a los atributos
    public String getEventName() {
        return eventName;
    }

    public String getSeatCategory() {
        return seatCategory;
    }

    public double getPrice() {
        return price;
    }

    // Métodos setters para actualizar los atributos
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setSeatCategory(String seatCategory) {
        this.seatCategory = seatCategory;
    }

    public void setPrice(double price) {
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
        return String.format("Evento: %s, Categoría: %s, Precio: %.2f€",
                eventName, seatCategory, price);
    }
}
