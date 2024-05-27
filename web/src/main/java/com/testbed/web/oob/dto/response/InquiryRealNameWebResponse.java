package com.testbed.web.oob.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * api_tran_id -  거래고유번호(API)
 * api_tran_dtm - 거래일시(밀리세컨드)
 * rsp_code -  응답코드(API)
 * rsp_message - 응답메시지(API)
 * bank_tran_id - 거래고유번호(참가은행)
 * bank_tran_date - 거래일자(참가은행)
 * bank_code_tran -  응답코드를 부여한 참가은행.표준코드
 * bank_rsp_code - 응답코드(참가은행)
 * bank_rsp_message - 응답메시지(참가은행)
 * bank_code_std - 개설기관.표준코드
 * bank_code_sub - 개설기관.점별코드
 * bank_name - 개설기관명
 * savings_bank_name - 개별저축은행명주1)
 * account_num - 계좌번호
 * account_seq - 회차번호
 * account_holder_info_type - 예금주 실명번호 구분코드 [" "|"1"|"2"|"3"|"4"|"5"|"6"|"E"|"N"]
 * account_holder_info - 예금주 실명번호
 * account_holder_name - 예금주성명
 * account_type - 계좌종류 [ 1:수시입출금, 2:예적금 , 6:수익증권, T:종합계좌 ]
 */
@Getter
@NoArgsConstructor
public class InquiryRealNameWebResponse {

    private String userId;
    private String apiTranId;
    private String apiTranDtm;
    private String rspCode;
    private String rspMessage;
    private String bankTranId;

    private String bankTranDate;
    private String bankCodeTran;
    private String bankRspCode;
    private String bankRspMessage;
    private String bankCodeStd;
    private String bankCodeSub;
    private String bankName;
    private String accountNm;
    private String accountHolderInfoType;
    private String accountHolderInfo;
    private String accountHolderName;
    private String accountType;


    @Builder
    public InquiryRealNameWebResponse (String userId, String apiTranId, String apiTranDtm, String rspCode, String rspMessage, String bankTranId,
                String bankTranDate, String bankCodeTran, String bankRspCode, String bankRspMessage, String bankCodeStd, String bankCodeSub, String bankName, String accountNm, String accountHolderInfoType, String accountHolderInfo, String accountHolderName, String accountType) {
        this.userId = userId;
        this.apiTranId = apiTranId;
        this.apiTranDtm = apiTranDtm;
        this.rspCode = rspCode;
        this.rspMessage = rspMessage;
        this.bankTranId = bankTranId;

        this.bankTranDate = bankTranDate;
        this.bankCodeTran = bankCodeTran;
        this.bankRspCode = bankRspCode;
        this.bankRspMessage = bankRspMessage;
        this.bankCodeStd = bankCodeStd;
        this.bankCodeSub = bankCodeSub;
        this.bankName = bankName;
        this.accountNm = accountNm;
        this.accountHolderInfoType = accountHolderInfoType;
        this.accountHolderInfo = accountHolderInfo;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
    }


}
