package service;

import enums.EventStatus;
import enums.ReservationStatus;
import model.Event;
import repository.EventRepository;
import repository.ReservationRepository;


import java.util.Comparator;

public class ReportService {
    ReservationRepository reservationRepository=new ReservationRepository();
    EventRepository eventRepository=new EventRepository();

    public void showReports() {

        System.out.println("Total active events: " +
                eventRepository.findAll().stream()
                        .filter(event -> event.getStatus() == EventStatus.ACTIVE)
                        .count());

        System.out.println("Most expensive event: " +
                eventRepository.findAll().stream()
                        .max(Comparator.comparing(Event::getTicketPrice))
                        .orElse(null));

        System.out.println("Average ticket price: " +
                eventRepository.findAll().stream()
                        .mapToDouble(event -> event.getTicketPrice().doubleValue())
                        .average()
                        .orElse(0));

        System.out.println("Active reservations:");
        reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getStatus() == ReservationStatus.ACTIVE)
                .forEach(System.out::println);

        System.out.println("Fully booked events:");
        eventRepository.findAll().stream()
                .filter(event -> event.getCapacity() == event.getReservationCount())
                .forEach(System.out::println);
    }
}
