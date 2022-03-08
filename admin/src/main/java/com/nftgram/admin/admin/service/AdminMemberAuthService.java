package com.nftgram.admin.admin.service;

import com.nftgram.core.domain.admin.AdminMember;
import com.nftgram.core.repository.AdminMemberRepository;
import com.nftgram.core.repository.NftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
public class AdminMemberAuthService {

    private final AdminMemberRepository adminMemberRepository;

    /**
     * 관리자 정보 가져오기
     * @param adminId
     * @return
     */
    @Transactional(readOnly = true)
    public AdminMember getAdminMeber(String adminId){
        AdminMember adminMember = adminMemberRepository.findByAdminId(adminId);
        return adminMember;
    }

    /**
     * 관리자 등록
     * @param adminId
     * @param adminName
     * @param password
     * @return
     */
    @Transactional
    public Long addAdminMember(String adminId, String adminName, String password , String salt ) {
        AdminMember adminMember = new AdminMember(adminId, adminName, password ,salt);
        adminMemberRepository.save(adminMember);
        return adminMember.getAId();
    }
}
