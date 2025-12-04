package com.lastdance.escape.member.dto.login;

public class LoginResponseDto {
    private boolean success;
    private Long memberId;
    private String name;

    public LoginResponseDto(boolean success, Long memberId, String name) {
        this.success = success;
        this.memberId = memberId;
        this.name = name;
    }
}
