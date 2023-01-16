package com.lgq.House.mapper;

import com.lgq.House.entity.Houselist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
public interface HouselistMapper extends BaseMapper<Houselist> {

    List<Houselist> selectBytypeWhole();

    List<Houselist> selectBytypeTogether();
}
