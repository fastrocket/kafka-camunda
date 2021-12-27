package com.example.workflow.models;

import lombok.Value;

import java.util.List;

@Value
public class DomainName {
    Integer id;
    String name;
    List<HostIp> nameServers;
    List<DnsRecord> records;
}
