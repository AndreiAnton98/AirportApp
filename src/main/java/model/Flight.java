package model;

import java.time.LocalTime;
import java.util.List;

public class Flight {
    private int id;
    private int user_id;
    private String departure;
    private String arrival;
    private String departure_time;
    private String arrival_time;
    private String days;
    private int price;

    public Flight(int id, int user_id, String departure, String arrival, String departure_time, String arrival_time, String days, int price) {
        this.id = id;
        this.user_id = user_id;
        this.departure = departure;
        this.arrival = arrival;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.days = days;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
