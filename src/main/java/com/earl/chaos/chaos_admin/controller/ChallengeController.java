package com.earl.chaos.chaos_admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.earl.chaos.chaos_admin.domain.Challenge;
import com.earl.chaos.chaos_admin.domain.Group;
import com.earl.chaos.chaos_admin.domain.Note;
import com.earl.chaos.chaos_admin.domain.User;
import com.earl.chaos.chaos_admin.dto.ChallengeDto;
import com.earl.chaos.chaos_admin.dto.GroupDto;
import com.earl.chaos.chaos_admin.dto.ResultDto;
import com.earl.chaos.chaos_admin.service.ChallengeService;
import com.earl.chaos.chaos_admin.service.GroupService;
import com.earl.chaos.chaos_admin.service.NoteService;
import com.earl.chaos.chaos_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("challenge")
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    @Autowired
    UserService userService;

    @Autowired
    NoteService noteService;

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
            challengeDto.setDoneTime(new Date());
            if (challengeService.updateById(BeanUtil.toBean(challengeDto, Challenge.class))) {
                if (challenge.getOutCreateId() != null) {
                    User user = userService.getById(challenge.getOutCreateId());
                    Note note = new Note();
                    note.setUserId(user.getId());
                    note.setChallengeId(challenge.getId());
                    note.setCreateTime(new Date());
                    note.setDescription("下发的任务已完成，请确认："+challenge.getName());
                    noteService.save(note);
                }else{
                    User user = userService.getById(challenge.getUserId());
                    user.setTotalPrice(user.getTotalPrice()+challenge.getPrice());
                    userService.updateById(user);
                }
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

    //创建挑战  任务
    @PostMapping("/create")
    public ResultDto create(@RequestBody ChallengeDto challengeDto){
        Challenge challenge = BeanUtil.toBean(challengeDto, Challenge.class);
        if (challenge.getPrizeId() != null) {
            // 如果存在奖品，则对应积分为0
            challenge.setPrice(0);
        }
        challenge.setCreateTime(new Date());
        if (challengeService.save(challenge)) {
            return ResultDto.successWithoutResult("创建成功");
        }else{
            return ResultDto.fail("创建失败");
        }
    }

    //更新挑战  任务
    @PostMapping("/update")
    public ResultDto update(@RequestBody ChallengeDto challengeDto){
        Challenge challenge = challengeService.getById(challengeDto.getId());
        if (!challenge.getIsDone()) {
            challenge = BeanUtil.toBean(challengeDto, Challenge.class);
            if (challengeService.updateById(challenge)) {
                return ResultDto.successWithoutResult("更新成功");
            }else{
                return ResultDto.fail("更新失败");
            }
        }else{
            return ResultDto.fail("当前任务已完成，不可更新");
        }
    }

    //删除挑战  任务
    @PostMapping("/delete")
    public ResultDto delete(@RequestBody ChallengeDto challengeDto){
        if (challengeService.removeById(challengeDto.getId())) {
            return ResultDto.successWithoutResult("删除成功");
        }else{
            return ResultDto.fail("删除失败");
        }
    }

    //查询循环任务列表
    @PostMapping("/loopList")
    public ResultDto loopList(@RequestBody ChallengeDto challengeDto){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", challengeDto.getUserId());
        queryWrapper.eq("is_loop", challengeDto.getIsLoop());
        return ResultDto.success("查询成功", challengeService.list(queryWrapper));
    }

    //加入循环任务
    @PostMapping("/addLoop")
    public ResultDto addLoop(@RequestBody ChallengeDto challengeDto){
        // 判断是否已经已完成
        Challenge challenge = challengeService.getById(challengeDto.getId());
        if (challenge.getIsDone()) {
            challengeDto.setIsLoop(true);
            if (challengeService.updateById(BeanUtil.toBean(challengeDto, Challenge.class))) {
                User user = userService.getById(challenge.getUserId());
                userService.updateById(user);
                return ResultDto.successWithoutResult("任务加入循环列表成功");
            }else{
                return ResultDto.fail("任务加入循环列表失败");
            }
        }else{
            return ResultDto.fail("当前任务未完成，无法加入循环列表");
        }
    }


    //获取周常任务列表
    @PostMapping("/getWeeklyList")
    public ResultDto getWeeklyList(@RequestBody ChallengeDto challengeDto){
        return ResultDto.success("查询成功", challengeService.getWeeklyList(challengeDto.getUserId()));
    }
}
