package com.lastdance.escape.member.dto.login;

import com.lastdance.escape.member.MemberType;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private boolean success;
    private Long memberId;
    private String name;
    private MemberType memberType;

    public LoginResponseDto(boolean success, Long memberId, String name, MemberType memberType) {
        this.success = success;
        this.memberId = memberId;
        this.name = name;
        this.memberType = memberType;
    }
}
