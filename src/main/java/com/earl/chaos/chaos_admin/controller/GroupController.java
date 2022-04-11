package com.earl.chaos.chaos_admin.controller;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.earl.chaos.chaos_admin.domain.Group;
import com.earl.chaos.chaos_admin.domain.User;
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

import java.util.List;

@RestController()
@RequestMapping("group")
public class GroupController {

    @Autowired
    GroupService groupservice;

    @Autowired
    UserService userService;

    @PostMapping("/getGroup")
    public ResultDto getGroup(@RequestBody GroupDto group){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", group.getCode());
        Group one = groupservice.getOne(queryWrapper);
        return ResultDto.success("查询成功", one);
    }

    @PostMapping("/add")
    public ResultDto add(@RequestBody GroupDto group){
        group.setCode(String.valueOf(UUID.fastUUID()));
        if (groupservice.save(group)) {
            User user = userService.getById(group.getUserId());
            if (user != null) {
                user.setGroupId(group.getId());
                userService.updateById(user);
                return ResultDto.successWithoutResult("新增成功");
            }else{
                return ResultDto.fail("用户不存在");
            }
        }else{
            return ResultDto.fail("新增失败");
        }
    }

    @PostMapping("/edit")
    public ResultDto edit(@RequestBody GroupDto group){
        Group byId = groupservice.getById(group.getId());
        if (byId != null) {
            byId.setName(group.getName());
            groupservice.updateById(byId);
            return ResultDto.successWithoutResult("更新成功");
        }else{
            return ResultDto.fail("分组不存在");
        }
    }

    @PostMapping("/users")
    public ResultDto users(@RequestBody GroupDto group){
        List<User> users = groupservice.findUsersById(group.getId());
        if (users != null) {
            return ResultDto.success("获取成功", users);
        }else{
            return ResultDto.fail("获取失败");
        }
    }

    @PostMapping("/delUser")
    public ResultDto delUser(@RequestBody GroupDto group){
        User user = userService.getById(group.getUserId());
        if (user != null) {
            userService.clearGroupId(group.getUserId());
            return ResultDto.successWithoutResult("剔除成功");
        }else{
            return ResultDto.fail("人员不存在");
        }
    }
}
