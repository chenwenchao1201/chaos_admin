package com.earl.chaos.chaos_admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.earl.chaos.chaos_admin.domain.Challenge;
import com.earl.chaos.chaos_admin.domain.Group;
import com.earl.chaos.chaos_admin.domain.User;
import com.earl.chaos.chaos_admin.dto.ResultDto;
import com.earl.chaos.chaos_admin.dto.UserDto;
import com.earl.chaos.chaos_admin.service.ChallengeService;
import com.earl.chaos.chaos_admin.service.GroupService;
import com.earl.chaos.chaos_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userservice;

    @Autowired
    GroupService groupService;

    @Autowired
    ChallengeService challengeService;

    @PostMapping("/login")
    public ResultDto login(@RequestBody UserDto user){
        if (userservice.login(user)) {
            return ResultDto.successWithoutResult("登陆成功");
        }else{
            return ResultDto.fail("登陆失败，用户不存在");
        }
    }

    @PostMapping("/register")
    public ResultDto register(@RequestBody UserDto user){
        if (userservice.findByWeixinId(user.getWeixinId())!=null) {
            return ResultDto.fail("微信已存在");
        }else{
            userservice.register(user);
            return ResultDto.successWithoutResult("注册成功");
        }
    }

    @PostMapping("/getUserInfo")
    public ResultDto getUserInfo(@RequestBody UserDto user){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("weixin_id", user.getWeixinId());
        User one = userservice.getOne(queryWrapper);
        return ResultDto.success("查询成功",one);
    }


    @PostMapping("/addGroup")
    public ResultDto addGroup(@RequestBody UserDto user){
        User user1 = userservice.getById(user.getId());
        if (user1 != null) {
            if (user1.getGroupId() == null) {
                Group group = groupService.getByCode(user.getCode());
                user1.setGroupId(group.getId());
                userservice.updateById(user1);
                return ResultDto.successWithoutResult("加入成功");
            }else{
                return ResultDto.fail("用户已加入小窝");
            }
        }else {
            return ResultDto.fail("用户不存在");
        }
    }

    @PostMapping("/modifyUserInfo")
    public ResultDto modifyUserInfo(@RequestBody UserDto user){
        User user1 = userservice.getById(user.getId());
        if (user1 != null) {
            userservice.updateById(user);
            return ResultDto.successWithoutResult("修改成功");
        }else {
            return ResultDto.fail("用户不存在");
        }
    }

    @PostMapping("/getUserChallenge")
    public ResultDto getUserChallenge(@RequestBody UserDto user){
        User user1 = userservice.getById(user.getId());
        if (user1 != null) {
            return ResultDto.success("获取挑战列表成功",challengeService.getListByUserId(user.getId()));
        }else {
            return ResultDto.fail("用户不存在");
        }
    }


}
