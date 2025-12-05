package com.lastdance.escape.member.service;

import com.lastdance.escape.member.domain.Member;
import com.lastdance.escape.member.repository.MemberRepository;
import com.lastdance.escape.member.dto.login.LoginRequestDto;
import com.lastdance.escape.member.dto.login.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberLoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        Member member = memberRepository.findByLoginId(loginRequestDto.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다."));
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())){
            throw new IllegalStateException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        return new LoginResponseDto(true, member.getId(), member.getName(), member.getMemberType());
    }
}
