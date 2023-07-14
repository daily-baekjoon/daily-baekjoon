package com.dailybaekjoon.dto.member;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KakaoLoginResponseDto {
    private String name;
    private String email;
    private String bojId;
    private String nickName;
    private String accessToken;
}
