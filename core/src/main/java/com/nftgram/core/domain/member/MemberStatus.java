package com.nftgram.core.domain.member;




public enum MemberStatus {


    ACTIVE("활동중"),

    SUSPEND("일시정지"),

    REMOVE("탙퇴");


    private final String typeName;

    MemberStatus(String typeName){
        this.typeName = typeName;
    }

    private String getTypeName(){
        return typeName;
    }
}
