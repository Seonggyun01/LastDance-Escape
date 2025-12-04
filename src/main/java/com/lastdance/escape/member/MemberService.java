package com.lastdance.escape.member;

import com.lastdance.escape.email.Email;
import com.lastdance.escape.member.dto.signUp.SignUpRequestDto;
import com.lastdance.escape.member.dto.signUp.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        loginIdDuplicateValidate(signUpRequestDto);
        emailDuplicateValidate(signUpRequestDto);
        validateRawPassword(signUpRequestDto.getPassword());

        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());

        Email email = new Email(signUpRequestDto.getEmail());
        Member member = Member.createNormal(signUpRequestDto.getLoginId(),
                signUpRequestDto.getName(), encodedPassword, email);
        memberRepository.save(member);

        return new SignUpResponseDto(member.getId(), member.getName(), true, member.getMemberType());
    }

    private void validateRawPassword(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 비어 있을 수 없습니다.");
        }
        if (rawPassword.length() < 8 || rawPassword.length() > 20) {
            throw new IllegalArgumentException("비밀번호는 8~20자여야 합니다.");
        }
        if (!rawPassword.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=?]).+$")) {
            throw new IllegalArgumentException("비밀번호는 영문, 숫자, 특수문자를 모두 포함해야 합니다.");
        }
    }

    private void emailDuplicateValidate(SignUpRequestDto signUpRequestDto) {
        if (signUpRequestDto.getEmail() == null || signUpRequestDto.getEmail().isBlank()) {
            return;
        }
        if (memberRepository.findByEmail_Value(signUpRequestDto.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 사용 중인 email 입니다.");
        }
    }

    private void loginIdDuplicateValidate(SignUpRequestDto signUpRequestDto) {
        if (memberRepository.findByLoginId(signUpRequestDto.getLoginId()).isPresent()) {
            throw new IllegalStateException("이미 사용 중인 아이디 입니다.");
        }
    }
}
