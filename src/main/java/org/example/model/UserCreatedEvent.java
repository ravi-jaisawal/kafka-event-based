package org.example.model;

import lombok.Data;

@Data
public class UserCreatedEvent {

    private String userId;
    private String email;
    private String name;

    // Constructors, getters, setters
    public UserCreatedEvent(String name) {
        this.name = name;
    }

    public UserCreatedEvent(String name, String email) {
        this.email = email;
        this.name = name;
    }

    public UserCreatedEvent(String userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    // Getters and Setters

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}
