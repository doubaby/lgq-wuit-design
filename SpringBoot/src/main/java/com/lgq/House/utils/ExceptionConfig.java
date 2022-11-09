package com.lgq.House.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LGQ
 */
@Slf4j
@ControllerAdvice
public class ExceptionConfig {
    @ResponseBody
    @ExceptionHandler(HouseException.class)
    public static ResultVo ERROR(HouseException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResultVo.ERROR(e.getCode(), e.getMessage());
    }
}
