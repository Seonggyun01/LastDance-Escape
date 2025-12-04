package com.lastdance.escape.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    @DisplayName("정상적인 이메일이면 객체가 생성된다")
    void validEmail() {
        // given
        String emailValue = "test@example.com";

        // when
        Email email = new Email(emailValue);

        // then
        assertEquals(emailValue, email.getValue());
    }

    @Test
    @DisplayName("null 이메일이면 예외 없이 생성된다 (선택 입력 가능)")
    void nullEmailIsAllowed() {
        // when & then
        assertDoesNotThrow(() -> new Email(null));
    }

    @Test
    @DisplayName("잘못된 이메일 형식이면 예외 발생")
    void invalidEmail() {
        // given
        String invalidEmail = "wrong-email-format";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    @DisplayName("빈 문자열 이메일도 잘못된 형식으로 판단하여 예외 발생")
    void blankEmailIsInvalid() {
        // given
        String invalidEmail = "";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }
}
