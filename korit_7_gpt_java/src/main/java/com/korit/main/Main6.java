package com.korit.main;

import lombok.AllArgsConstructor;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main6 {

    @AllArgsConstructor
    static class Authority<T> implements Supplier<T> {
        private T role;

        @Override
        public T get() {
            return role;
        }
    }

    public static void main(String[] args) {
        // 주로 쓰이는 인터페이스들

        Runnable runnable;
        // Thread(Main 메서드의 역할) - 그래서 run able
        // 그냥 interface 로 안에 추상 메서드 run() 하나만 있음

                List<Runnable> programs = new ArrayList<>();
                // Runnable 을 담을수 있는 list 객체
                // Runnable 은 안에 함수가 하나 있는 @FunctionalInterface 라서 람다식 사용 가능

                for (int i = 0; i < 5; i++) {
                    final int fianalI = i;
                    programs.add(() -> System.out.println("프로그램" + (fianalI + 1)) );
                }
                for (Runnable program : programs) {
                    program.run();
                }


        Consumer<String> consumer;
        // 매개변수로 값을 받아서 해당 값을 처리(return 해주지는 않음) forEach 에서 사용





        Supplier<String> supplier;
        // 매개변수로 값을 받지 않고 처리한 데이터를 return 해준다
        Authority<String> authority = new Authority<>("ROLE_ADMIN");
        String role = authority.get();



        Function<String, String> function;
        // 매개변수로 값을 받아서 처리한 데이터를 return 해주는 역할
        // 람다식이 한번 쓰고 끝내는 것처럼 작용
        Function<Integer, Integer> addFunction = num -> num + 10;
        Integer result = addFunction.apply(100);

        BiFunction<Integer, Integer, String> biFunction;
        BiFunction<Integer, Integer, Integer> addBiFunction = (num1, num2) -> num1 + num2;
        Integer result2 = addBiFunction.apply(100,200);



        Map<String, Object> userMap = Map.of("username", "admin", "password", "1234");
        /*
        키 username
        값 admin
        한쌍을 entryS - "username" : "admin"
         */
        // Map을 반복문으로 쓰는 방법
        Set<Map.Entry<String, Object>> entries = userMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) { // Set으로 반복을 돌리기 위해?
            entry.getKey();
            entry.getValue();
        }
        userMap.forEach((key, value) -> {
            System.out.println("key : " + key + "value :" + value);
        });



        Predicate<String> predicate;
        // boolean 반환(무조건) 그래서 매개변수가 1개
        List<Integer> nums = List.of(1, 2, 3, 4, 5);
        List<Integer> evenNums = nums.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());



        UnaryOperator<Integer> unaryOperator;
        BinaryOperator<Double> binaryOperator;


        BiConsumer<Integer, String> biConsumer;
        BiPredicate<Integer, Integer> biPredicate;



    }
}
