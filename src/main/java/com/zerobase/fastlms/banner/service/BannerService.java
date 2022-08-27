package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.course.dto.CourseDto;

import java.util.List;

public interface BannerService {
    /**
     * 배너 목록
     */
    List<BannerDto> list(BannerParam parameter);

    /**
     * 배너 상세
     */
    BannerDto getById(long id);

    /**
     * 배너 수정
     */
    boolean set(BannerInput parameter);

    /**
     * 배너 등록
     */
    boolean add(BannerInput parameter);

    /**
     * 배너 삭제
     */
    boolean delete(String idList);

    /**
     * 프론트 배너
     */
    List<BannerDto> frontList();
}