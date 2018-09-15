package models;

import java.sql.Time;
import java.util.Date;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Post {
    private Long id;
    private Long user_id;
    private String text;
    private Time date;
}
