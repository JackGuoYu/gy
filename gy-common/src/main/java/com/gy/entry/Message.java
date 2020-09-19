package com.gy.entry;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private Long id;

    private Object msg;

    private LocalDateTime sendTime;
}
