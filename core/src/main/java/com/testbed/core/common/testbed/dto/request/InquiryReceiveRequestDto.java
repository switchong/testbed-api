package com.testbed.core.common.testbed.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 수취조회 - /v2.0/inquiry/receive;
 bank_tran_id - 은행거래고유번호주
 cntr_account_type(N) - 약정 계좌/계정구분 ( N:계좌, C:계정 )
 cntr_account_num - 약정 계좌/계정 번호
 bank_code_std - 입금은행.표준코드 (N)
 account_num - 계좌번호(N)
 account_seq - 회차번호(N)
 fintech_use_num - 핀테크이용번호(N)
 print_content - 입금계좌인자내역
 tran_amt - 거래금액
 req_client_name - 요청고객성명
 req_client_bankCode - 요청고객계좌 , 개설기관.표준코드 (N)
 req_client_account_num - 요청고객계좌번호(N)
 req_client_fintech_use_num - 요청고객핀테크이용번호(N)
 req_client_num - 요청고객회원번호
 transfer_purpose - 이체용도 ( TR:송금, ST:결제, AU:인증 )
 sub_frnc_name - 하위가맹점명 (N)
 sub_frnc_num - 하위가맹점번호 (N)
 sub_frnc_business_num - 하위가맹점 사업자등록번호(N)
 cms_num - CMS 번호 (N)
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InquiryReceiveRequestDto {

    private static InquiryReceiveRequestDto inquiryReceiveRequestDto;

    @JsonProperty("bank_tran_id")
    private String bankTranId;
    @JsonProperty("cntr_account_type")
    private String cntrAccountType;
    @JsonProperty("cntr_account_num")
    private String cntrAccountNum;
    @JsonProperty("bank_code_std")
    private String bankCodeStd;
    @JsonProperty("account_num")
    private String accountNum;
    @JsonProperty("account_seq")
    private String accountSeq;
    @JsonProperty("fintech_use_num")
    private String fintechUseNum;
    @JsonProperty("print_content")
    private String printContent;
    @JsonProperty("tran_amt")
    private String tranAmt;
    @JsonProperty("req_client_name")
    private String reqClientName;
    @JsonProperty("req_client_bank_code")
    private String reqClientBankCode;
    @JsonProperty("req_client_account_use_num")
    private String reqClientAccountNum;
    @JsonProperty("req_client_fintech_use_num")
    private String reqClientFintechUseNum;
    @JsonProperty("req_client_num")
    private String reqClientNum;
    @JsonProperty("transfer_purpose")
    private String transferPurpose;
    @JsonProperty("sub_frnc_name")
    private String subFrncName;
    @JsonProperty("sub_frnc_num")
    private String subFrncNum;
    @JsonProperty("sub_frnc_business_num")
    private String subFrncBusinessNum;
    @JsonProperty("cms_num")
    private String cmsNum;

    public InquiryReceiveRequestDto(String bankTranId, String cntrAccountType, String cntrAccountNum, String bankCodeStd, String accountNum, String accountSeq, String fintechUseNum, String printContent, String tranAmt
            , String reqClientName, String reqClientBankCode, String reqClientAccountNum, String reqClientFintechUseNum, String reqClientNum, String transferPurpose, String subFrncName, String subFrncNum, String subFrncBusinessNum, String cmsNum) {
        if(cntrAccountType.isEmpty()) {
            cntrAccountType = "N";
        }
        this.bankTranId = bankTranId;
        this.cntrAccountType = cntrAccountType;
        this.cntrAccountNum = cntrAccountNum;
        this.bankCodeStd = bankCodeStd;
        this.accountNum = accountNum;
        this.accountSeq = accountSeq;
        this.fintechUseNum = fintechUseNum;
        this.printContent = printContent;
        this.tranAmt = tranAmt;
        this.reqClientName = reqClientName;
        this.reqClientBankCode = reqClientBankCode;
        this.reqClientAccountNum = reqClientAccountNum;
        this.reqClientFintechUseNum = reqClientFintechUseNum;
        this.reqClientNum = reqClientNum;
        this.transferPurpose = transferPurpose;
        this.subFrncName = subFrncName;
        this.subFrncNum = subFrncNum;
        this.subFrncBusinessNum = subFrncBusinessNum;
        this.cmsNum = cmsNum;
    }

    public static InquiryReceiveRequestDto of (String bankTranId, String cntrAccountType, String cntrAccountNum, String bankCodeStd, String accountNum, String accountSeq, String fintechUseNum, String printContent, String tranAmt
            , String reqClientName, String reqClientBankCode, String reqClientAccountNum, String reqClientFintechUseNum, String reqClientNum, String transferPurpose, String subFrncName, String subFrncNum, String subFrncBusinessNum, String cmsNum) {
        return new InquiryReceiveRequestDto( bankTranId, cntrAccountType, cntrAccountNum, bankCodeStd, accountNum, accountSeq, fintechUseNum, printContent, tranAmt
                , reqClientName, reqClientBankCode, reqClientAccountNum, reqClientFintechUseNum, reqClientNum, transferPurpose, subFrncName, subFrncNum, subFrncBusinessNum, cmsNum);
    }
}

