package com.lastdance.escape.member.dto.signUp;

import com.lastdance.escape.member.MemberType;
import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private Long memberId;
    private String name;
    private Boolean success;
    private MemberType memberType;

    public SignUpResponseDto(Long memberId, String name, Boolean success, MemberType memberType) {
        this.memberId = memberId;
        this.name = name;
        this.success = success;
        this.memberType = memberType;
    }
}
