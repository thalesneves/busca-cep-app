package com.projeto.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "logs")
public class HttpLog {

    @Id
    private String id;
    private String uri;
    private String method;
    private String requestHeaders;
    private String requestBody;
    private String statusCode;
    private String statusText;
    private String responseHeaders;
    private String responseBody;
    private String timestamp;

}