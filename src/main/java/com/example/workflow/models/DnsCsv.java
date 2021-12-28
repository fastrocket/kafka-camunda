package com.example.workflow.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class DnsCsv {
    private String name;
    private String status;
    private Integer whois07;
    private Integer whois15;
    private Integer whois30;
    private LocalDate expiration;
    private Boolean locked;
    private Boolean autorenew;
    private String nameserver1;
    private Integer batchErrors;
}
