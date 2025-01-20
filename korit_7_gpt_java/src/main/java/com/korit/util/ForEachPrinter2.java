package com.korit.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer; // 람다 전용 객체

// 제네릭 - 아무 타입이나. 참조형으로
/*
제네릭클래스안에 정적메서드는 X 이미 메모리에 올라가있으니까 생성안됨
-> 그래서 반환타입에 <T> 이런 식으로 미리 지정

클래스 자체가 제네릭 타입 매개변수를 사용하고 있기 때문에 메모리에 이미 할당된 정적 메서드라도 이렇게 동적인 타입변환이 가능하다
메서드의 반환타입
 */
public class ForEachPrinter2 {
    // T타입의 List<T>를 반환 (void 대신)
    public static <T> List<T> print(List<T> datas, Consumer<List<T>> action) {
        List<T> result = new ArrayList<>();

        action.accept(datas);

        return result;
//        return (R) "ddd";
    }
}
