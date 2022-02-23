package com.earl.chaos.chaos_admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earl.chaos.chaos_admin.domain.User;
import com.earl.chaos.chaos_admin.service.UserService;
import com.earl.chaos.chaos_admin.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author earl
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-02-23 22:02:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




