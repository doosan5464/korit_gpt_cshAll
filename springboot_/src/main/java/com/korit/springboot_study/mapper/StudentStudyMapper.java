package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.entity.study.Major;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // @Mapper라는 컴포넌트로 분리
// mapper : SQL과 Java 객체를 연결하는 역할을 하는 인터페이스 또는 XML 파일을 의미
// mapper는 인터페이스로 만들어야 함
public interface StudentStudyMapper {

    List<Major> selectMajorsAll(); // List인 이유 : mybatis가 List를 보고 단건조회라고 인식해서?
    // 함수 선언만 있고 구현이 없음. 그래도 실행이 됨
    // 왜? @Mapper를 붙이면 MyBatis가 이 인터페이스의 구현체를 자동으로 생성
    // student_study_mapper.xml 에서 sql을 정의해놨고 where로 조건도 있음

    List<Instructor> selectInstructorsAll();

    int insertMajor(Major major);

    int insertInstructor(Instructor instructor);

    int updateMajorName(Major major);
    // insert는 return이 무조건 int(성공횟수)
    // update도 마찬가지
}
