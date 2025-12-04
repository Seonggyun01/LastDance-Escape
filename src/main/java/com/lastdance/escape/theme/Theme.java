package com.lastdance.escape.theme;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private int difficulty; // 1~5 같은 난이도

    protected Theme() {
    }

    public Theme(String title, String description, int difficulty) {
        validate(title, description, difficulty);
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
    }

    private void validate(String title, String description, int difficulty) {
        validateTitle(title);
        validateDescription(description);
        validateDifficulty(difficulty);
    }

    private void validateDifficulty(int difficulty) {
        if (difficulty < 1 || difficulty > 5) {
            throw new IllegalArgumentException("난이도는 1~5의 범위여야 합니다.");
        }
    }

    private void validateDescription(String description) {
        if (description != null && description.length() > 500) {
            throw new IllegalArgumentException("테마 설명은 최대 500자까지 가능합니다.");
        }
        
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("테마 제목은 비어 있을 수 없습니다.");
        }
        if (title.length() > 50) {
            throw new IllegalArgumentException("테마 제목은 최대 50자까지 가능합니다.");
        }
    }
}

