package com.example.plans.sharedhub.models;

import com.example.plans.sharedhub.models.deviceunit.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document("transactions")
@NoArgsConstructor
@AllArgsConstructor
public class IoTDeviceData {
    @Id
    private String id;

    private String eui;

    private String timestamp;

    private String connection;

    private String last_connection_time;

    private String connectionStrength;

    private List<SensorResult> sensors;

    private Location location;

    private Metadata metadata;

    private Security security;

    private UserDetails user_details;

    private String status;

    private PowerSourceResult power_source;

    private String firmware_version;
}

@Data
class UserDetails {
    private String username;
    private String email;
}

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
class SensorResult extends Sensor {
    private double value;
}

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
class PowerSourceResult extends PowerSource {
    @JsonProperty("battery_level")
    private String batteryLevel;
}