package com.nftgram.api.common.exception;

public class CExpiredAccessTokenException extends  RuntimeException{

    public CExpiredAccessTokenException() {
        super();
    }

    public CExpiredAccessTokenException(String message) {
        super(message);
    }

    public CExpiredAccessTokenException(String message , Throwable cause) {
        super(message , cause);
    }
}
