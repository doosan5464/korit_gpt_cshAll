package com.korit.servlet_study.datas;

import com.korit.servlet_study.entity.Category;

import java.util.List;

public class DataList {

    // ProductRegisterPageServlet에서 사용될 데이터를 미리 준비하는 클래스
    public static List<Category> getCategoryList() {
        return List.of(
            new Category(1, "커피"),
            new Category(2, "에이드"),
            new Category(3, "티")
        );
    }
}
