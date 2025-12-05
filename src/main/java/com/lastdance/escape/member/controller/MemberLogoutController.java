package com.lastdance.escape.member.controller;

import com.lastdance.escape.member.service.MemberLogoutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberLogoutController {

    private final MemberLogoutService memberLogoutService;

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        memberLogoutService.logout(request.getSession(false));
        return ResponseEntity.ok().build();
    }
}
