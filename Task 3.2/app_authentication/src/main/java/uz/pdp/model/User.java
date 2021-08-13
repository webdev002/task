package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor

public class User {
    public User(String id, String firstName, String lastName, String phoneNumber, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String password;

    public User(String firstName, String lastName, String phoneNumber, String username) {
    }
}
