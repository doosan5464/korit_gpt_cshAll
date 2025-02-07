package com.korit.springboot_study.dto.request;

import com.korit.springboot_study.entity.Author;
import com.korit.springboot_study.entity.Book;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
public class ReqAddBookDto {
    @ApiModelProperty(value = "도서명", example = "소나기", required = true)
    @Pattern(regexp = "^(?=.*[가-힣])[가-힣\\d]+$", message = "입력값은 최소한 한 글자 이상의 한글을 포함해야 하며, 숫자는 한글이 포함된 경우에만 사용할 수 있습니다.")
    private String bookName;
    @ApiModelProperty(value = "저자 ID", example = "1", required = true)
    @Min(value = 1, message = "입력값은 1이상의 정수입니다.")
    private int authorId;
    @ISBN
    @ApiModelProperty(value = "ISBN", example = "D200320910", required = true)
    private String isbn;
    @ApiModelProperty(value = "카테고리 ID", example = "1", required = true)
    @Min(value = 1, message = "입력값은 1이상의 정수입니다.")
    private int categoryId;
    @ApiModelProperty(value = "출판사 ID", example = "1", required = true)
    @Min(value = 1, message = "입력값은 1이상의 정수입니다.")
    private int publisherId;
    @ApiModelProperty(value = "이미지 경로(URL)", example = "http://example.com/test.jpg", required = true)
    @URL
    private String bookImgUrl;

    public Book toBook() {
        return Book.builder()
                .bookName(bookName)
                .authorId(authorId)
                .isbn(isbn)
                .categoryId(categoryId)
                .publisherId(publisherId)
                .bookImgUrl(bookImgUrl)
                .build();
    }

}