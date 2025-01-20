package com.korit.main;

import com.korit.service.ATestService;
import com.korit.service.BTestService;
import com.korit.service.TestPrint;

import java.util.Random;

public class Main1 {

    public static void main(String[] args) {
/*        int a = 10; // 값을 담음. int 바이트 크기만큼
        System.out.println(a);

        ATestService aTestService = new ATestService(); // 주소만 담음
        BTestService bTestService = new BTestService();

        System.out.println(new ATestService()); // com.korit.service.ATestService@2133c8f8 데이터는 없지만 메모리를 할당하긴 함
        System.out.println(new BTestService()); // com.korit.service.BTestService@43a25848

        aTestService.print(); // ATestService 출력
        bTestService.print(); // BTestService 출력*/
        TestPrint testPrint = null;

        ATestService aTestService = new ATestService();
        BTestService bTestService = new BTestService();

        Random random = new Random();
        int randomInt = random.nextInt(100);

        if (randomInt % 2 == 0) {
            testPrint = aTestService;
        } else {
            testPrint = bTestService;
        }
    }
}
