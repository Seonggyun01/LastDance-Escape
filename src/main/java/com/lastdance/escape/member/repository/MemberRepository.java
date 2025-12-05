package com.lastdance.escape.member.repository;

import com.lastdance.escape.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByName(String name);

    Optional<Member> findByEmail_Value(String value);
}
