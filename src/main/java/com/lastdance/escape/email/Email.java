package com.lastdance.escape.email;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Email {

    @Column(name = "email", unique = true)
    private String value;

    protected Email() {}  // JPA 기본 생성자

    public Email(String value) {
        if (value != null && !isValid(value)) {
            throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다: " + value);
        }
        this.value = value;
    }

    private boolean isValid(String value) {
        return value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}
