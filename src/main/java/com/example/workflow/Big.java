package com.example.workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Big {
    Integer id;
    String key;
    LocalDateTime created;
    String message;

    public Big() {
        super();
    }
}
