package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDto {
    String userId;
    String userName;
    String phone;
    String password;
    LocalDateTime regDt;
    LocalDateTime udtDt;
    boolean emailAuthYn;
    LocalDateTime emailAuthDt;
    String emailAuthKey;
    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;
    boolean adminYn;
    String userStatus;

    String zipcode;
    String addr;
    String addrDetail;

    LocalDateTime loginDt;

    // 추가컬럼
    long totalCount;
    long seq;

    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .phone(member.getPhone())
                //.password(member.getPassword())
                .regDt(member.getRegDt())
                .udtDt(member.getUdtDt())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthDt(member.getEmailAuthDt())
                .emailAuthKey(member.getEmailAuthKey())
                .resetPasswordKey(member.getResetPasswordKey())
                .adminYn(member.isAdminYn())
                .userStatus(member.getUserStatus())
                .zipcode(member.getZipcode())
                .addr(member.getAddr())
                .addrDetail(member.getAddrDetail())
                .build();
    }

    public String getRegDtTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";
    }

    public String getUdtDtTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return udtDt != null ? udtDt.format(formatter) : "";
    }

    public String getLoginDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return loginDt != null ? loginDt.format(formatter) : "";
    }
}
