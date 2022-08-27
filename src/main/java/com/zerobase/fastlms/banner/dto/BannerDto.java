package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BannerDto {
    long id;

    String bannerName;

    String filename;
    
    String urlFilename;

    String linkUrl;

    String target;

    int sortValue;

    boolean usingYn;

    LocalDateTime regDt;

    LocalDateTime udtDt;

    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .filename(banner.getFilename())
                .urlFilename(banner.getUrlFilename())
                .linkUrl(banner.getLinkUrl())
                .target(banner.getTarget())
                .sortValue(banner.getSortValue())
                .usingYn(banner.isUsingYn())
                .regDt(banner.getRegDt())
                .udtDt(banner.getUdtDt())
                .build();
    }

    public static List<BannerDto> of(List<Banner> banners) {
        if (banners.isEmpty()) {
            return null;
        }

        List<BannerDto> bannerList = new ArrayList<>();
        for (Banner x : banners) {
            bannerList.add(BannerDto.of(x));
        }

        return bannerList;
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return regDt != null ? regDt.format(formatter) : "";
    }
}
