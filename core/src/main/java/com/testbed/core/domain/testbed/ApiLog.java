package com.testbed.core.domain.testbed;

import com.testbed.core.domain.common.BaseEntity;
import com.testbed.core.domain.testbed.value.Method;
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

    @Enumerated(value = EnumType.STRING)
    @Column(name = "method", length=10)
    private Method method;

    @Column(name = "state", length=32)
    private String state;

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

    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

    @Builder
    public ApiLog(String uriId, String uriPath, Method method, String state, String request, String response, String rspCode, String rspMessage, LocalDateTime createDate) {
        this.uriId = uriId;
        this.uriPath = uriPath;
        this.method = method;
        this.state = state;
        this.request = request;
        this.response = response;
        this.rspCode = rspCode;
        this.rspMessage = rspMessage;
        this.createDate = createDate;
    }
}
