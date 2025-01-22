package com.korit.servlet_study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
    private int status;
    private String message;
    private T data;

    // 서버에서 클라이언트로 응답을 보낼 때 사용하는 응답 포맷을 정의하는 제네릭 클래스
    // status: 응답의 상태 코드(예: 200, 400)를 나타냄.
    // message: 응답 메시지(성공 또는 실패 메시지).
    // data: 실제 응답에 포함될 데이터. 타입은 T로 유연하게 설정 가능.
    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<T>(200, "success", data);
    }

    public static <T> ResponseDto<T> fail(T data) {
        return new ResponseDto<T>(400, "fail", data);
    }
}
