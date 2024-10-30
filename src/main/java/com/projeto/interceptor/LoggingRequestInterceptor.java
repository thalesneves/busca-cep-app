package com.projeto.interceptor;

import com.projeto.domain.entity.HttpLog;
import com.projeto.repository.HttpLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    private final HttpLogRepository httpLogRepository;

    @Autowired
    public LoggingRequestInterceptor(HttpLogRepository httpLogRepository) {
        this.httpLogRepository = httpLogRepository;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);

        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);

        HttpLog httpLog = buildHttpLog(request, body, response);
        saveHttpLog(httpLog);

        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        logger.info(">> URI: {}", request.getURI());
        logger.info(">> Method: {}", request.getMethod());
        logger.info(">> Headers: {}", request.getHeaders());
        logger.info(">> Body: {}", new String(body, StandardCharsets.UTF_8));
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        byte[] responseBody = StreamUtils.copyToByteArray(response.getBody());

        logger.info("<< Status code: {}", response.getStatusCode());
        logger.info("<< Status text: {}", response.getStatusText());
        logger.info("<< Headers: {}", response.getHeaders());
        logger.info("<< Response Body: {}", new String(responseBody, StandardCharsets.UTF_8));
    }

    private void saveHttpLog(HttpLog httpLog) {
        httpLogRepository.save(httpLog);
    }

    private HttpLog buildHttpLog(HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
        HttpLog httpLog = new HttpLog();
        httpLog.setUri(request.getURI().toString());
        httpLog.setMethod(request.getMethod().toString());
        httpLog.setRequestHeaders(request.getHeaders().toString());
        httpLog.setRequestBody(new String(body, StandardCharsets.UTF_8));
        httpLog.setStatusCode(response.getStatusCode().toString());
        httpLog.setStatusText(response.getStatusText());
        httpLog.setResponseHeaders(response.getHeaders().toString());
        httpLog.setResponseBody(response.getBody().toString());
        httpLog.setTimestamp(LocalDateTime.now().toString());

        return httpLog;
    }

}
