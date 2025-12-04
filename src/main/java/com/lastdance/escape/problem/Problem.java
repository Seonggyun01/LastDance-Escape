package com.lastdance.escape.problem;

import com.lastdance.escape.theme.Theme;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Problem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    @Column(nullable = false)
    private int orderNo;

    @Column(nullable = false, length = 50)
    private String answer;

    @Column(nullable = false, length = 500)
    private String hintText;

    protected Problem() {}

    public Problem(Theme theme,int orderNo, String answer, String hintText) {
        validate(theme, orderNo, answer, hintText);
        this.theme = theme;
        this.orderNo = orderNo;
        this.answer = answer;
        this.hintText = hintText;
    }

    private static void validate(Theme theme, int orderNo, String answer, String hintText){
        validateTheme(theme);
        validateOrderNo(orderNo);
        validateAnswer(answer);
        validateHintText(hintText);
    }

    private static void validateHintText(String hintText) {
        if (hintText == null || hintText.isBlank()) {
            throw new IllegalArgumentException("힌트는 비어 있을 수 없습니다.");
        }
        if (hintText.length() > 500) {
            throw new IllegalArgumentException("힌트는 최대 500자까지 가능합니다.");
        }
    }

    private static void validateAnswer(String answer) {
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("문제 정답은 비어 있을 수 없습니다.");
        }
        if (answer.length() > 50) {
            throw new IllegalArgumentException("정답은 최대 50자까지 가능합니다.");
        }
    }

    private static void validateTheme(Theme theme) {
        if(theme == null){
            throw new IllegalStateException("문제는 반드시 특정 테마에 속해야 합니다.");
        }
    }

    private static void validateOrderNo(int orderNo) {
        if (orderNo < 1) {
            throw new IllegalArgumentException("문제 순번(orderNo)은 1 이상이어야 합니다.");
        }
    }
}
