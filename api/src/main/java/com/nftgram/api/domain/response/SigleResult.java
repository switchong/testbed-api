package com.nftgram.api.domain.response;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class SigleResult<T> extends  CommonResult {

    private T data;
}
