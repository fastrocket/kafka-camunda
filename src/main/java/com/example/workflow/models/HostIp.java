package com.example.workflow.models;

import lombok.Value;

import java.net.InetAddress;

@Value
public class HostIp {
    Integer id;
    String hostname;
    InetAddress ipAddress;
}
