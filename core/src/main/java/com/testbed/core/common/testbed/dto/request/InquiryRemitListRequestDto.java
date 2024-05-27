package com.testbed.core.common.testbed.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 송금인정보조회 - /v2.0/inquiry/remit_list;
 * bank_tran_id - 은행거래고유번호주
 * bank_code_std - 조회하고자 하는 이용기관수취계좌의 개설기관.표준코드
 * account_num - 조회하고자 하는 이용기관수취계좌의 계좌번호
 * from_date - 조회시작일자
 * from_time - 조회시작시간
 * to_date - 조회종료일자
 * to_time - 조회종료시간
 * sort_order - 정렬순서 ( D:Descending, A:Ascending )
 * befor_inquiry_trace_info - 직전조회추적정보주 ( N )
 * tran_dtime - 요청일시
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InquiryRemitListRequestDto {

    private static InquiryRemitListRequestDto inquiryRemitListRequestDto;

    @JsonProperty("bank_tran_id")
    private String bankTranId;
    @JsonProperty("bank_code_std")
    private String bankCodeStd;
    @JsonProperty("account_num")
    private String accountNum;
    @JsonProperty("from_date")
    private String fromDate;
    @JsonProperty("from_time")
    private String fromTime;
    @JsonProperty("to_date")
    private String toDate;
    @JsonProperty("to_time")
    private String toTime;
    @JsonProperty("sort_order")
    private String sortOrder;
    @JsonProperty("befor_inquiry_trace_info")
    private String beforInquiryTraceInfo;
    @JsonProperty("tran_dtime")
    private String tranDtime;

    public InquiryRemitListRequestDto(String bankTranId, String bankCodeStd, String accountNum, String fromDate, String fromTime
            , String toDate, String toTime, String sortOrder, String beforInquiryTraceInfo, String tranDtime) {
        this.bankTranId = bankTranId;
        this.bankCodeStd = bankCodeStd;
        this.accountNum = accountNum;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
        this.sortOrder = sortOrder;
        this.beforInquiryTraceInfo = beforInquiryTraceInfo;
        this.tranDtime = tranDtime;
    }

    public static InquiryRemitListRequestDto of (String bankTranId, String bankCodeStd, String accountNum, String fromDate, String fromTime
            , String toDate, String toTime, String sortOrder, String beforInquiryTraceInfo, String tranDtime) {
        return new InquiryRemitListRequestDto(bankTranId, bankCodeStd, accountNum, fromDate, fromTime, toDate, toTime, sortOrder, beforInquiryTraceInfo, tranDtime);
    }
}

