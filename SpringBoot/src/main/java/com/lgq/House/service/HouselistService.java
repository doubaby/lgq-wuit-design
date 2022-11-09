package com.lgq.House.service;

import com.lgq.House.entity.Houselist;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
public interface HouselistService extends IService<Houselist> {

    Map<String,Object> selectHotList(Houselist houselist, Long current, Long size);
}
