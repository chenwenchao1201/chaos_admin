package com.earl.chaos.chaos_admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earl.chaos.chaos_admin.domain.User;
import com.earl.chaos.chaos_admin.dto.UserDto;
import com.earl.chaos.chaos_admin.service.UserService;
import com.earl.chaos.chaos_admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author earl
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-02-23 22:02:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public void register(UserDto user) {
        userMapper.insert(BeanUtil.toBean(user, User.class));
    }

    @Override
    public boolean login(UserDto user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("weixin_id",user.getWeixinId());
        User one = getOne(wrapper);
        if (one != null) {
            return true;
        }else{
            return false;
        }
    }
}




