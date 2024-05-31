package com.example.plans.sharedhub.models;

import com.example.plans.sharedhub.models.deviceunit.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("devices")
public class IoTDevice {
    @Id
    @JsonProperty("eui")
    private String devEUI;

    private List<String> ownership;

    private Location location;

    @JsonProperty("operational_dates")
    private OperationalDates operationalDates;

    private List<Sensor> sensors;

    private Metadata metadata;

    private Security security;

    private Connectivity connectivity;

    @JsonProperty("power_source")
    private PowerSource powerSource;

    @JsonProperty("firmware_version")
    private String firmwareVersion;
}

