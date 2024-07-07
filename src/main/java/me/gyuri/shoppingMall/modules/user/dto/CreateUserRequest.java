package me.gyuri.shoppingMall.modules.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String email;
    private String nickname;
    private String password;
}
