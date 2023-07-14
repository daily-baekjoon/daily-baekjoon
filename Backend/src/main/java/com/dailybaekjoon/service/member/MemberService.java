package com.dailybaekjoon.service.member;

import com.dailybaekjoon.dto.member.KakaoLoginResponseDto;
import com.dailybaekjoon.dto.member.KakaoSaveRequestDto;
import com.dailybaekjoon.dto.member.KakaoTokenResponseDto;
import com.dailybaekjoon.dto.member.KakaoUserInfoResponseDto;
import com.dailybaekjoon.entity.member.Member;
import com.dailybaekjoon.entity.member.kakao.KakaoUserInfo;
import com.dailybaekjoon.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;
    private final KakaoUserInfo kakaoUserInfo;

    @Transactional
    public void kakaoJoin(KakaoSaveRequestDto kakaoSaveRequestDto){
        memberRepository.save(kakaoSaveRequestDto.toEntity());
        log.info("새로운 회원 저장 완료");
    }

    @Transactional
    public KakaoLoginResponseDto kakaoLogin(KakaoTokenResponseDto kakaoTokenResponseDto){
        KakaoUserInfoResponseDto userInfo = kakaoUserInfo.getUserInfo(kakaoTokenResponseDto.getAccessToken());
        Optional<Member> existingMember = memberRepository.findByEmail(userInfo.getKakaoAccount().getEmail());
        KakaoLoginResponseDto kakaoLoginResponseDto;

        // 1. 이미 존재하는 회원일 경우
        if(existingMember.isPresent()){
            Member member = existingMember.get();
            kakaoLoginResponseDto = new KakaoLoginResponseDto(
                    member.getName(),
                    member.getEmail(),
                    member.getBojId(),
                    member.getNickName(),
                    member.getAccessToken()
            );
        }
        // 2. 존재하지 않는 회원일 경우
        else {
            KakaoSaveRequestDto kakaoSaveRequestDto = KakaoSaveRequestDto.builder()
                    .email(userInfo.getKakaoAccount().getEmail())
                    .nickName(userInfo.getKakaoProperties().getNickname())
                    .accessToken(kakaoTokenResponseDto.getAccessToken())
                    .refreshToken(kakaoTokenResponseDto.getRefreshToken())
                    .build();
            kakaoJoin(kakaoSaveRequestDto);
            Optional<Member> newMember = memberRepository.findByEmail(userInfo.getKakaoAccount().getEmail());
            kakaoLoginResponseDto = new KakaoLoginResponseDto(
                    newMember.get().getName(),
                    newMember.get().getEmail(),
                    newMember.get().getBojId(),
                    newMember.get().getNickName(),
                    newMember.get().getAccessToken());
        }
        return kakaoLoginResponseDto;
    }
}
