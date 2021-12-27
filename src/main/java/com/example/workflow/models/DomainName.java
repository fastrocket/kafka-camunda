package com.example.workflow.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainName {
    Integer id;
    String name;
    List<HostIp> nameServers;
    List<DnsRecord> records;
}
