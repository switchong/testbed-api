package com.testbed.web.oob.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 송금인정보조회 - /v2.0/inquiry/remit_list;
 * api_tran_id - 거래고유번호(API)
 * api_tran_dtm - 거래일시(밀리세컨드)
 * rsp_code - 응답코드(API)
 * rsp_message - 응답메시지(API)
 * bank_tran_id - 거래고유번호(은행)
 * bank_tran_date - 거래일자(은행)
 * bank_code_tran - 응답코드를 부여한 은행표준코드
 * bank_rsp_code - 응답코드(은행)
 * bank_rsp_message - 응답메시지(은행)
 * bank_code_std - 조회된 이용기관수취계좌의 개설기관.표준코드
 * account_num - 조회된 이용기관수취계좌의 계좌번호
 * bank_name - 개설기관명
 * savings_bank_name - 개별저축은행명
 * balance_amt - 계좌잔액(-금액가능)
 * total_record_cnt - 총 조회건수
 * page_record_cnt - 현재페이지 레코드건수
 * next_page_yn - 다음페이지 존재여부
 * befor_inquiry_trace_info - 직전조회 추적정보
 * res_list<object> - 조회된 송금내역
 * --tran_date - 거래일자
 * --tran_time - 거래시간
 * --tran_type - 거래구분
 * --print_content - 통장인자내용
 * --tran_amt - 거래금액
 * --after_balance_amt - 거래 후 잔액(-금액가능)
 * --branch_name - 거래점명
 * --remitter_name - 송금인명
 * --remitter_bank_code - 송금한 계좌의 은행코드
 * --remitter_account_num - 송금한 계좌번호
 */
@Getter
@NoArgsConstructor
public class InquiryRemitListSubWebResponse {

    private String tranDate;
    private String tranTime;
    private String tranType;
    private String printContent;
    private String tranAmt;
    private String afterBalanceAmt;
    private String branchName;
    private String remitterName;
    private String remitterBankCode;
    private String remitterAccountNum;

    @Builder
    public InquiryRemitListSubWebResponse(String tranDate,String tranTime,String tranType,String printContent,String tranAmt,String afterBalanceAmt,String branchName,String remitterName,String remitterBankCode,String remitterAccountNum) {
        this.tranDate = tranDate;
        this.tranTime = tranTime;
        this.tranType = tranType;
        this.printContent = printContent;
        this.tranAmt = tranAmt;
        this.afterBalanceAmt = afterBalanceAmt;
        this.branchName = branchName;
        this.remitterName = remitterName;
        this.remitterBankCode = remitterBankCode;
        this.remitterAccountNum = remitterAccountNum;
    }
}

