package com.example.plans.userhub.models.request;

import com.example.plans.sharedhub.models.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest extends BaseRequest {
    private String email;

    private String username;
}
