package com.dailybaekjoon.repository.member;

import com.dailybaekjoon.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByMemberId(Long memberId);
    Optional<Member> findByEmail(String email);
    boolean existsByMemberId(Long memberId);
    boolean existsByNickName(String nickName);
    boolean existsByEmail(String email);
}
