package models;

import lombok.*;

import java.sql.Time;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Like {
    private Long id;
    private Long from_id;
    private Long target_id;
    private Time date;
}
