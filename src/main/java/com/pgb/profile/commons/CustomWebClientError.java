package com.pgb.profile.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.nio.charset.Charset;

public class CustomWebClientError extends HttpServerErrorException {

    public CustomWebClientError(HttpStatus statusCode) {
        super(statusCode);
    }

    public CustomWebClientError(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public CustomWebClientError(HttpStatus statusCode, String statusText, byte[] body, Charset charset) {
        super(statusCode, statusText, body, charset);
    }

    public CustomWebClientError(HttpStatus statusCode, String statusText, HttpHeaders headers, byte[] body, Charset charset) {
        super(statusCode, statusText, headers, body, charset);
    }

    public CustomWebClientError(String message, HttpStatus statusCode, String statusText, HttpHeaders headers, byte[] body, Charset charset) {
        super(message, statusCode, statusText, headers, body, charset);
    }
}
