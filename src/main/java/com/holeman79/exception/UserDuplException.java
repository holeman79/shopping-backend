package com.holeman79.exception;

public class UserDuplException extends BusinessException {

    public UserDuplException(String message) {
        super(message, ErrorCode.USER_ID_DUPLICATION);
    }
}