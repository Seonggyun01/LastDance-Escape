package com.lastdance.escape.member.dto.signUp;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private String name;
    private String loginId;
    private String password;
    private String email;

    protected SignUpRequestDto() {
    }

    public SignUpRequestDto(String name, String loginId, String password, String email) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
    }
}
