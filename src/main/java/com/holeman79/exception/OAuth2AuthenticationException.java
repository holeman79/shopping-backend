package com.holeman79.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class OAuth2AuthenticationException extends AuthenticationException {
    private ErrorCode errorCode;

    public OAuth2AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OAuth2AuthenticationException(String message) {
        super(message);
    }

    public OAuth2AuthenticationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
