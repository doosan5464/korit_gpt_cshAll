package com.korit.util;

public class ForEachPrinter {
    // 정적 메서드는 런타임이 아니라 컴파일에 정적 메모리에 할당되니까 안에 있는 변수는 static final로 해야 런타임때 작동하는 놈들을 컴파일 때 초기화시킴
    static public void print(Integer[] nums) {
        for (Integer num : nums) {
            System.out.println(num);
        }
    }
}
