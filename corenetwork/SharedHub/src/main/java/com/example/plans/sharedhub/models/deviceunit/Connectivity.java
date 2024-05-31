package com.example.plans.sharedhub.models.deviceunit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Connectivity {
    @JsonProperty("supported_connectivity")
    private List<String> supportedConnectivity;

    @JsonProperty("enabled_connectivity")
    private List<String> enabledConnectivity;
}
