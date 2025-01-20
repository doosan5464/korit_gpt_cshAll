package com.korit.service;

public class ATestService implements TestPrint {

    @Override
    public void print() {
        System.out.println("ATestService 출력");
    }
}
