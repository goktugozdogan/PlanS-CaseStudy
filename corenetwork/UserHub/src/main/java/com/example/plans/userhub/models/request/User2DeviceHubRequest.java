package com.example.plans.userhub.models.request;

import com.example.plans.sharedhub.models.request.BaseRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User2DeviceHubRequest extends BaseRequest {
    @JsonProperty("eui")
    private String DevEUI;

    private String email;

    public User2DeviceHubRequest(String traceid, String DevEUI, String email) {
        super(traceid);
        this.DevEUI = DevEUI;
        this.email = email;
    }

}
