package service;

import enums.EventStatus;
import enums.ReservationStatus;
import exception.CapacityExceededException;
import exception.EventCancelledException;
import exception.InvalidDataException;
import model.Event;
import model.Reservation;
import repository.EventRepository;
import repository.ReservationRepository;

public class ReservationServiceImpl implements ReservationService{
    ReservationRepository reservationRepository=new ReservationRepository();
    EventRepository eventRepository=new EventRepository();

    @Override
    public void add(Reservation reservation) {
        Event event=eventRepository.findById((long) reservation.getEventId()).
                orElseThrow(()-> new EventCancelledException("Event not found"));
        if (event.getStatus() != EventStatus.ACTIVE){
            throw new InvalidDataException("Event is not active");
        }
        if ((event.getCapacity() - event.getReservationCount()) < reservation.getTicketCount()){
            throw new CapacityExceededException("Not enough capacity");
        }
        reservationRepository.save(reservation);
        event.setReservationCount(event.getReservationCount() + reservation.getTicketCount());
        eventRepository.update(event);
    }

    @Override
    public void cancel(Reservation reservation) {
        reservation.setStatus(ReservationStatus.CANCELLED);

        Event event=eventRepository.findById((long) reservation.getEventId()).
                orElseThrow(()-> new EventCancelledException("Event not found"));
        event.setReservationCount(event.getReservationCount() - reservation.getTicketCount());
        reservationRepository.update(reservation);
    }
}
