package com.lgq.House.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgq.House.entity.Dto.UserDto;
import com.lgq.House.entity.Houselist;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lgq.House.entity.Vo.HouseListVo;
import io.swagger.models.auth.In;

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

    Map<String,Object> selectHotList(Long page, Long size, HouseListVo houseListVo);

    Map<String, Object> Pagelist(Page<Houselist> pageQuery);

    List<Houselist> selectByWhole();

    List<Houselist> selectBytypeTogether();

}
