package com.nftgram.web.common.auth;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        JsonFactory factory = new JsonFactory();
        StringWriter jsonObjectWriter = new StringWriter();

        try {
            JsonGenerator generator = factory.createGenerator(jsonObjectWriter);
            generator.writeStartObject();
            generator.writeStringField("loginYN", "Y");
            generator.writeStringField("nftMemberId", Long.toString(nftMemberId));
            generator.writeStringField("nftMemberUserId", nftMemberUserId);
            generator.writeEndObject();
            generator.close();

            String encodeLoginData = aes256Converter.encode(jsonObjectWriter.toString());

            return encodeLoginData;
        } catch ( GeneralSecurityException | IOException e) {
            throw new IllegalStateException("로그인 정보 쿠키 생성 실패");
        }
    }

    public NftMemberAuthDto getTokenToMember(String token) throws GeneralSecurityException, UnsupportedEncodingException {
        NftMemberAuthDto bizMemberAuthDto = memberTokenToDto(token);
        return bizMemberAuthDto;
    }

    private NftMemberAuthDto memberTokenToDto(String token) throws GeneralSecurityException, UnsupportedEncodingException {
        try {
            String jsonAdminMember = aes256Converter.decode(token);
            ObjectMapper mapper = new ObjectMapper();
            NftMemberAuthDto nftMemberAuthDto = mapper.readValue(jsonAdminMember, NftMemberAuthDto.class);

            return nftMemberAuthDto;
        } catch (JsonProcessingException | GeneralSecurityException | UnsupportedEncodingException e ) {
            throw new IllegalStateException("로그인 정보 읽기 실패");
        }
    }

}
