package com.nftgram.admin.common.auth;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nftgram.admin.config.security.AES256Converter;
import com.nftgram.admin.admin.dto.AdminMemberAuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Log
@Component
@RequiredArgsConstructor
public class MemberTokenManager {

    private final AES256Converter aes256Converter;

    public String makeToken( String adminId) throws GeneralSecurityException, UnsupportedEncodingException {
        JsonFactory factory = new JsonFactory();
        StringWriter jsonObjectWriter = new StringWriter();

        try {
            JsonGenerator generator = factory.createGenerator(jsonObjectWriter);
            generator.writeStartObject();
            generator.writeStringField("loginYN", "Y");
            generator.writeStringField("adminId", adminId);
            generator.writeEndObject();
            generator.close();

            String encodeLoginData = aes256Converter.encode(jsonObjectWriter.toString());

            return encodeLoginData;
        } catch ( GeneralSecurityException | IOException e) {
            throw new IllegalStateException("로그인 정보 쿠키 생성 실패");
        }
    }

    public AdminMemberAuthDto getTokenToMember(String token) throws GeneralSecurityException, UnsupportedEncodingException {
        AdminMemberAuthDto nftMemberAuthDto = memberTokenToDto(token);
        return nftMemberAuthDto;
    }

    private AdminMemberAuthDto memberTokenToDto(String token) throws GeneralSecurityException, UnsupportedEncodingException {
        try {
            String jsonAdminMember = aes256Converter.decode(token);
            ObjectMapper mapper = new ObjectMapper();
            AdminMemberAuthDto nftMemberAuthDto = mapper.readValue(jsonAdminMember, AdminMemberAuthDto.class);

            return nftMemberAuthDto;
        } catch (JsonProcessingException | GeneralSecurityException | UnsupportedEncodingException e ) {
            throw new IllegalStateException("로그인 정보 읽기 실패");
        }
    }

}
