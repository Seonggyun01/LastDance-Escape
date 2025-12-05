package com.lastdance.escape.member.controller;

import com.lastdance.escape.member.service.MemberSignUpService;
import com.lastdance.escape.member.dto.signUp.SignUpRequestDto;
import com.lastdance.escape.member.dto.signUp.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberSignUpController {

    private final MemberSignUpService memberService;

    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        SignUpResponseDto signUpResponseDto= memberService.signUp(signUpRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponseDto);
    }
}
