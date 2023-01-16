package com.lgq.House.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgq.House.entity.Dto.UserDto;
import com.lgq.House.entity.Houselist;
import com.lgq.House.entity.User;
import com.lgq.House.entity.Vo.HouseListVo;
import com.lgq.House.mapper.HouselistMapper;
import com.lgq.House.service.HouselistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
@Service
public class HouselistServiceImpl extends ServiceImpl<HouselistMapper, Houselist> implements HouselistService {

    @Resource
    private HouselistMapper houselistMapper;

    @Override
    public Map<String, Object> selectHotList( Long page, Long size,HouseListVo houseListVo) {
        Page<Houselist> pageList = new Page<>(page, size);
        QueryWrapper<Houselist> queryWrapper = new QueryWrapper<>();
        //如果地址不为空，则按照地址迷糊查询
        if (!StringUtils.isEmpty(houseListVo.getAddress())) {
            queryWrapper.like("address", houseListVo.getAddress());
        }
        //如果状态不为空，则按照状态查询
        if (!StringUtils.isEmpty(houseListVo.getCity())) {
            queryWrapper.eq("city", houseListVo.getCity());
        }
        //如果价格不为空，则按照价格查询
        if (!StringUtils.isEmpty(houseListVo.getPrice())) {
            queryWrapper.ge("price", houseListVo.getPrice());
        }
        queryWrapper.orderByDesc("gmt_create");
        this.page(pageList, queryWrapper);
        long total = pageList.getTotal();
        List<Houselist> records = pageList.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("total", total);
        return map;
    }

    /**
     * 分页查询
    */
    @Override
    public Map<String, Object> Pagelist(Page<Houselist> pageQuery) {
        Page<Houselist> page = this.page(pageQuery);
        HashMap<String, Object> map = new HashMap<>();
        List<Houselist> records = page.getRecords();
        long total = page.getTotal();
        map.put("total",total);
        map.put("records",records);
        return map;
    }

    @Override
    public List<Houselist> selectByWhole() {
        return houselistMapper.selectBytypeWhole() ;
    }

    @Override
    public List<Houselist> selectBytypeTogether() {
        return houselistMapper.selectBytypeTogether();
    }

}
