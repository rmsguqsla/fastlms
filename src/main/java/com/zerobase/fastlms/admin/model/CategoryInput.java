package com.zerobase.fastlms.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryInput {

    private long id;
    private String categoryName;
    private int sortValue;
    private boolean usingYn;
}
