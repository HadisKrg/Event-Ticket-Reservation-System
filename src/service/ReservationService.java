package service;

import model.Reservation;

public interface ReservationService {
    public void add(Reservation reservation);

    public void cancel(Reservation reservation);
}
