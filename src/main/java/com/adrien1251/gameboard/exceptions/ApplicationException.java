package com.adrien1251.gameboard.exceptions;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;


@Log4j2
@Data
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private HttpStatus errCode;
    private String errMsg;

    public ApplicationException(HttpStatus errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
        log.warn(errMsg);
    }
}
