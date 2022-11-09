package com.lgq.House.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import static com.lgq.House.utils.ResultCode.*;

/**
 * @author LGQ
 */
@Data
@Log4j
public class ResultVo {
    private String msg;
    private Object data;
    private Integer code;

    private ResultVo() {
    }

    @ApiModelProperty("成功返回data")
    public static ResultVo SUCCESS(Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.code = SUCCESS;
        resultVo.data = data;
        resultVo.msg = SuccessMsg;
        return resultVo;
    }

    /**
     * @param code
     * @param msg
     * @return 返回失败状态码和失败信息
     */
    public static ResultVo ERROR(Integer code,String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.code = ERROR;
        resultVo.msg = ErrorMsg;
        return resultVo;
    }

    /**
     * @param code
     * @return 只返回状态码
     */
    public static ResultVo ERROR(Integer code) {
        ResultVo resultVo = new ResultVo();
        resultVo.code = ERROR;
        return resultVo;
    }

}
