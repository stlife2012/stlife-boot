package org.stlife.commons.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.stlife.commons.web.HttpCode;
import org.stlife.commons.web.Result;
import org.stlife.commons.web.ResultCode;
import org.stlife.commons.web.ResultUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * GlobalExceptionAdvice
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-09-23 14:02
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {
    /**
     * 处理未知异常
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Result> handleHttpException(HttpServletRequest req, Exception ex){
        log.error(ex.getMessage());
        String uri = req.getRequestURI();
        String method = req.getMethod();

        //设置header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpStatus status = HttpStatus.resolve(HttpCode.SYSTEM_ERROR);
        Result resultData = ResultUtil.error(ResultCode.ERROR,ex.getMessage(),method + " " + uri);
        //返回统一的数据格式
        ResponseEntity<Result> result = new ResponseEntity<>(resultData,headers,status);
        return result;
    }

    /**
     * 处理已知异常
     */
    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<Result> handleHttpException(HttpServletRequest req, HttpException ex){
        log.error(ex.getMessage());
        String uri = req.getRequestURI();
        String method = req.getMethod();

        //设置header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpStatus status = HttpStatus.resolve(ex.getCode());
        Result resultData = ResultUtil.error(ex.getCode(),ex.getMessage(),method + " " + uri);
        //返回统一的数据格式
        ResponseEntity<Result> result = new ResponseEntity<>(resultData,headers,status);

        return result;
    }
}
