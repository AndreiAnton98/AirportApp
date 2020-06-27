package model;

import java.time.LocalDateTime;

public class Audit {
    private int id;
    private int user_id;
    private String action;
    private String timestamp;

    public Audit(int id, int user_id, String action, String timestamp) {
        this.id = id;
        this.user_id = user_id;
        this.action = action;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return getAction() + " pe " + getTimestamp();
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
