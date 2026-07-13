package service;

import enums.EventStatus;
import exception.CapacityExceededException;
import exception.InvalidDataException;
import model.Event;
import repository.EventRepository;

import java.math.BigDecimal;

public class EventServiceImpl implements EventService{
    EventRepository eventRepository=new EventRepository();

    public void eventValidation(Event event){
        if (event.getTitle() == null || event.getTitle().isBlank()){
            throw new InvalidDataException("Invalid data");
        }
        if (event.getCapacity() < 0){
            throw new CapacityExceededException("Capacity can not be negative");
        }
        if (event.getTicketPrice().compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidDataException("Ticket price can not be negative");
        }
    }
    @Override
    public void add(Event event) {
        eventValidation(event);
        eventRepository.save(event);
    }

    @Override
    public void update(Event event) {
        eventValidation(event);
        eventRepository.update(event);
    }

    @Override
    public void cancel(Event event) {
        event.setStatus(EventStatus.CANCELLED);
        eventRepository.delete(event.getId());
    }
}
