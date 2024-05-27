package com.testbed.web.oob.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class InquiryReceiveWebRequest {

    private String userId;
    private String bankTranId;
    private String cntrAccountType;
    private String cntrAccountNum;
    private String bankCodeStd;
    private String accountNum;
    private String accountSeq;
    private String fintechUseNum;
    private String printContent;
    private String tranAmt;
    private String reqClientName;
    private String reqClientBankCode;
    private String reqClientAccountNum;
    private String reqClientFintechUseNum;
    private String reqClientNum;
    private String transferPurpose;
    private String subFrncName;
    private String subFrncNum;
    private String subFrncBusinessNum;
    private String cmsNum;

    @Builder
    public InquiryReceiveWebRequest(String userId, String bankTranId, String cntrAccountType, String cntrAccountNum, String bankCodeStd, String accountNum, String accountSeq, String fintechUseNum, String printContent, String tranAmt
            , String reqClientName, String reqClientBankCode, String reqClientAccountNum, String reqClientFintechUseNum, String reqClientNum, String transferPurpose, String subFrncName, String subFrncNum, String subFrncBusinessNum, String cmsNum) {
        if(cntrAccountType.isEmpty()) {
            cntrAccountType = "N";
        }
        this.userId = userId;
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
}

