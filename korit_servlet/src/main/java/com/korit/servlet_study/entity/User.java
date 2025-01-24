package com.korit.servlet_study.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User { // sql에서 받아 저장할 데이터
    private int userId;
    private String username;
    @JsonIgnore // Json으로 변환할때 제외시킴. 바로 밑에 것만
    private String password;
    private String name;
    private String email;
}