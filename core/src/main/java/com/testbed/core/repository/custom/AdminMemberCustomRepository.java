package com.testbed.core.repository.custom;

import com.testbed.core.domain.admin.AdminMember;


public interface AdminMemberCustomRepository {

    AdminMember findByAdminId(String adminId);





}
