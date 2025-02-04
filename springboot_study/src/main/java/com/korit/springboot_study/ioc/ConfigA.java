package com.korit.springboot_study.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Component 와 @Bean 의 차이
// @Component : 클래스를 빈으로 등록할 때 사용
// @Bean : 메서드의 반환값을 빈으로 등록

@Configuration
// @Configuration 의 특징
// 1. 호출하지 않아도 @Bean이 달린 메서드는 호출이 된다
// 2. @Bean 메서드가 리턴하는 타입도 @Bean으로 등록된다(이런식으로 등록된 @Bean들은 메서드명이 component의 이름이 된다)
// 3. 우선순위가 높다. 설정 파일이기 때문에
// 4. @Configuration은 싱글톤으로 관리되므로, 한 번만 초기화되며 여러 번 호출되지 않는다
public class ConfigA {

    @Bean // 언제 쓰냐??
    // iocContainer 대신에 직접 관리해야 할 객체 생성
    // 라이브러리에 있는 생성자를 Bean 으로 등록하고 싶을 때
    public ClassD call() { // 타입을 ClassD로 둬서 ClassD 또한 @Bean으로 등록. (call 컴포넌트로)
        System.out.println("ConfigA Call");
        return new ClassD();
    }
}