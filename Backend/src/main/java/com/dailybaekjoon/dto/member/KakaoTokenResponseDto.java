package com.dailybaekjoon.dto.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Description;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
// NOTE : 카카오 API에게 code값 넘겨주고 아래 항목 받아옴
public class KakaoTokenResponseDto {
    @Description("사용자 엑세스 토큰 값")
    @JsonProperty("access_token")
    private String accessToken;
    @Description("토큰 타입, bearer로 고정")
    @JsonProperty("token_type")
    private String tokenType;
    @Description("엑세스 토큰과 ID 토큰의 만료 시간")
    @JsonProperty("expires_in")
    private int expiresIn;
    @Description("사용자 리프레시 토큰 값")
    @JsonProperty("refresh_token")
    private String refreshToken;
    @Description("리프레시 토큰 만료시간(초)")
    @JsonProperty("refresh_token_expires_in")
    private int refreshTokenExpiresIn;

}
