package com.dailybaekjoon.dto.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

// NOTE : 인가코드를 이용하여 Token ( Access , Refresh )를 받는다.
@Component
@RequiredArgsConstructor
public class KakaoTokenJsonData {
    private final WebClient webClient;
    @Value("${kakao.tokenUri}")
    private String TOKEN_URI;
    @Value("${kakao.redirectUri}")
    private String REDIRECT_URI;
    @Value("${kakao.grantType}")
    private String GRANT_TYPE;
    @Value("${kakao.clientId}")
    private String CLIENT_ID;
    @Value("${kakao.clientSecret}")
    private String CLIENT_SECRET;


    public KakaoTokenResponseDto getToken(String code) {
        String uri = TOKEN_URI + "?grant_type=" + GRANT_TYPE + "&client_id=" + CLIENT_ID + "&client_secret="+CLIENT_SECRET+"&redirect_uri=" + REDIRECT_URI + "&code=" + code;
        System.out.println(uri);

        Flux<KakaoTokenResponseDto> response = webClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(KakaoTokenResponseDto.class);

        return response.blockFirst();
    }


}
