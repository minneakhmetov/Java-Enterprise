package models;

import lombok.*;

import java.sql.Time;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Call {
    private Long id;
    private Long call_id;
    private int duration;
    private Long to_id;
    private Long from_id;
    private Time date;
}

