package com.korit.main;

public class Main7 {


    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        System.out.println("첫번째 반복문 i: " + i);
                        Thread.sleep(1000); // 1초 - 개입하는 개념이라 오류가 생길 수도 있음
                    } catch (InterruptedException e) { // 중간에 끼어드는 에러
                        System.out.println("프로그램에 오류가 발생함");
                    }
                }
            }
        };
        Thread t1 = new Thread(runnable);
        // Thread 위로 타고 가보면 Runnable을 매개변수로 받는 생성자가 있음
        t1.start();


        Runnable runnable2 = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("두번째 반복문 i: " + i);
                    Thread.sleep(1000); // 1초
                } catch (InterruptedException e) {
                    System.out.println("프로그램에 오류가 발생함");
                }
            }
        };
        Thread t2 = new Thread(runnable2);
        t2.start();


        for (int i = 0; i < 100; i++) {
            System.out.println("MAIN Thread i: " + i);
            Thread.sleep(1000);
        }
        // 총 3개의 Thread // html 에서는 요청 하나당 스레드를 구분해서 동시에 처리함



    }


}
