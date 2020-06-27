package controller;

import dao.FlightDao;
import dao.UserDao;
import model.Flight;
import model.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class FlightController {
    private FlightDao flightDao;
    private static FlightController instance;
    
    private FlightController(){
        Connection connection = DatabaseConnection.getInstance();
        flightDao = new FlightDao(connection);
    }

    public static FlightController getInstance(){
        if(instance == null){
            instance = new FlightController();
        }
        return instance;
    }
    
    public boolean addFlight(Flight flight, User user){
        return flightDao.createFlight(flight, user);
    }
    
    public List<Flight> getFlights(User user){
        return flightDao.getFlightByUserId(user);
    }
    
    public List<String> getFlightsDepartureArrival(User user){
        List<String> list = new ArrayList<>();
        List<Flight> flights = flightDao.getFlightByUserId(user);
        for(int i=0; i < flights.size(); i++){
            Flight flight = flights.get(i);
            String departure = flight.getDeparture();
            String arrival = flight.getArrival();
            list.add(departure + arrival);
        }
        return list;
    }
}
