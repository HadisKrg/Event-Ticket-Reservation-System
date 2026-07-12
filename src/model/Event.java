package model;

import enums.EventStatus;

import java.math.BigDecimal;

public abstract class Event extends BaseModel<Long>{
    private String title;
    private String location;
    private int capacity;
    private int reservationCount;
    private BigDecimal ticketPrice;
    private EventStatus status;

    public Event(String title, String location, int capacity, int capacityCount, BigDecimal ticketPrice, EventStatus status) {
        this.title = title;
        this.location = location;
        this.capacity = capacity;
        this.reservationCount = capacityCount;
        this.ticketPrice = ticketPrice;
        this.status = status;
    }

    public Event(Long id, String title, String location, int capacity, int capacityCount, BigDecimal ticketPrice, EventStatus status) {
        setId(id);
        this.title = title;
        this.location = location;
        this.capacity = capacity;
        this.reservationCount = capacityCount;
        this.ticketPrice = ticketPrice;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(int reservationCount) {
        this.reservationCount = reservationCount;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", capacityCount=" + reservationCount +
                ", ticketPrice=" + ticketPrice +
                ", status=" + status +
                '}';
    }
}
