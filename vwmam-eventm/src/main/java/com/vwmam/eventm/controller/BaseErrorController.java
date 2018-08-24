package com.vwmam.eventm.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vwmam.eventm.result.JsonResult;
import com.vwmam.eventm.result.ResultModel;

/**
 * 异常处理
 * Created by jasonzhu on 2017/7/14.
 */
@ControllerAdvice
public class BaseErrorController{
	
	private final static Logger log = LoggerFactory.getLogger(BaseErrorController.class);

    /**
     * 未知异常或参数异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultModel exception(Exception exception){
        log.error("未预期异常",exception);
        if (exception.getClass() == BindException.class) {
            return JsonResult.error(((BindException) exception).getFieldError().getDefaultMessage());
        }
        if (exception.getClass() == MethodArgumentNotValidException.class){
            // 参数校验未通过
            return JsonResult.error(((MethodArgumentNotValidException) exception).getBindingResult().getFieldError().getDefaultMessage());
        }
        if(exception.getClass() == HttpMessageNotReadableException.class){
            return JsonResult.error("提交的参数异常，请检查后再提交");
        }
        if (exception.getClass() == SQLException.class){
            return JsonResult.error("提交的参数不合法，请检查后再提交");
        }

        if (exception.getClass() == IllegalArgumentException.class){
            return JsonResult.error(exception.getMessage());
        }
        return JsonResult.error("操作失败，请检查后再重试");
    }

}
