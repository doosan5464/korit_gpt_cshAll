package com.korit.servlet_study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

// 데이터 저장
public class Category {
    private int id;
    private String name;
}