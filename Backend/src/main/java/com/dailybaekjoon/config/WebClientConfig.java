package com.dailybaekjoon.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.function.Function;

@Configuration
public class WebClientConfig {
    // NOTE : ReactorResourceFactory 빈을 생성해서 사용할 수 있도록 설정
    //  반응형 구성 요소에 리소스를 할당하고 관리하는 방법을 제어함
    @Bean
    public ReactorResourceFactory resourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false);
        return factory;
    }

    // NOTE : WebClient 빈을 생성해서 사용할 수 있도록 설정
    @Bean
    public WebClient webClient() {
        Function<HttpClient, HttpClient> mapper = client -> { // HttpClient 옵션을 설정하기 위한 Function을 정의
            return HttpClient.create() // HttpClient 인스턴스를 생성
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000) // 연결 시간 초과를 1초로 설정
                    .doOnConnected(connection -> { // 연결된 클라이언트에 ReadTimeoutHandler와 WriteTimeoutHandler를 추가
                        connection.addHandlerLast(new ReadTimeoutHandler(10)) // 10초 동안 응답이 없을 경우 연결을 종료
                                .addHandlerLast(new WriteTimeoutHandler(10)); // 10초 동안 요청을 보내지 않을 경우 연결을 종료
                    })
                    .responseTimeout(Duration.ofSeconds(1)); // 응답 시간 초과를 1초로 설정
        };
        ClientHttpConnector connector = // ReactorClientHttpConnector를 생성
                new ReactorClientHttpConnector(resourceFactory(), mapper); // ReactorResourceFactory와 mapper 함수를 전달하여 사용

        // WebClient를 빌더 패턴을 사용하여 생성, ClientHttpConnector를 설정하여 사용
        return WebClient.builder().clientConnector(connector).build();
    }
}