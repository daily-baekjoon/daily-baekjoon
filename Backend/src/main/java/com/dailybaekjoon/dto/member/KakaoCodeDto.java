package com.dailybaekjoon.dto.member;

import lombok.*;

@Data
// NOTE : 인가코드를 받기위해 필요한 code값
public class KakaoCodeDto {
    private String code;
}