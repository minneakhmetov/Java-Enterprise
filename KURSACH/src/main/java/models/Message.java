package models;

import lombok.*;

import java.sql.Time;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Message {
    private Long id;
    private Long chat_id;
    private String text;
    private Long to_id;
    private Long from_id;
    private Time date;

}

