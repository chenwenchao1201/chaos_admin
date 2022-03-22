package com.earl.chaos.chaos_admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.earl.chaos.chaos_admin.domain.Group;
import com.earl.chaos.chaos_admin.dto.GroupDto;
import com.earl.chaos.chaos_admin.dto.ResultDto;
import com.earl.chaos.chaos_admin.dto.UserDto;
import com.earl.chaos.chaos_admin.service.GroupService;
import com.earl.chaos.chaos_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("group")
public class GroupController {

    @Autowired
    GroupService groupservice;

    @PostMapping("/getGroup")
    public ResultDto getGroup(@RequestBody GroupDto group){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", group.getCode());
        Group one = groupservice.getOne(queryWrapper);
        return ResultDto.success("查询成功", one);
    }

}
