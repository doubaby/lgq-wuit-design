package com.lgq.House.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author LGQ
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseException extends RuntimeException {
    public String msg;
    public Integer code;

}
