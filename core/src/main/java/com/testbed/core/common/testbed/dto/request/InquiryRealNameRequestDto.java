package com.testbed.core.common.testbed.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 private String 계좌실명조회 - /v2.0/inquiry/real_name;
 private String bank_tran_id;
 private String bank_code_std;
 private String account_num;
 private String account_holder_info_type;
 private String account_holder_info;
 private String tran_dtime;
 private String @return;
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InquiryRealNameRequestDto {

    private static InquiryRealNameRequestDto inquiryRealNameRequestDto;

    @JsonProperty("bank_tran_id")
    private String bankTranId;
    @JsonProperty("bank_code_std")
    private String bankCodeStd;
    @JsonProperty("account_num")
    private String accountNum;
    @JsonProperty("account_holder_info_type")
    private String accountHolderInfoType;
    @JsonProperty("account_holder_info")
    private String accountHolderInfo;
    @JsonProperty("tran_dtime")
    private String tranDtime;

    public InquiryRealNameRequestDto(String bankTranId, String bankCodeStd, String accountNum, String accountHolderInfoType, String accountHolderInfo, String tranDtime) {
        this.bankTranId = bankTranId;
        this.bankCodeStd = bankCodeStd;
        this.accountNum = accountNum;
        this.accountHolderInfoType = accountHolderInfoType;
        this.accountHolderInfo = accountHolderInfo;
        this.tranDtime = tranDtime;
    }

    public static InquiryRealNameRequestDto of (String bankTranId, String bankCodeStd, String accountNum, String accountHolderInfoType, String accountHolderInfo, String tranDtime) {
        return new InquiryRealNameRequestDto(bankTranId, bankCodeStd, accountNum, accountHolderInfoType, accountHolderInfo, tranDtime);
    }
}

