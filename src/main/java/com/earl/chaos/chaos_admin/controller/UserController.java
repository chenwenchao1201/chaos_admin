package com.earl.chaos.chaos_admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.earl.chaos.chaos_admin.domain.User;
import com.earl.chaos.chaos_admin.dto.ResultDto;
import com.earl.chaos.chaos_admin.dto.UserDto;
import com.earl.chaos.chaos_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userservice;

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
        userservice.register(user);
        return ResultDto.successWithoutResult("注册成功");
    }

    @PostMapping("/getUserInfo")
    public ResultDto getUserInfo(@RequestBody UserDto user){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("weixin_id", user.getWeixinId());
        User one = userservice.getOne(queryWrapper);
        return ResultDto.success("查询成功",one);
    }
}
