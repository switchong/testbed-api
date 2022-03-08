package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.admin.AdminMember;


public interface AdminMemberCustomRepository {

    AdminMember findByAdminId(String adminId);





}
