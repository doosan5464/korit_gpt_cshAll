package com.korit.main;

import com.korit.util.ForEachPrinter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
제네릭
왜 쓰냐? (매개변수로 Object로 받으면 되니까)
- 이러면 업캐스팅이 Object로 되서 하위를 쓰려면 다운캐스팅하는 번거로움때문에
- 미리 <T> 로 지정하면 업캐스팅이 안된다. 상위가 아니라 그냥 타입이니까

 */
public class Main3 {

    public static void main(String[] args) {
        // new ForEachPrinter2<Integer>(); -> Integer 생략시 Object 자동으로
        String[] names = new String[] {"a","b","c"};
        List<String> datas = ForEachPrinter2.print(Arrays.asList(names), data -> System.out.println(data)); // a, b, c
        // names는 String[] 타입. ForEachPrinter2매개변수에서 T가 String으로 변함. 반환타입도 String으로. 그래서 처음 변수 지정시 List<String> datas.

        // List.of로 지정한 정적인 리스트를 동적으로 쓰는 법. clean code - 잘 모르는 사람과 코드를 공유시
        List<Integer> list = List.of(1,2,3,4,5); // 불변, 상수 리스트
        List<Integer> list2 = new ArrayList<>();
        list2.addAll(list);
        list2.add(10);

        // stream 방법. refactoring code - 잘 아는 사람들끼리 코드 공유. 불필요한 코드 제거
        List<Integer> list3 = list.stream()
                    .collect(Collectors.toList());
        list3.add(20);


    }
}
