package com.dailybaekjoon.dto.member;

import com.dailybaekjoon.entity.member.kakao.KakaoAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KakaoUserInfoResponse {
    @JsonProperty("id")
    private Long memberId;
    @JsonProperty("connected_at")
    private String connectedAt;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;
}
