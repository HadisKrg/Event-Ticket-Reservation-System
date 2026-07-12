package model;

import enums.ReservationStatus;

import java.util.Date;

public class Reservation extends BaseModel<Long> {
    private String customerName;
    private String getCustomerPhone;
    private int eventId;
    private int ticketCount;
    private Date reservationDate;
    private ReservationStatus status;

    public Reservation(Long id,String customerName, String getCustomerPhone, int eventId, int ticketCount, Date reservationDate, ReservationStatus status) {
        setId(id);
        this.customerName = customerName;
        this.getCustomerPhone = getCustomerPhone;
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

    public String getGetCustomerPhone() {
        return getCustomerPhone;
    }

    public void setGetCustomerPhone(String getCustomerPhone) {
        this.getCustomerPhone = getCustomerPhone;
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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
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
                "customerName='" + customerName + '\'' +
                ", getCustomerPhone='" + getCustomerPhone + '\'' +
                ", eventId=" + eventId +
                ", ticketCount=" + ticketCount +
                ", reservationDate=" + reservationDate +
                ", status=" + status +
                '}';
    }
}
