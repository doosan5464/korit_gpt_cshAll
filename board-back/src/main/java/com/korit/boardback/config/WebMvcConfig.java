package com.korit.boardback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Configuration // Spring 설정 클래스임을 명시
public class WebMvcConfig implements WebMvcConfigurer {
// 특정 폴더에 있는 이미지 파일을 URL을 통해 제공하도록 설계한 설정 파일

    @Value("${user.dir}") // 애플리케이션 루트 경로 설정
    private String rootPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**") // "/image/**" 경로로 요청이 들어오면
                .addResourceLocations("file:" + rootPath + "/upload") // 실제 파일이 위치한 경로 지정
                .resourceChain(true)
                .addResolver(new PathResourceResolver() { // 리소스 경로 해석기 추가
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        resourcePath = URLDecoder.decode(resourcePath, StandardCharsets.UTF_8); // URL 디코딩
                        return super.getResource(resourcePath, location);
                    }
                });
    }
}
