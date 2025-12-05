package com.lastdance.escape.member.domain;

import com.lastdance.escape.email.Email;
import com.lastdance.escape.member.MemberType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String loginId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MemberType memberType;

    @Embedded
    private Email email;

    protected Member() {
    }

    private Member(String loginId, String name, String password, Email email, MemberType memberType) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.memberType = memberType;
    }

    public static Member createNormal(String loginId, String name,
                                      String password, Email email) {
        validate(loginId, name, password);
        return new Member(loginId, name, password, email, MemberType.NORMAL);
    }

    public static Member createGuest(String guestName) {
        String randomLoginId = "GUEST_" + System.currentTimeMillis();
        String randomPassword = "GUEST_PW_" + System.nanoTime();
        Email email = null;
        String name = (guestName == null || guestName.isBlank()) ? "게스트" : "G-" + guestName;
        validateName(name);
        return new Member(randomLoginId, name, randomPassword, email, MemberType.GUEST);
    }

    private static void validate(String loginId, String name, String password) {
        validateLoginId(loginId);
        validateName(name);
        validatePassword(password);
    }

    private static void validateLoginId(String loginId) {
        if (loginId == null || loginId.isBlank()) {
            throw new IllegalStateException("로그인 ID는 비어있을 수 없습니다.");
        }
        if (loginId.length() < 6 || loginId.length() > 20) {
            throw new IllegalStateException("로그인 ID는 6~20자여야 합니다.");
        }
        if (!loginId.matches("^[A-Za-z0-9._\\-!@#$%^&*]+$")) {
            throw new IllegalStateException("로그인 ID 형식이 올바르지 않습니다.(영문/숫자/특수문자만 가능)");
        }
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("이름은 비어있을 수 없습니다.");
        }
        if (name.isEmpty() || name.length() > 20) {
            throw new IllegalArgumentException("닉네임은 1~20자여야 합니다.");
        }
        if (!name.matches("^[A-Za-z0-9가-힣]+$")) {
            throw new IllegalArgumentException("닉네임은 한글/영문/숫자만 가능합니다.");
        }
    }

    private static void validatePassword(String encodedPassword) {
        if (encodedPassword == null || encodedPassword.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 비어 있을 수 없습니다.");
        }
        if (encodedPassword.length() > 100) {
            throw new IllegalArgumentException("비밀번호 길이가 너무 깁니다.");
        }
    }
}
