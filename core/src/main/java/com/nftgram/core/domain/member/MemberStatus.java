package com.nftgram.core.domain.member;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {


    ACTIVE("ACTIVE","활동중"),

    SUSPEND("SUSPEND","일시정지"),

    REMOVE("REMOVE" ,"삭제");


//    private final String typeName;
//
//
//    MemberStatus(String typeName){
//        this.typeName = typeName;
//    }
//
//    private String getTypeName(){
//        return typeName;
//    }
    private final String key;
    private final String title;
}
