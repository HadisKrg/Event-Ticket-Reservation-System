package model;

import enums.EventStatus;

import java.math.BigDecimal;

/*
id BIGSERIAL
title VARCHAR
location VARCHAR
capacity INTEGER
reserved_count INTEGER
ticket_price DECIMAL
status VARCHAR
 */
public abstract class Event extends BaseModel<Long>{
    private String title;
    private String location;
    private int capacity;
    private int capacityCount;
    private BigDecimal ticketPrice;
    private EventStatus status;

    public Event(String title, String location, int capacity, int capacityCount, BigDecimal ticketPrice, EventStatus status) {
        this.title = title;
        this.location = location;
        this.capacity = capacity;
        this.capacityCount = capacityCount;
        this.ticketPrice = ticketPrice;
        this.status = status;
    }

    public Event(Long id, String title, String location, int capacity, int capacityCount, BigDecimal ticketPrice, EventStatus status) {
        setId(id);
        this.title = title;
        this.location = location;
        this.capacity = capacity;
        this.capacityCount = capacityCount;
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

    public int getCapacityCount() {
        return capacityCount;
    }

    public void setCapacityCount(int capacityCount) {
        this.capacityCount = capacityCount;
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
}
