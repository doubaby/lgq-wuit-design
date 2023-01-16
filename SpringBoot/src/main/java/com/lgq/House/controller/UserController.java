package com.lgq.House.controller;


import cn.hutool.core.bean.BeanUtil;
import com.lgq.House.entity.Dto.UserDto;
import com.lgq.House.entity.User;
import com.lgq.House.service.UserService;
import com.lgq.House.utils.HouseException;
import com.lgq.House.utils.JwtUtils;
import com.lgq.House.utils.ResultVo;
import com.lgq.House.utils.UserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
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
@Api(tags = "用户管理")
@RestController
@CrossOrigin
@RequestMapping("/House/user")
public class UserController {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserService userService;

    /**
     * 登录接口
     *
     * @param userDto
     * @return
     */

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResultVo login(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new HouseException("用户名或密码不能为空", 401);
        }
        String token = userService.login(userDto);
        return ResultVo.SUCCESS(token);
    }

    /**
     * 注册接口
     *
     * @param userDto
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResultVo register(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultVo.ERROR(401, "用户名或密码不能为空");
        }
        userService.register(userDto);
        return ResultVo.SUCCESS();
    }

    @GetMapping("/me/{token}")
    @ApiOperation("用户信息")
    public ResultVo me(@PathVariable("token") String token) {
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(LOGIN_INFO + token);
        UserDto userDto = BeanUtil.fillBeanWithMap(map, new UserDto(), false);
//        UserDto user = UserHolder.getUser();
//        HashMap<Object, Object> hashMap = new HashMap<>();
//        hashMap.put("userDto",userDto);
//        hashMap.put("user",user);
        return ResultVo.SUCCESS(userDto);
    }

    @GetMapping("/userInfo/{id}")
    @ApiOperation("用户信息")
    public ResultVo userInfo(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        return ResultVo.SUCCESS(user);
    }

    @PostMapping("/userInfoEdit")
    @ApiOperation("用户信息")
    public ResultVo userInfoEdit(@RequestBody User userDto) {
        userService.saveOrUpdate(userDto);
        return ResultVo.SUCCESS();
    }
}

