package models;

import java.util.Date;
import lombok.*;

@Builder
@Getter
public class Post {
    private Long id;
    private Long user_id;
    private String text;
    private Date date;
}
