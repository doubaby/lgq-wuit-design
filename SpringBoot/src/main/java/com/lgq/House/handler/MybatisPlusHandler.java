package com.lgq.House.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author LGQ
 * @createTime 2022/8/17 13:47
 * @description 自动填充日期时间
 */
@Component
public class MybatisPlusHandler implements MetaObjectHandler
    {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject)
    {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}
