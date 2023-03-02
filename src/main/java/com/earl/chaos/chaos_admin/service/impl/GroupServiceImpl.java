package com.earl.chaos.chaos_admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earl.chaos.chaos_admin.domain.Group;
import com.earl.chaos.chaos_admin.domain.User;
import com.earl.chaos.chaos_admin.mapper.GroupMapper;
import com.earl.chaos.chaos_admin.mapper.UserMapper;
import com.earl.chaos.chaos_admin.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author earl
 * @description 针对表【group】的数据库操作Service实现
 * @createDate 2022-02-23 22:02:01
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group>
        implements GroupService {

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Group getByCode(String code) {
        List<Group> groupList = groupMapper.findByCode(code);
        return groupList.isEmpty() ? null : groupList.get(0);
    }

    @Override
    public List<User> findUsersById(Integer id) {
        List<User> users = userMapper.findByGroupId(id);
        return users.isEmpty() ? null : users;
    }
}




