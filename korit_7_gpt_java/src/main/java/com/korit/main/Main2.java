package com.korit.main;

import com.korit.util.ForEachPrinter;

public class Main2 {
    public static void main(String[] args) {
        Integer[] nums = new Integer[] {1,2,3,4,5,6,7,8,9}; // 런타임에 새로운 주소(동적 메모리)에 넣음
        Integer[] nums2 = new Integer[] {11,22,33,44,55,66,77,88,99};
        Integer[] nums3 = new Integer[] {111,222,333,444,55,666,777,888,999};

        /*for (Integer num : nums) {
            System.out.println(num);
        }
        for (int i = 0; i < nums2.length; i++) {
            System.out.println(nums2[i]);
        }*/

        ForEachPrinter forEachPrinter = new ForEachPrinter();
        forEachPrinter.print(nums);
        forEachPrinter.print(nums2);
        forEachPrinter.print(nums3);
    }
}
