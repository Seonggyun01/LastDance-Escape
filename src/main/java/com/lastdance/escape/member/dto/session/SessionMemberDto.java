package com.lastdance.escape.member.dto.session;

import com.lastdance.escape.member.MemberType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionMemberDto {
    private Long memberId;
    private String name;
    private MemberType memberType;
}
