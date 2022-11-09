package com.lgq.House.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgq.House.entity.Houselist;
import com.lgq.House.mapper.HouselistMapper;
import com.lgq.House.service.HouselistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    @Override
    public Map<String, Object> selectHotList(Houselist houselist, Long current, Long size) {
        Page<Houselist> page = new Page<>(current, size);
        QueryWrapper<Houselist> queryWrapper = new QueryWrapper<>();
        //如果地址不为空，则按照地址迷糊查询
        if (!StringUtils.isEmpty(houselist.getAddress())) {
            queryWrapper.like("address", houselist.getAddress());
        }
        //如果状态码不为空，按照状态码查询
        if (!StringUtils.isEmpty(houselist.getStatus())) {
            queryWrapper.eq("status", houselist.getStatus());
        }
        String gmtCreate = String.valueOf(houselist.getGmtCreate());
        queryWrapper.orderByDesc("gmt_create",gmtCreate);
        this.page(page, queryWrapper);
        long total = page.getTotal();
        List<Houselist> records = page.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("total", total);
        return map;
    }
}
