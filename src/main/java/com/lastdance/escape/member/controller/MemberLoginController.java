package com.lastdance.escape.member.controller;

import com.lastdance.escape.member.dto.login.LoginRequestDto;
import com.lastdance.escape.member.dto.login.LoginResponseDto;
import com.lastdance.escape.member.dto.session.SessionMemberDto;
import com.lastdance.escape.member.service.MemberLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberLoginController {
    private final MemberLoginService memberLoginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto,
                                                  HttpServletRequest request) {
        LoginResponseDto loginResponseDto = memberLoginService.login(loginRequestDto);

        HttpSession session = request.getSession(true);

        SessionMemberDto sessionMemberDto = new SessionMemberDto(
                loginResponseDto.getMemberId(),
                loginResponseDto.getName(),
                loginResponseDto.getMemberType()
        );

        session.setAttribute("LOGIN_MEMBER",sessionMemberDto);

        return ResponseEntity.ok(loginResponseDto);
    }
}
