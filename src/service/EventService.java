package service;

import model.Event;

import java.util.List;

public interface EventService {
    void add(Event event);

    void update(Event event);

    void cancel(Event event);
}
