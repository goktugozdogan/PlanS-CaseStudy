package com.example.plans.userhub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @JsonIgnore
    private String id;

    private String name;

    /*
    private boolean enablePermissionX;
    private boolean enablePermissionY;
    private boolean enablePermissionZ;
     */
}
