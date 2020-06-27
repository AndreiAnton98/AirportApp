package dao;

import model.Flight;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDao {
    private PreparedStatement createFlightStatement;
    private PreparedStatement getFlightByUserId;

    public FlightDao(Connection connection){
        try {
            createFlightStatement = connection.prepareStatement("INSERT INTO flight VALUES (null, ?, ?, ?, ?, ?, ?, ?)");
            getFlightByUserId = connection.prepareStatement("SELECT * FROM flight WHERE user_id = ?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean createFlight(Flight flight, User user){
        try {
            createFlightStatement.setString(1, String.valueOf(user.getId()));
            createFlightStatement.setString(2, flight.getDeparture());
            createFlightStatement.setString(3, flight.getArrival());
            createFlightStatement.setString(4, flight.getDeparture_time());
            createFlightStatement.setString(5, flight.getArrival_time());
            createFlightStatement.setString(6, flight.getDays());
            createFlightStatement.setString(7, String.valueOf(flight.getPrice()));
            return createFlightStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<Flight> getFlightByUserId(User user){
        List<Flight> flights = new ArrayList<>();
        try {
            getFlightByUserId.setString(1, String.valueOf(user.getId()));
            ResultSet rs = getFlightByUserId.executeQuery();
            while(rs.next()){
                Flight flight = new Flight(rs.getInt("id"), rs.getInt("user_id"), rs.getString("departure"), rs.getString("arrival"), rs.getString("departure_time"), rs.getString("arrival_time"), rs.getString("days"), rs.getInt("price"));
                flights.add(flight);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flights;
    }
}
