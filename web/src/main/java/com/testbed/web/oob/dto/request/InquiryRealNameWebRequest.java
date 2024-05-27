package com.testbed.web.oob.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 private String 계좌실명조회 - /v2.0/inquiry/real_name
 private String bank_tran_id;
 private String bank_code_std;
 private String account_num;
 private String account_holder_info_type;
 private String account_holder_info;
 private String tran_dtime;
 private String @return;
 */
@Getter
@NoArgsConstructor
public class InquiryRealNameWebRequest {

    private String userId;
    private String bankTranId;
    private String bankCodeStd;
    private String accountNum;
    private String accountHolderInfoType;
    private String accountHolderInfo;
    private String tranDtime;

    @Builder
    public InquiryRealNameWebRequest(String userId, String bankTranId, String bankCodeStd, String accountNum, String accountHolderInfoType, String accountHolderInfo, String tranDtime) {
        this.userId = userId;
        this.bankTranId = bankTranId;
        this.bankCodeStd = bankCodeStd;
        this.accountNum = accountNum;
        this.accountHolderInfoType = accountHolderInfoType;
        this.accountHolderInfo = accountHolderInfo;
        this.tranDtime = tranDtime;
    }
}

