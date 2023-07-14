package com.dailybaekjoon.controller.member;

import com.dailybaekjoon.dto.member.*;
import com.dailybaekjoon.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("Member Controller")
@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
@Slf4j
public class MemberController {
    private final KakaoTokenJsonData kakaoTokenJsonData;
    private final MemberService memberService;

    @PostMapping("/oauth/kakao")
    @ApiOperation(value = "카카오 소셜 회원가입", notes = "인가 코드 요청 , 인가 코드를 전송해주고 토큰 받기, 해당 토큰으로 사용자 정보 받아오기")
    public ResponseEntity<?> kakaoOuth(@RequestBody KakaoCodeDto kakaoCodeDto){
        try {
            // NOTE : 인가 코드(code)로 엑세스 토큰(access token) 발급받기
            String code = kakaoCodeDto.getCode();
            KakaoTokenResponseDto kakaoTokenResponseDto = kakaoTokenJsonData.getToken(code);
            KakaoLoginResponseDto memberLoginResponseDto = memberService.kakaoLogin(kakaoTokenResponseDto);
            return ResponseEntity.ok().body(memberLoginResponseDto);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
