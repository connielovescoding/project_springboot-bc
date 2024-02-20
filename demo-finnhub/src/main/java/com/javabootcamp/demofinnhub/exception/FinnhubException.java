package com.javabootcamp.demofinnhub.exception;

import com.javabootcamp.demofinnhub.infra.enums.Code;

public class FinnhubException extends BusinessException {
    
    public FinnhubException(Code code) {
        super(code);
    }
}
