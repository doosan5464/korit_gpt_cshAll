package com.korit.springboot_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring Boot 애플리케이션의 시작점
@SpringBootApplication
public class SpringbootStudyApplication {

    // servlet에서는 main없었음. 톰캣에서 실행을 해줬어서, spingboots 패키지? 라이브러리?안에 톰캣이 내장이 되어있음
    public static void main(String[] args) {
        SpringApplication.run(SpringbootStudyApplication.class, args);
    }

}
