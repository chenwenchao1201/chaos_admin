package com.earl.chaos.chaos_admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.earl.chaos.chaos_admin.domain.Challenge;
import com.earl.chaos.chaos_admin.domain.Group;
import com.earl.chaos.chaos_admin.domain.User;
import com.earl.chaos.chaos_admin.dto.ChallengeDto;
import com.earl.chaos.chaos_admin.dto.GroupDto;
import com.earl.chaos.chaos_admin.dto.ResultDto;
import com.earl.chaos.chaos_admin.service.ChallengeService;
import com.earl.chaos.chaos_admin.service.GroupService;
import com.earl.chaos.chaos_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("challenge")
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    @Autowired
    UserService userService;

    @PostMapping("/getChallenges")
    public ResultDto getChallenges(@RequestBody ChallengeDto challengeDto){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", challengeDto.getUserId());
        queryWrapper.eq("is_done", challengeDto.getIsDone());
        return ResultDto.success("查询成功", challengeService.list(queryWrapper));
    }



    @PostMapping("/done")
    public ResultDto done(@RequestBody ChallengeDto challengeDto){
        // 判断是否已经已完成
        Challenge challenge = challengeService.getById(challengeDto.getId());
        if (!challenge.getIsDone()) {
            challengeDto.setIsDone(true);
            if (challengeService.updateById(BeanUtil.toBean(challengeDto, Challenge.class))) {
                User user = userService.getById(challenge.getUserId());
                user.setTotalPrice(user.getTotalPrice()+challenge.getPrice());
                userService.updateById(user);
                return ResultDto.successWithoutResult("任务完成成功");
            }else{
                return ResultDto.fail("任务完成失败");
            }
        }else{
            return ResultDto.fail("当前任务已完成，不可重复完成");
        }
    }

    @PostMapping("/again")
    public ResultDto again(@RequestBody ChallengeDto challengeDto){
        // 判断是否已经已完成
        Challenge challenge = challengeService.getById(challengeDto.getId());
        if (challenge.getIsDone()) {
            challengeDto.setIsDone(false);
            if (challengeService.updateById(BeanUtil.toBean(challengeDto, Challenge.class))) {
                User user = userService.getById(challenge.getUserId());
                user.setTotalPrice(user.getTotalPrice()+challenge.getPrice());
                userService.updateById(user);
                return ResultDto.successWithoutResult("任务再挑战成功");
            }else{
                return ResultDto.fail("任务再挑战失败");
            }
        }else{
            return ResultDto.fail("当前任务未完成，无需重复挑战");
        }
    }



    @PostMapping("/getById")
    public ResultDto getById(@RequestBody ChallengeDto challengeDto){
        Challenge challenge = challengeService.getById(challengeDto.getId());
        if (challenge != null) {
            return ResultDto.success("查询成功", challenge);
        }else{
            return ResultDto.fail("查询失败，id不正确");
        }
    }
}
