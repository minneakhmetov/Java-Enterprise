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
    private String first_name;
    private String last_name;
    private String username;
    private String hashPassword;
    private List<Chat> chats;
    private List<Post> posts;
    private List<Like> likes;
}

