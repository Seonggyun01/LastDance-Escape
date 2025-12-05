package com.lastdance.escape.playRecord;

import com.lastdance.escape.member.domain.Member;
import com.lastdance.escape.theme.Theme;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;

@Entity
@Getter
public class PlayRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int durationSec;

    @Column(nullable = false)
    private LocalDate playDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    @Column(nullable = false)
    private int hintCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    protected PlayRecord() {
    }

    public PlayRecord(int durationSec, LocalDate playDate, Theme theme,
                      int hintCount, Member member) {
        validate(durationSec, playDate, theme, hintCount, member);
        this.durationSec = durationSec;
        this.playDate = playDate;
        this.theme = theme;
        this.hintCount = hintCount;
        this.member = member;
    }

    private void validate(int durationSec, LocalDate playDate, Theme theme,
                          int hintCount, Member member) {
        validateDurationSec(durationSec);
        validatePlayDate(playDate);
        validateTheme(theme);
        validateHintCount(hintCount);
        validateMember(member);
    }

    private void validateMember(Member member) {
        if(member == null){
            throw new IllegalStateException("회원 정보는 반드시 필요합니다.");
        }
    }

    private void validateHintCount(int hintCount) {
        if (hintCount < 0) {
            throw new IllegalStateException("힌트 사용 개수 0이상이어야 합니다. 입력된 힌트 개수 = " + hintCount);
        }
    }

    private void validateTheme(Theme theme) {
        if(theme == null){
            throw new IllegalStateException("테마 정보는 반드시 필요합니다.");
        }
    }

    private void validatePlayDate(LocalDate playDate) {
        if(playDate == null){
            throw new IllegalStateException("진행 날짜는 비어있을 수 없습니다.");
        }
    }

    private void validateDurationSec(int durationSec) {
        if(durationSec < 0){
            throw new IllegalStateException("진행 시간은 0초 이상이어야 합니다.");
        }
    }
}
