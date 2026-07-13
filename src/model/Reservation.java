package model;

import enums.ReservationStatus;

import java.sql.Date;
import java.time.LocalDate;

public class Reservation extends BaseModel<Long> {
    private String customerName;
    private String customerPhone;
    private int eventId;
    private int ticketCount;
    private LocalDate reservationDate;
    private ReservationStatus status;

    public Reservation(Long id,String customerName, String getCustomerPhone, int eventId, int ticketCount, LocalDate reservationDate, ReservationStatus status) {
        setId(id);
        this.customerName = customerName;
        this.customerPhone = getCustomerPhone;
        this.eventId = eventId;
        this.ticketCount = ticketCount;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public Reservation(String customerName, String customerPhone, int eventId, int ticketCount, LocalDate reservationDate, ReservationStatus status) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.eventId = eventId;
        this.ticketCount = ticketCount;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + getId()+ '\''+
                "customerName='" + customerName + '\'' +
                ", getCustomerPhone='" + customerPhone + '\'' +
                ", eventId=" + eventId +
                ", ticketCount=" + ticketCount +
                ", reservationDate=" + reservationDate +
                ", status=" + status +
                '}';
    }
}
