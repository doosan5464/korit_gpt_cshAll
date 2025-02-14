package com.korit.springboot_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Controller 어노테이션을 해줘야 함
// 내장되어있는 Dispather Servlet을 통해 여기서 GetMapping으로 경로를 관리가능
public class FirstController {


    @GetMapping("/mvc/hello")
    // model은 데이터 view는 템플릿 controller는 ??
    public String hello(Model model) { // Model을 매개변수로 받아올 수 있음.
        // DispatcherServlet에서 Model 객체 생성
        model.addAttribute("name", "김준일"); // Model에 키 값 형태로 설정
        model.addAttribute("name", "김준일");
        model.addAttribute("name", "김준일");
        model.addAttribute("name", "김준일");
        System.out.println("hello 메소드 호출");
        return "hello";
        // Thymeleaf에서는 템플릿 이름만 반환하면 자동으로 src/main/resources/templates/ 경로에서 .html을 찾아서 렌더링해줌
        // Thymeleaf :  resources/templates/ 폴더 안에 HTML 파일을 넣으면 됨
    }


    @GetMapping("/mvc/hello2")
    public String hello2() {
        System.out.println("hello2 메소드 호출");
        return "hello";
    }
}