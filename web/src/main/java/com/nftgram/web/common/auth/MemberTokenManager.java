package com.nftgram.web.common.auth;

import com.nftgram.web.config.security.AES256Converter;
import com.nftgram.web.member.dto.NftMemberAuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.security.GeneralSecurityException;

import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;

@Log
@Component
@RequiredArgsConstructor
public class MemberTokenManager {

    private final AES256Converter aes256Converter;

    public String makeToken(Long nftMemberId, String nftMemberUserId) throws GeneralSecurityException, UnsupportedEncodingException {

        NftMemberAuthDto nftMemberAuthDto = NftMemberAuthDto.builder()
                                                        .nftMemberId(nftMemberId)
                                                        .nftMemberUserId(nftMemberUserId)
                                                        .build();

        // 객체 직렬화
        byte[] serializedMember = new byte[0];
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(nftMemberAuthDto);
                serializedMember = baos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return aes256Converter.encode(getEncoder().encodeToString(serializedMember));
    }

    @Transactional(transactionManager = "bizMemberTransactionManager", readOnly = true)
    public NftMemberAuthDto getTokenToMember(String token) throws GeneralSecurityException, UnsupportedEncodingException {
        NftMemberAuthDto bizMemberAuthDto = memberTokenToDto(token);

        return bizMemberAuthDto;
    }

    private NftMemberAuthDto memberTokenToDto(String token) throws GeneralSecurityException, UnsupportedEncodingException {
        String decodeToken = aes256Converter.decode(token);

        NftMemberAuthDto nftMemberAuthDto = null;
        byte[] derializedMember = getDecoder().decode(decodeToken);
        try (ByteArrayInputStream bais = new ByteArrayInputStream(derializedMember)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                Object objectMember = ois.readObject();
                nftMemberAuthDto = (NftMemberAuthDto) objectMember;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return nftMemberAuthDto;
    }

}
