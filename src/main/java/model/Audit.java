package model;

import java.time.LocalDateTime;

public class Audit {
    private int id;
    private int user_id;
    private Actions action;
    private LocalDateTime timestamp;

    public Audit(int id, int user_id, Actions action, LocalDateTime timestamp) {
        this.id = id;
        this.user_id = user_id;
        this.action = action;
        this.timestamp = timestamp;
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

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
