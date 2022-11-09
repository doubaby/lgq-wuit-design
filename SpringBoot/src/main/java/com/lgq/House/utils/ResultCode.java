package com.lgq.House.utils;

public interface ResultCode {
    /**
     * 成功状态码
     */
    Integer SUCCESS = 20000;

    /**
     * 失败状态码
     */
    Integer ERROR = 20001;

    /**
     * 成功信息
     */
    String SuccessMsg = "请求成功";

    /**
     * 失败信息
     */
    String ErrorMsg = "请求失败";
}
