package com.lastdance.escape.member.dto.login;

import lombok.Getter;

@Getter
public class LoginRequestDto {

    private String loginId;
    private String password;

    public LoginRequestDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
