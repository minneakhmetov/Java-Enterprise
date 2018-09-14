package models;

import java.util.List;

public class Chat {
    private Long id;
    private Long to_id;
    private Long from_id;
    private List<Message> messages;
}
