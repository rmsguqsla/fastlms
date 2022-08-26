package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.MemberCode;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Data
public class LoginHistoryDto implements MemberCode {

    private String userId;

    private LocalDateTime loginDt;

    private String clientIp;

    private String userAgent;

    private long seq;

    public String getLoginDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return loginDt != null ? loginDt.format(formatter) : "";
    }
}
