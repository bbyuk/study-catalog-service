package com.polarbookshop.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "polar") // 이 클래스는 'polar'로 시작하는 설정 속성에 대한 소스임을 표시한다.
public class PolarProperties {
    /**
     * A message to welcome users.
     */
    private String greeting; // 사용자 정의 속성인 polar.greeting (prefix + 필드명) 속성이 문자열로 인식되는 필드.

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
