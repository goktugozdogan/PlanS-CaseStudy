package com.example.plans.userhub.models.response;

import com.example.plans.sharedhub.models.response.EntityResponse;
import com.example.plans.userhub.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse extends EntityResponse {
    private List<User> answer;

    public UserResponse(String traceid, User user) {
        super(traceid);
        this.answer = new ArrayList<>();
        this.answer.add(user);
    }
}
