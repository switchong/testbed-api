package com.testbed.core.domain.testbed;

import com.testbed.core.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "api_log")
public class ApiLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long apiSeq;

    @Column(name = "uri_id", length=50)
    private String uriId;

    @Column(name = "uri_path", length=100)
    private String uriPath;

    @Column(name = "method", length=10)
    private String method;

    @Column(name = "request", length=3000)
    private String request;

    @Column(name = "response", length=3000)
    private String response;

    @Column(name = "rsp_code", length=5)
    private String rspCode;

    @Column(name = "rsp_message", length=500)
    private String rspMessage;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Builder
    public ApiLog(String uriId, String uriPath, String method, String request, String response, String rspCode, String rspMessage) {
        this.uriId = uriId;
        this.uriPath = uriPath;
        this.method = method;
        this.request = request;
        this.response = response;
        this.rspCode = rspCode;
        this.rspMessage = rspMessage;
    }
}
