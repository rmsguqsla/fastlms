package com.zerobase.fastlms.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseInput {
    private long id;

    private long categoryId;

    private String subject;

    private String imagePath;

    private String keyword;

    private String summary;

    private String contents;

    private long price;

    private long salePrice;

    private String saleEndDtText;

    // 삭제를 위한
    String idList;
}
