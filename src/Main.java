import enums.EventStatus;
import enums.ReservationStatus;
import model.Event;
import model.Reservation;
import repository.EventRepository;
import repository.ReservationRepository;
import service.EventServiceImpl;
import service.ReportService;
import service.ReservationServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportService reportService = new ReportService();
        EventRepository eventRepository = new EventRepository();
        ReservationRepository reservationRepository = new ReservationRepository();
        EventServiceImpl eventService = new EventServiceImpl();
        ReservationServiceImpl reservationService = new ReservationServiceImpl();
        while (true) {
            System.out.println("1. Create Event\n" + "2. Show All Events\n" + "3. Update Event\n" + "4. Cancel Event\n" + "5. Create Reservation\n" + "6. Cancel Reservation\n" + "7. Show All Reservations\n" + "8. Reports\n" + "9. Exit \n");
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1: {
                    try {
                        System.out.println("Event title:");
                        String title = scanner.nextLine();
                        System.out.println("Event location:");
                        String location = scanner.nextLine();
                        System.out.println("Event capacity:");
                        int capacity = scanner.nextInt();
                        System.out.println("Event reservation count:");
                        int reservationCount = scanner.nextInt();
                        System.out.println("Event ticket price:");
                        BigDecimal ticketPrice = scanner.nextBigDecimal();
                        scanner.nextLine();
                        System.out.println("Event status:");
                        EventStatus status = EventStatus.valueOf(scanner.nextLine().toUpperCase());
                        Event event = new Event(title, location, capacity, reservationCount, ticketPrice, status);
                        eventService.add(event);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 2: {
                    try {
                        System.out.println(eventRepository.findAll());
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3: {
                    try {
                        System.out.println("Event Id:");
                        Long id = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("Event title:");
                        String title = scanner.nextLine();
                        System.out.println("Event location:");
                        String location = scanner.nextLine();
                        System.out.println("Event capacity:");
                        int capacity = scanner.nextInt();
                        System.out.println("Event reservation count:");
                        int reservationCount = scanner.nextInt();
                        System.out.println("Event ticket price:");
                        BigDecimal ticketPrice = scanner.nextBigDecimal();
                        scanner.nextLine();
                        System.out.println("Event status:");
                        EventStatus status = EventStatus.valueOf(scanner.nextLine().toUpperCase());
                        Event event = new Event(id, title, location, capacity, reservationCount, ticketPrice, status);
                        eventService.update(event);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    try {
                        System.out.println("Event Id:");
                        Long id = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("Event title:");
                        String title = scanner.nextLine();
                        System.out.println("Event location:");
                        String location = scanner.nextLine();
                        System.out.println("Event capacity:");
                        int capacity = scanner.nextInt();
                        System.out.println("Event reservation count:");
                        int reservationCount = scanner.nextInt();
                        System.out.println("Event ticket price:");
                        BigDecimal ticketPrice = scanner.nextBigDecimal();
                        scanner.nextLine();
                        System.out.println("Event status:");
                        EventStatus status = EventStatus.valueOf(scanner.nextLine().toUpperCase());
                        Event event = new Event(id, title, location, capacity, reservationCount, ticketPrice, status);
                        eventService.cancel(event);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 5: {
                    try {
                        System.out.println("Customer name:");
                        String customerName = scanner.nextLine();
                        System.out.println("Customer phone:");
                        String customerPhone = scanner.nextLine();
                        System.out.println("Event id:");
                        int eventId = scanner.nextInt();
                        System.out.println("Event count:");
                        int eventCount = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Reservation date:");
                        LocalDate reservationDate = LocalDate.parse(scanner.nextLine());
                        System.out.println("Reservation status:");
                        ReservationStatus status = ReservationStatus.valueOf(scanner.nextLine().toUpperCase());
                        Reservation reservation = new Reservation(customerName, customerPhone, eventId, eventCount, reservationDate, status);
                        reservationService.add(reservation);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 6: {
                    try {
                        System.out.println("Reservation Id:");
                        Long id = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("Customer name:");
                        String customerName = scanner.nextLine();
                        System.out.println("Customer phone:");
                        String customerPhone = scanner.nextLine();
                        System.out.println("Event id:");
                        int eventId = scanner.nextInt();
                        System.out.println("Event count:");
                        int eventCount = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Reservation date:");
                        LocalDate reservationDate = LocalDate.parse(scanner.nextLine());
                        System.out.println("Reservation status:");
                        ReservationStatus status = ReservationStatus.valueOf(scanner.nextLine().toUpperCase());
                        Reservation reservation = new Reservation(id, customerName, customerPhone, eventId, eventCount, reservationDate, status);
                        reservationService.cancel(reservation);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 7:
                    try {
                        System.out.println(reservationRepository.findAll());
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 8:
                    try {
                        reportService.showReports();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 9:
                    System.out.println("Exit");
                    return;

                default:
                    System.out.println("Invalid input");
            }
        }
    }
}