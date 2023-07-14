package com.dailybaekjoon.dto.member;

import com.dailybaekjoon.entity.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// NOTE : 이메일, 토큰값 DB에 저장
public class KakaoSaveRequestDto {
    private String email;
    private String nickName;
    private String accessToken;
    private String refreshToken;
    @Builder
    public KakaoSaveRequestDto(String email, String nickName, String accessToken, String refreshToken){
        this.email=email;
        this.nickName=nickName;
        this.accessToken=accessToken;
        this.refreshToken=refreshToken;
    }

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .nickName(nickName)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .providerId("KAKAO")
                .build();
    }
}
