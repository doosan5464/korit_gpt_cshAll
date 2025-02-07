package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
// mapper : SQL과 Java 객체를 연결하는 역할을 하는 인터페이스 또는 XML 파일을 의미
public interface CategoryMapper {

    int insert(Category category);
    // Entity 클래스는 db의 category 테이블과 매핑이 됨
    // String이 아니라 굳이 Category 타입으로 잡은 이유 : 객체식으로 안에 몇개의 매개변수가 있을지 몰라서
    // (@NoArgus~, @AllArgus~ 로 매개변수의 경우가 2가지)

    List<Category> selectAllByNameContaining(@Param(value = "categoryName") String categoryName);
    // @Param은 매개변수를 지정해주는 어노테이션 (보통 타입이 객체가 아니라 그냥 String 같은거 일때)
    // 하나일때는 생략해도 문제없지만 2개 이상일때는 하나씩 지정해줘야 함
    // 그냥 Select는 검색할 categoryName만 필요하므로 객체 Category는 필요없음. 그래서 String
}