package repository;

import db.DatabaseConfig;
import enums.EventStatus;
import enums.ReservationStatus;
import exception.EventNotFoundException;
import exception.RepositoryException;
import model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventRepository implements GenericRepository<Event,Long>{
    @Override
    public void save(Event event) {
        String save="insert into event (title, location, capacity, reserved_count, ticket_price, status) VALUES (?,?,?,?,?,?);";
        try (Connection connection= DatabaseConfig.getConnection();
             PreparedStatement statement= connection.prepareStatement(save)){
            statement.setString(1,event.getTitle());
            statement.setString(2,event.getLocation());
            statement.setInt(3,event.getCapacity());
            statement.setInt(4,event.getReservationCount());
            statement.setBigDecimal(5,event.getTicketPrice());
            statement.setString(6,event.getStatus().name());
            int rows=statement.executeUpdate();
            if (rows > 0){
                System.out.println("Event added successfully");
            }
        }catch (SQLException e){
            throw new RepositoryException("Failed to save event",e);
        }
    }

    @Override
    public Optional<Event> findById(Long aLong) {
        String findById="select * from event where id=?;";
        try ( Connection connection=DatabaseConfig.getConnection();
              PreparedStatement statement=connection.prepareStatement(findById)){
            statement.setLong(1,aLong);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                        Event event = new Event(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("location"),
                        resultSet.getInt("capacity"),
                        resultSet.getInt("reserved_count"),
                        resultSet.getBigDecimal("ticket_price"),
                        EventStatus.valueOf(resultSet.getString("status"))
                );
                return Optional.of(event);
            }
            System.out.println("Event whit id "+aLong + " not found");
        }catch (SQLException e){
            throw new RepositoryException("Failed to find event",e);
        }
        return Optional.empty();
    }

    @Override
    public List<Event> findAll() {
        String findAll="select * from event;";
        List<Event> events=new ArrayList<>();
        try (Connection connection=DatabaseConfig.getConnection();
             PreparedStatement statement= connection.prepareStatement(findAll)){
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Event event = new Event(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("location"),
                        resultSet.getInt("capacity"),
                        resultSet.getInt("reserved_count"),
                        resultSet.getBigDecimal("ticket_price"),
                        EventStatus.valueOf(resultSet.getString("status"))
                );
                events.add(event);
            }

        }catch (SQLException e){
            throw new RepositoryException("Failed to read event",e);
        }
        return events;
    }

    @Override
    public void update(Event event) {
        String update="update event set (title, location, capacity, reserved_count, ticket_price, status)=" +
                " (?,?,?,?,?,?) where id=?;";
        try(Connection connection=DatabaseConfig.getConnection();
            PreparedStatement statement= connection.prepareStatement(update)) {
            findById(event.getId());
            statement.setString(1,event.getTitle());
            statement.setString(2,event.getLocation());
            statement.setInt(3,event.getCapacity());
            statement.setInt(4,event.getReservationCount());
            statement.setBigDecimal(5,event.getTicketPrice());
            statement.setString(6,event.getStatus().name());
            statement.setLong(7,event.getId());
            int rows=statement.executeUpdate();
            if (rows > 0){
                System.out.println("Event updated successfully");
            }

        }catch (SQLException e){
            throw new RepositoryException("Failed to update event",e);
        }
    }

    @Override
    public void delete(Long aLong) {
        String delete="delete from event where id=?;";
        try(Connection connection=DatabaseConfig.getConnection();
        PreparedStatement statement=connection.prepareStatement(delete)) {
            findById(aLong);
            statement.setLong(1,aLong);
            int rows=statement.executeUpdate();
            if (rows > 0){
                System.out.println("Event deleted successfully");
            }
        }catch (SQLException e){
            throw new RepositoryException("Failed to delete event",e);
        }
    }
}
