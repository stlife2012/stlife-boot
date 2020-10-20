package org.stlife.commons.exception;

import lombok.Data;
import org.stlife.commons.web.HttpCode;

/**
 * HttpException
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-09-23 15:16
 **/
@Data
public class HttpException extends RuntimeException {
    private int code;

    public HttpException(String message){
        super(message);
        this.code = HttpCode.SYSTEM_ERROR;
    }

    public HttpException(int code,String message){
        super(message);
        this.code = code;
    }
}
