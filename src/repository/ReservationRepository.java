package repository;

import db.DatabaseConfig;
import enums.EventStatus;
import enums.ReservationStatus;
import exception.RepositoryException;
import model.Event;
import model.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationRepository implements GenericRepository<Reservation,Long>{
    @Override
    public void save(Reservation reservation) {
        String save="insert into reservation (customer_name, customer_phone, event_id, ticket_count, reservation_date, status) values (?,?,?,?,?,?);";
        try (Connection connection= DatabaseConfig.getConnection();
             PreparedStatement statement= connection.prepareStatement(save)){
            statement.setString(1,reservation.getCustomerName());
            statement.setString(2,reservation.getCustomerPhone());
            statement.setInt(3,reservation.getEventId());
            statement.setInt(4,reservation.getTicketCount());
            statement.setDate(5, Date.valueOf( reservation.getReservationDate()));
            statement.setString(6,reservation.getStatus().name());
            int rows=statement.executeUpdate();
            if (rows > 0){
                System.out.println("Reservation added successfully");
            }
        }catch (SQLException e){
            throw new RepositoryException("Failed to save reservation",e);
        }
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        String findById="select * from reservation where id=?;";
        try ( Connection connection=DatabaseConfig.getConnection();
              PreparedStatement statement=connection.prepareStatement(findById)){
            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                Reservation reservation = new Reservation(
                        resultSet.getLong("id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_phone"),
                        resultSet.getInt("event_id"),
                        resultSet.getInt("ticket_count"),
                        resultSet.getDate("reservation_date").toLocalDate(),
                        ReservationStatus.valueOf(resultSet.getString("status"))
                );
                return Optional.of(reservation);
            }
            System.out.println("Event whit id "+ id + " not found");
        }catch (SQLException e){
            throw new RepositoryException("Failed to find event",e);
        }
        return Optional.empty();
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations=new ArrayList<>();
        String findAll="select * from reservation;";
        try (Connection connection=DatabaseConfig.getConnection();
        PreparedStatement statement=connection.prepareStatement(findAll)){
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Reservation reservation=new Reservation(
                        resultSet.getLong("id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_phone"),
                        resultSet.getInt("event_id"),
                        resultSet.getInt("ticket_count"),
                        resultSet.getDate("reservation_date").toLocalDate(),
                        ReservationStatus.valueOf(resultSet.getString("status")));
               reservations.add(reservation);
            }
        }catch (SQLException e){
            throw new RepositoryException("Failed to read reservation",e);
        }
        return reservations;
    }

    @Override
    public void update(Reservation reservation) {
        String update="update reservation set (customer_name, customer_phone, event_id, ticket_count, reservation_date, status)" +
                " = (?,?,?,?,?,?) where id=?;";
        try (Connection connection=DatabaseConfig.getConnection();
             PreparedStatement statement=connection.prepareStatement(update)){
            findById(reservation.getId());
            statement.setString(1,reservation.getCustomerName());
            statement.setString(2,reservation.getCustomerPhone());
            statement.setInt(3,reservation.getEventId());
            statement.setInt(4,reservation.getTicketCount());
            statement.setDate(5, Date.valueOf( reservation.getReservationDate()));
            statement.setString(6,reservation.getStatus().name());
            statement.setLong(7,reservation.getId());
            int rows= statement.executeUpdate();
            if (rows > 0){
                System.out.println("Reservation updated successfully");
            }
        }catch (SQLException e){
            throw new RepositoryException("Failed to update reservation",e);
        }
    }

    @Override
    public void delete(Long id) {
        String delete="delete from reservation where id=?;";
        try(Connection connection=DatabaseConfig.getConnection();
            PreparedStatement statement=connection.prepareStatement(delete)) {
            findById(id);
            statement.setLong(1,id);
            int rows=statement.executeUpdate();
            if (rows > 0){
                System.out.println("Reservation deleted successfully");
            }
        }catch (SQLException e){
            throw new RepositoryException("Failed to delete reservation",e);
        }
    }
}
