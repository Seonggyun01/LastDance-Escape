package com.lastdance.escape.member.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberLogoutService {
    public void logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }
}
