package com.lastdance.escape.member;

import com.lastdance.escape.member.dto.signUp.SignUpRequestDto;
import com.lastdance.escape.member.dto.signUp.SignUpResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("정상적인 회원가입이 성공적으로 처리된다")
    void signUp_success() {
        // given
        SignUpRequestDto request = new SignUpRequestDto(
                "홍길동",
                "hong1234",
                "Password1!",
                "hong@example.com"
        );

        // when
        SignUpResponseDto response = memberService.signUp(request);

        // then
        assertNotNull(response);
        assertTrue(response.getSuccess());
        assertNotNull(response.getMemberId());
        assertEquals("홍길동", response.getName());

        Member saved = memberRepository.findById(response.getMemberId())
                .orElseThrow(() -> new IllegalStateException("회원이 저장되지 않았습니다."));

        assertEquals("hong1234", saved.getLoginId());
        assertEquals("홍길동", saved.getName());
        assertEquals(MemberType.NORMAL, saved.getMemberType());
        // 비밀번호는 인코딩되어 저장되므로 raw와 같지 않아야 한다
        assertNotEquals("Password1!", saved.getPassword());
    }

    @Test
    @DisplayName("로그인 ID가 중복되면 회원가입 시 예외가 발생한다")
    void signUp_duplicateLoginId() {
        // given
        SignUpRequestDto first = new SignUpRequestDto(
                "홍길동",
                "duplicatedId",
                "Password1!",
                "hong1@example.com"
        );
        memberService.signUp(first);

        SignUpRequestDto second = new SignUpRequestDto(
                "아무개",
                "duplicatedId", // 같은 loginId
                "Password1!",
                "hong2@example.com"
        );

        // when & then
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> memberService.signUp(second));

        assertTrue(ex.getMessage().contains("이미 사용 중인 아이디"));
    }

    @Test
    @DisplayName("이메일이 중복되면 회원가입 시 예외가 발생한다")
    void signUp_duplicateEmail() {
        // given
        SignUpRequestDto first = new SignUpRequestDto(
                "홍길동",
                "loginA",
                "Password1!",
                "dup@example.com"
        );
        memberService.signUp(first);

        SignUpRequestDto second = new SignUpRequestDto(
                "아무개",
                "loginB",
                "Password1!",
                "dup@example.com"  // 같은 이메일
        );

        // when & then
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> memberService.signUp(second));

        assertTrue(ex.getMessage().contains("이미 사용 중인 email"));
    }

    @Test
    @DisplayName("비밀번호 형식이 올바르지 않으면 회원가입 시 예외가 발생한다")
    void signUp_invalidPassword() {
        // given
        SignUpRequestDto request = new SignUpRequestDto(
                "홍길동",
                "loginC",
                "abc123",  // 너무 짧고, 규칙 불만족
                "hong3@example.com"
        );

        // when & then
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> memberService.signUp(request));

        assertTrue(ex.getMessage().contains("비밀번호는 8~20자여야 합니다.")
                || ex.getMessage().contains("비밀번호는 영문, 숫자, 특수문자를 모두 포함해야 합니다."));
    }

    @Test
    @DisplayName("이메일을 입력하지 않아도 회원가입이 가능하다(null/blank 허용)")
    void signUp_withoutEmail() {
        // given
        SignUpRequestDto request = new SignUpRequestDto(
                "홍길동",
                "loginNoEmail",
                "Password1!",
                null  // 이메일 없음
        );

        // when
        SignUpResponseDto response = memberService.signUp(request);

        // then
        assertTrue(response.getSuccess());

        Member saved = memberRepository.findById(response.getMemberId())
                .orElseThrow();

        assertNull(saved.getEmail()); // Email 값 객체 자체가 null이어야 함
    }
}
