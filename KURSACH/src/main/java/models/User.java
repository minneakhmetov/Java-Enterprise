package models;


import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String hashPassword;
}
