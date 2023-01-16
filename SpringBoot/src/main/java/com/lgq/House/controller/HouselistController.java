package com.lgq.House.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgq.House.entity.Dto.UserDto;
import com.lgq.House.entity.Houselist;
import com.lgq.House.entity.Vo.HouseListVo;
import com.lgq.House.service.HouselistService;
import com.lgq.House.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lgq.House.config.RedisConst.LOGIN_INFO;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
@SuppressWarnings("all")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/House/houselist")
@Api(tags = "房屋管理")
public class HouselistController {

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    UserController userController;

    @Resource
    private HouselistService houselistService;

    @ApiOperation(value = "查询热门房屋列表")
    @PostMapping("/getHotHouseQuery/{page}/{size}")
    @ApiImplicitParam(dataType = "HouseList", name = "houselist", value = "房屋列表", required = false)
    public ResultVo selectAll(@RequestBody(required = false) HouseListVo houseListVo,
                              @PathVariable("page") Long current,
                              @PathVariable("size") Long size
    ) {
        Map<String, Object> Houselist = houselistService.selectHotList(current, size,houseListVo);
        return ResultVo.SUCCESS(Houselist);
    }

    @ApiOperation("根据ID查询房屋详情")
    @PostMapping("/details/{houseId}")
    public ResultVo houseDetails(@PathVariable("houseId") Integer houseId) {
        Houselist houseDetails = houselistService.getById(houseId);
        return ResultVo.SUCCESS(houseDetails);
    }

    @GetMapping("/list/whole")
    public ResultVo houseListWhole(HttpServletRequest request) {
        List<Houselist> list = houselistService.selectByWhole();
        return ResultVo.SUCCESS(list);
    }

    @GetMapping("/list/together")
    public ResultVo houseListTogethter() {
        List<Houselist> list = houselistService.selectBytypeTogether();
        return ResultVo.SUCCESS(list);
    }

    @ApiOperation(value = "整租分页")
    @GetMapping("/whole/pages/{page}/{size}")
    public ResultVo WholePage(@PathVariable("page") Long page,
                              @PathVariable("size") Long size) {
        LambdaQueryWrapper<Houselist> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Houselist::getType, 0);
        Page<Houselist> pageWhole = new Page<>(page, size);
        houselistService.page(pageWhole, wrapper);
        List<Houselist> records = pageWhole.getRecords();
        long total = pageWhole.getTotal();
        HashMap<String, Object> map = new HashMap<>(12);
        map.put("records", records);
        map.put("total", total);
        return ResultVo.SUCCESS(map);
    }

    @ApiOperation(value = "合租分页")
    @GetMapping("/together/pages/{page}/{size}")
    public ResultVo togetherPage(@PathVariable("page") Long page,
                                 @PathVariable("size") Long size) {
        LambdaQueryWrapper<Houselist> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Houselist::getType, 1);
        Page<Houselist> pageWhole = new Page<>(page, size);
        houselistService.page(pageWhole, wrapper);
        List<Houselist> records = pageWhole.getRecords();
        long total = pageWhole.getTotal();
        HashMap<String, Object> map = new HashMap<>(12);
        map.put("records", records);
        map.put("total", total);
        return ResultVo.SUCCESS(map);
    }


}

