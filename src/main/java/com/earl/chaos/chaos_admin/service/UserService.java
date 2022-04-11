package com.earl.chaos.chaos_admin.service;

import com.earl.chaos.chaos_admin.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.earl.chaos.chaos_admin.dto.UserDto;

/**
* @author earl
* @description 针对表【user】的数据库操作Service
* @createDate 2022-02-23 22:02:01
*/
public interface UserService extends IService<User> {

    void register(UserDto user);

    boolean login(UserDto user);

    User findByWeixinId(String weixinId);

    boolean clearGroupId(Integer userId);
}
