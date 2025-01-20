package com.korit.main;

import java.util.ArrayList;
import java.util.List;

public class Main4 {
    public static void main(String[] args) {

        List<Integer> temp = List.of(1,2,3,4,5,6,7,8,9,10);


        List<Integer> temp4 = new ArrayList<>();
        // List<Integer> temp4 = null
        // temp4 = new ArrayList<>(); -> 컴파일 단계에서 밑에 forEach는 여기 대입으로 읽지 않고 위에 null로 인식.

        // AtomicReference<Integer> ato = new AtomicReference<>(0); - 만약 전역변수를 람다식안에 쓰고 싶다면 타입을 이렇게 둬야하는데 나중에 학습

        temp.forEach(number -> temp4.add(number));
        // 함수조건문은 결국 지역변수. temp4에는 ArrayList가 들어와야 하는데 null로 초기화하고 밑에 대입하면 여기선 그렇게 읽지않음
        System.out.println(temp4);

    }
}
