package com.dailybaekjoon.controller.member;

import com.dailybaekjoon.dto.member.*;
import com.dailybaekjoon.service.member.MemberService;
import com.dailybaekjoon.entity.member.kakao.KakaoUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Member Controller")
@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
@Slf4j
public class MemberController {
    private final KakaoTokenJsonData kakaoTokenJsonData;
    private final MemberService memberService;
    private final KakaoUserInfo kakaoUserInfo;
    // NOTE : "회원이 소셜 로그인을 끝마치면 자동으로 실행되는 API"
    //  "인가 코드를 이용해 토큰을 받고, 해당토큰으로 사용자 정보를 조회함"
    //  "사용자 정보를 이용하여 서비스에 회원가입함"
    @GetMapping("/oauth/kakao")
    @ApiOperation(value = "인가 코드로 토큰 받아온 후, 사용자 정보 조회함")
    public String kakaoOuth(@RequestBody KakaoCodeDto kakaoCodeDto){
        String code= kakaoCodeDto.getCode();
        log.info("인가 코드를 이용하여 토큰을 받습니다.");
        KakaoTokenResponse kakaoTokenResponse= kakaoTokenJsonData.getToken(code);

        log.info("Token information.{}",kakaoTokenResponse);
        String accessToken = kakaoTokenResponse.getAccessToken();

        log.info("token : {}", accessToken);
        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(accessToken);

        log.info("This is member information.{}",userInfo);

        KakaoSaveRequestDto kakaoSaveRequestDto = KakaoSaveRequestDto.builder()
                .email(userInfo.getKakaoAccount().getEmail())
                .accessToken(kakaoTokenResponse.getAccessToken())
                .refreshToken(kakaoTokenResponse.getAccessToken())
                .build();

        memberService.createKakaoMember(kakaoSaveRequestDto);

        return "okay";
    }
}
