package com.korit.boardback.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
// Bearer 담을려고?
public class RespTokenDto {
    private String type;
    private String name;
    private String token;

}
