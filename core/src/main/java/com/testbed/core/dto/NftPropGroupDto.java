package com.testbed.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NftPropGroupDto {
    private String traitType;

    private String traitValue;

    private Long traitCount;

}
