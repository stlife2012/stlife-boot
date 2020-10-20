package org.stlife.commons.web;

/**
 * ResultUtil
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-09-23 15:31
 **/
public class ResultUtil {

    public static Result success(Object object){
        Result result = new Result(ResultCode.SUCCESS,"success",object);
        return result;
    }

    public static Result success(){
        return success(null);
    }


    public static Result error(int code,String message){
        Result result = new Result(ResultCode.ERROR,message,null);
        return result;
    }

    public static Result error(int code,String message,Object object){
        Result result = new Result(ResultCode.ERROR,message,object);
        return result;
    }
}
