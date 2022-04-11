package com.earl.chaos.chaos_admin.service;

import com.earl.chaos.chaos_admin.domain.Group;
import com.baomidou.mybatisplus.extension.service.IService;
import com.earl.chaos.chaos_admin.domain.User;

import java.util.List;

/**
* @author earl
* @description 针对表【group】的数据库操作Service
* @createDate 2022-02-23 22:02:01
*/
public interface GroupService extends IService<Group> {

    Group getByCode(String code);

    List<User> findUsersById(Integer id);
}
