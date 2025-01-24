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
    private T body;

    // 서버에서 클라이언트로 응답을 보낼 때 사용하는 응답 포맷을 정의하는 제네릭 클래스
    // status: 응답의 상태 코드(예: 200, 400)를 나타냄.
    // message: 응답 메시지(성공 또는 실패 메시지).
    // data: 실제 응답에 포함될 데이터. 타입은 T로 유연하게 설정 가능. 각 메서드에서 반환하는 객체의 실제 데이터


    // success, fail, forbidden : 정적인 메서드여서 기능을 한다
    public static <T> ResponseDto<T> success(T body) {
        return new ResponseDto<>(200, "success", body);
    }

    public static <T> ResponseDto<T> fail(T body) {
        return new ResponseDto<>(400, "fail", body);
    }

    public static <T> ResponseDto<T> forbidden(T body) {
        return new ResponseDto<>(403, "Forbidden", body);
    }
}
