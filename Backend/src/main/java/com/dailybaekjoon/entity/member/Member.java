package com.dailybaekjoon.entity.member;
import lombok.*;
import javax.persistence.*;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {
    @SequenceGenerator(
            name = "MEMBER_SEQ_GEN",
            sequenceName = "MEMBER_SEQ",
            initialValue = 100,
            allocationSize = 1
    )

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GEN")
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name", nullable = true, unique = true)// 소셜로그인 추가되면서 기존 email nullable = true 수정
    private String name;

    @Column(name = "email", nullable = false) // 소셜로그인 추가되면서 기존 password nullable = true 수정
    private String email;

    @Column(name = "boj_id", nullable = true, length = 50, unique = true) // boj_id = null (최초 로그인시를 대비함)
    private String bojId;

    @Column(name = "nickname", nullable = true) // 소셜로그인 추가되면서 기존 password nullable = true 수정
    private String nickName;

    @Column(name = "access_token", nullable = false, length = 155, unique = true)
    private String accessToken;

    @Column(name = "refresh_token", nullable = false, length = 155, unique = true)
    private String refreshToken;

    // 소셜로그인 계정 구분값
    @Column(name = "provider_id", nullable = true)
    private String providerId;

    @Column(name = "created_at", nullable = true)
    private LocalTime createdAt;

}
