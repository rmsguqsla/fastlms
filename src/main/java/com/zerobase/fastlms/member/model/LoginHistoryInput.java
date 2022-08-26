package com.zerobase.fastlms.member.model;

import com.zerobase.fastlms.member.entity.MemberCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Data
public class LoginHistoryInput implements MemberCode {
    private String userId;

    private LocalDateTime loginDt;

    private String clientIp;

    private String userAgent;
}
