package com.dailybaekjoon.service.member;

import com.dailybaekjoon.dto.member.KakaoSaveRequestDto;
import com.dailybaekjoon.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    @Transactional
    public void createKakaoMember(KakaoSaveRequestDto kakaoSaveRequestDto){
        memberRepository.save(kakaoSaveRequestDto.toEntity());
        log.info("새로운 회원 저장 완료");
    }
}
