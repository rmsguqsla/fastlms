package com.zerobase.fastlms.course.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private long categoryId;
    
    private String imagePath;
    private String keyword;
    private String subject;
    
    @Column(length = 1000)
    private String summary;
    
    @Lob
    private String contents;
    private long price;
    private long salePrice;
    private LocalDate saleEndDt;

    private LocalDateTime regDt;//등록일(추가날짜)
    private LocalDateTime udtDt;//수정일(수정날짜)


    private String filename;
    private String urlFilename;
    
}
