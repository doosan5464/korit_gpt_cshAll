package com.korit.springboot_study.dto.request;

import com.korit.springboot_study.entity.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ReqAddCategoryDto {
    @ApiModelProperty(value = "카테고리명", example = "교육", required = true)
    @Pattern(regexp = "^(?=.*[가-힣])[\\d가-힣]+$", message = "입력값은 최소한 한 글자 이상의 한글을 포함해야 하며, 숫자는 한글이 포함된 경우에만 사용할 수 있습니다.")
    private String categoryName;

    // @Builder 대신에 Dto -> Entity 로 변환하는 메서드 생성
    public Category toCategory() {
        return Category.builder()
                .categoryName(categoryName)
                .build();
    }
}