package entities;

import lombok.Data;

@Data
public class User {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
}
