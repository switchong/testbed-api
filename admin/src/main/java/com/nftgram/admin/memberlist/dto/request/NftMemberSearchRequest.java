package com.nftgram.admin.memberlist.dto.request;


import com.nftgram.admin.common.util.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NftMemberSearchRequest extends PageRequest {


    private Long nftMemberId;
    private String username;
    private String nftMemberUserId;

}
