package com.example.plans.sharedhub.models.deviceunit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Security {
    private boolean encryption;
    private boolean authentication;

    @JsonProperty("secure_boot")
    private boolean secureBoot;

    @JsonProperty("ota_updates")
    private boolean otaUpdates;
}
