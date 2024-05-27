package com.testbed.web.oob.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 수취조회 - /v2.0/inquiry/receive;
 api_tran_id - 거래고유번호(API)
 api_tran_dtm - 거래일시(밀리세컨드)
 rsp_code - 응답코드(API)
 rsp_message - 응답메시지(API)
 bank_code_std - 입금기관.표준코드
 bank_code_sub - 입금기관.점별코드
 bank_name - 입금기관명
 savings_bank_name - 개별저축은행명
 account_num - 입금계좌번호
 account_seq - 회차번호
 account_num_masked - 입금계좌번호(출력용)
 print_content - 입금계좌인자내역
 account_holder_name - 수취인성명
 bank_tran_id - 거래고유번호(참가은행)
 bank_tran_date - 거래일자(참가은행)
 bank_code_tran- 응답코드를 부여한 참가은행.표준코드
 bank_rsp_code - 응답코드(참가은행)
 bank_rsp_message - 응답메시지(참가은행)
 wd_bank_code_std - 출금(개설)기관.표준코드
 wd_bank_name - 출금(개설)기관명
 wd_account_num - 출금계좌번호
 tran_amt - 거래금액
 cms_num - CMS 번호
 */
@Getter
@NoArgsConstructor
public class InquiryReceiveWebResponse {

    private String userId;
    private String apiTranId;
    private String apiTranDtm;
    private String rspCode;
    private String rspMessage;
    private String bankCodeStd;
    private String bankCodeSub;
    private String bankName;
    private String savingsBankName;
    private String accountNum;
    private String accountSeq;
    private String accountNumMasked;
    private String printContent;
    private String accountHolderName;
    private String bankTranId;
    private String bankTranDate;
    private String bankCodeTran;
    private String bankRspCode;
    private String bankRspMessage;
    private String wdBankCodeStd;
    private String wdBankName;
    private String wdAccountNum;
    private String tranAmt;
    private String cmsNum;

    @Builder
    public InquiryReceiveWebResponse(String userId, String apiTranId, String apiTranDtm, String rspCode, String rspMessage, String bankCodeStd, String bankCodeSub, String bankName, String savingsBankName, String accountNum, String accountSeq, String accountNumMasked, String printContent, String accountHolderName, String bankTranId, String bankTranDate, String bankCodeTran, String bankRspCode, String bankRspMessage, String wdBankCodeStd, String wdBankName, String wdAccountNum, String tranAmt, String cmsNum) {
        this.userId = userId;
        this.apiTranId = apiTranId;
        this.apiTranDtm = apiTranDtm;
        this.rspCode = rspCode;
        this.rspMessage = rspMessage;
        this.bankCodeStd = bankCodeStd;
        this.bankCodeSub = bankCodeSub;
        this.bankName = bankName;
        this.savingsBankName = savingsBankName;
        this.accountNum = accountNum;
        this.accountSeq = accountSeq;
        this.accountNumMasked = accountNumMasked;
        this.printContent = printContent;
        this.accountHolderName = accountHolderName;
        this.bankTranId = bankTranId;
        this.bankTranDate = bankTranDate;
        this.bankCodeTran = bankCodeTran;
        this.bankRspCode = bankRspCode;
        this.bankRspMessage = bankRspMessage;
        this.wdBankCodeStd = wdBankCodeStd;
        this.wdBankName = wdBankName;
        this.wdAccountNum = wdAccountNum;
        this.tranAmt = tranAmt;
        this.cmsNum = cmsNum;
    }
}

