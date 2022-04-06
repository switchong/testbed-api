package com.nftgram.api.common.exception;

public class CLoginFailedException extends RuntimeException{

    public CLoginFailedException () {
        super();
    }

    public CLoginFailedException(String message) {
        super(message);
    }

    public CLoginFailedException(String message , Throwable cause) {
        super(message , cause);
    }
}
