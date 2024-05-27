package com.testbed.web.account.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class BalanceRequest {

    private String userId;
    private String bankTranId;
    private String fintechUseNum;
    private String tranDtime;

}
