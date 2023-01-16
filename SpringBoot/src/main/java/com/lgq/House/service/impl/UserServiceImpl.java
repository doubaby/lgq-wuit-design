package com.lgq.House.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lgq.House.entity.Dto.UserDto;
import com.lgq.House.entity.Houselist;
import com.lgq.House.entity.User;
import com.lgq.House.mapper.UserMapper;
import com.lgq.House.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgq.House.utils.HouseException;
import com.lgq.House.utils.JwtUtils;
import com.lgq.House.utils.ResultVo;
import com.lgq.House.utils.UserHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.lgq.House.config.RedisConst.LOGIN_INFO;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登陆接口
     *
     * @param userDto
     * @return
     */
    @Override
    public String login(UserDto userDto) {

        String username = userDto.getUsername();
        String password = userDto.getPassword();
        QueryWrapper<User> query = new QueryWrapper<>();
        //查询用户名是否一致
        query.eq("username", username);
        User user = baseMapper.selectOne(query);
        if (user == null) {
            throw new HouseException("该用户不存在", 401);
        }
        String DbPassword = user.getPassword();
        //查询密码是否一致
        if (!password.equals(DbPassword)) {
            throw new HouseException("密码错误", 401);
        }
        //将用户信息存放在redis中
        String jwtToken = JwtUtils.getJwtToken(user.getId().toString(), user.getNickname());

        HashMap<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("avatar", user.getAvatar());
        map.put("nickname", user.getNickname());
        stringRedisTemplate.opsForHash().putAll(LOGIN_INFO + jwtToken, map);
        stringRedisTemplate.expire(LOGIN_INFO + jwtToken, 30L, TimeUnit.MINUTES);
//        返回token
        BeanUtils.copyProperties(user, userDto);
        UserHolder.saveUser(userDto);
        return jwtToken;
    }

    /**
     * 注册接口
     *
     * @param userDto
     * @return
     */
    @Override
    public void register(UserDto userDto) {
        String username = userDto.getUsername();
        //判断输入的用户名是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new HouseException("用户名已存在", 401);
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        //存入数据库
        baseMapper.insert(user);
    }
}
