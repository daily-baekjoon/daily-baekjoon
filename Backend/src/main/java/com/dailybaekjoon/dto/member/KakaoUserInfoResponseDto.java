package com.dailybaekjoon.dto.member;

import com.dailybaekjoon.entity.member.kakao.KakaoAccount;
import com.dailybaekjoon.entity.member.kakao.KakaoProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KakaoUserInfoResponseDto {
    @JsonProperty("id")
    private Long memberId;
    @JsonProperty("connected_at")
    private String connectedAt;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;
    @JsonProperty("properties")
    private KakaoProperties kakaoProperties;
}
