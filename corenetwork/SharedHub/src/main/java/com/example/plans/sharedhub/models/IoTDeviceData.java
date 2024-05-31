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

    @JsonProperty("eui")
    private String devEUI;

    private String timestamp;

    private String connection;

    @JsonProperty("last_connection_time")
    private String lastConnectionTime;

    @JsonProperty("connection_strength")
    private String connectionStrength;

    private List<SensorResult> sensors;

    private Location location;

    private Metadata metadata;

    private Security security;

    @JsonProperty("user_details")
    private UserDetails userDetails;

    private String status;

    @JsonProperty("power_source")
    private PowerSourceResult powerSource;

    @JsonProperty("firmware_version")
    private String firmwareVersion;
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