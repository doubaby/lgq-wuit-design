package com.lgq.House.controller;


import com.lgq.House.entity.Houselist;
import com.lgq.House.service.HouselistService;
import com.lgq.House.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
@CrossOrigin
@RestController
@RequestMapping("/House/houselist")
@Api(tags = "房屋管理")
public class HouselistController {

    @Resource
    private HouselistService houselistService;

    @ApiOperation(value = "查询热门房屋列表")
    @PostMapping("/getHotHouse/{current}/{size}")
    @ApiImplicitParam(dataType = "HouseList", name = "houselist", value = "房屋列表", required = false)
    public ResultVo selectAll(@RequestBody(required = false) Houselist houselist,
                              @PathVariable("current") Long current,
                              @PathVariable("size") Long size
    ) {
        Map<String, Object> Houselist = houselistService.selectHotList(houselist, current, size);
        return ResultVo.SUCCESS(Houselist);
    }

    @ApiOperation(value = "全部房屋列表")
    @GetMapping("/allHouse")
    public ResultVo HouseList() {
        List<Houselist> houselists = houselistService.list();
        return ResultVo.SUCCESS(houselists);
    }

}

