    package com.korit.springboot_study.ioc;

    import org.springframework.stereotype.Component;

    @Component(value = "c2") // IoC 컨테이너에 의해 관리되는 하나의 Bean
    public class ClassC2 implements ClassC{

        @Override
        public void classCallC() {
            System.out.println("ClassC2 호출");
        }
    }