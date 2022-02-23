package com.earl.chaos.chaos_admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earl.chaos.chaos_admin.domain.Group;
import com.earl.chaos.chaos_admin.service.GroupService;
import com.earl.chaos.chaos_admin.mapper.GroupMapper;
import org.springframework.stereotype.Service;

/**
* @author earl
* @description 针对表【group】的数据库操作Service实现
* @createDate 2022-02-23 22:02:01
*/
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group>
    implements GroupService{

}




