package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.study.ReqStudentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController // RestController는 view 리턴이 없음. 이 컨트롤러안에서 뭐 하고 뭐 다 한다면 사용
@Api(tags = "REST API 수업") // Controller의 이름을 바꿔줌. Swagger에서 보기 좋게
public class FirstRestController {

    @ResponseBody // 응답의 body부분에 return을 포함. (데이터 형식으로만 응답, view, viewResolver 무시), @RestController안에 있다면 생략 가능
    @GetMapping("/api/hello")
    public Map<String, Object> hello(HttpServletRequest request) { // 원래 하던 방식
        String age = request.getParameter("age"); // request니까 Get요청. 프론트에서 날라온 "age"를 받아옴
        String address = request.getParameter("address");
        System.out.println(age);
        System.out.println(address);
        return Map.of("name", "김준일"); // 키, 값으로 반환하면 Json형식
    }

    @GetMapping("/api/hello2")
    public Map<String, Object> hello2(
            @RequestParam int age, // 자동으로 request에서 가져와서 형변환도 해주고 가져옴
            @RequestParam String address
            ) {

        System.out.println(age);
        System.out.println(address);
        return Map.of("name", "김준일");
    }

    @GetMapping("/api/student")
    @ApiOperation(value = "학생 조회(일반 for문)", notes = "일반 for문을 사용하여 선형 탐색 학습")
    public Map<String, Object> getStudent(
            @ApiParam(value = "조회할 학생 인덱스", required = true) // API의 파라미터에 대한 설명을 추가, required : 해당 파라미터가 필수인지 여부를 지정
            @RequestParam int id
    ) { // @RequestParam 없어도 되지만 명시의 목적
        List<Map<String, Object>> students = new ArrayList<>();
        students.add(Map.of("id", "0", "name", "최석현", "age", 26));
        students.add(Map.of("id", "1", "name", "백진우", "age", 32));
        students.add(Map.of("id", "2", "name", "이주원", "age", 28));
        students.add(Map.of("id", "3", "name", "정영훈", "age", 26));

        int foundIndex = -1;

        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).get("id").equals(id)) {
                foundIndex = i;
            }
        }

        if (foundIndex != -1) {
            return Map.of("error", "못찾음");
        }

        return students.get(id);
    }


    @GetMapping("/api/student2")
    @ApiOperation(value = "학생조회(foreach문)", notes = "조금 더 간편하게")
    public Map<String, Object> getStudent2(
            @ApiParam(value = "조회할 학생 인덱스", required = true)
            @RequestParam int studentId
    ) { // @RequestParam 없어도 되지만 명시의 목적
        List<Map<String, Object>> students = new ArrayList<>();
        students.add(Map.of("id", 11, "name", "최석현", "age", 26));
        students.add(Map.of("id", 22, "name", "백진우", "age", 32));
        students.add(Map.of("id", 33, "name", "이주원", "age", 28));
        students.add(Map.of("id", 44, "name", "정영훈", "age", 26));

        Map<String, Object> foundStudent = null;

        for(Map<String, Object> student : students) {
            if((Integer) student.get("id") == studentId) {
                foundStudent = student;
                break;
            }
        }

        if(foundStudent == null) {
            return Map.of("error", "못찾음");
        }

        return foundStudent;
    }


    @GetMapping("/api/student3")
    @ApiOperation(value = "학생조회(steam)", notes = "더 간편하게")
    public Map<String, Object> getStudent3(
            @ApiParam(value = "조회할 학생 인덱스", required = true)
            @RequestParam int studentId
    ) { // @RequestParam 없어도 되지만 명시의 목적
        List<Map<String, Object>> students = new ArrayList<>();
        students.add(Map.of("id", 11, "name", "최석현", "age", 26));
        students.add(Map.of("id", 22, "name", "백진우", "age", 32));
        students.add(Map.of("id", 33, "name", "이주원", "age", 28));
        students.add(Map.of("id", 44, "name", "정영훈", "age", 26));

        Map<String, Object> responseData = students.stream()
                .filter(student -> (Integer) student.get("id") == studentId)
                .findFirst() // findFirst()는 return이 Optional. null값 허용, filter가 null이 아니면
                .orElse(Map.of("error", "못찾음")) ; // null이면 이게 responseData에 대입

        return responseData;
    }


    @GetMapping("/api/getStudent4/{studentId}") // {studentId} : 경로 변수(Path Variable)
    // GET http://localhost:8080/api/getStudent4/1 -> 이러면 studentId = 1이 자동으로 매핑됨
    public Map<String, Object> getStudent4(
            @ApiParam(value = "학생 ID", required = true)
            @PathVariable int studentId, // 경로에 있는 변수
            ReqStudentDto reqStudentDto // dto에 있는 변수들을 가져올 수 있음
    ) {
        return Map.of("id", studentId, reqStudentDto.getName(), reqStudentDto.getAge()); // lombok
    }
}