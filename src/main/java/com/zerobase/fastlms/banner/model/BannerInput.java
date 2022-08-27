package com.zerobase.fastlms.banner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BannerInput {
    private long id;

    private String bannerName;

    private String linkUrl;

    private String target;

    private int sortValue;

    private boolean usingYn;

    // 삭제를 위한
    private String idList;

    private String filename;
    private String urlFilename;
}
