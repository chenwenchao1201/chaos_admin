package com.earl.chaos.chaos_admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.earl.chaos.chaos_admin.domain.Challenge;
import com.earl.chaos.chaos_admin.domain.Prize;
import com.earl.chaos.chaos_admin.domain.User;
import com.earl.chaos.chaos_admin.dto.ChallengeDto;
import com.earl.chaos.chaos_admin.dto.PrizeDto;
import com.earl.chaos.chaos_admin.dto.ResultDto;
import com.earl.chaos.chaos_admin.service.PrizeService;
import com.earl.chaos.chaos_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

/**
 * 奖品管理
 * @author earl
 */
@RestController()
@RequestMapping("prize")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private UserService userService;

    // 添加奖品
    @PostMapping("/add")
    public ResultDto add(@RequestBody PrizeDto prizeDto) {
        Prize prize = BeanUtil.toBean(prizeDto, Prize.class);
        if (prizeService.save(prize)) {
            return ResultDto.success("创建成功",prize);
        } else {
            return ResultDto.fail("创建失败");
        }
    }

    //获取奖品列表
    @PostMapping("/list")
    public ResultDto list(@RequestBody PrizeDto prizeDto) {
        Prize prize = BeanUtil.toBean(prizeDto, Prize.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", prize.getUserId());
        queryWrapper.eq("is_exchange", prize.getIsExchange());
        queryWrapper.orderByAsc("id");
        List<Prize> list = prizeService.list(queryWrapper);
        return ResultDto.success("获取列表成功",list);
    }

    //获取最新添加的3个未兑换奖品
    @PostMapping("/list3")
    public ResultDto list3(@RequestBody PrizeDto prizeDto) {
        Prize prize = BeanUtil.toBean(prizeDto, Prize.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", prize.getUserId());
        queryWrapper.eq("is_exchange", 0);
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 3");
        List<Prize> list = prizeService.list(queryWrapper);
        return ResultDto.success("获取列表成功",list);
    }

    //获取奖品名字列表
    @PostMapping("/nameList")
    public ResultDto nameList(@RequestBody PrizeDto prizeDto) {
        Prize prize = BeanUtil.toBean(prizeDto, Prize.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", prize.getUserId());
        queryWrapper.eq("is_exchange", prize.getIsExchange());
        queryWrapper.orderByDesc("create_time");
        queryWrapper.last("limit 3");
        List<Prize> list = prizeService.list(queryWrapper);
        return ResultDto.success("获取列表成功",list.stream().map(Prize::getName).toArray());
    }


    //兑换奖品
    @PostMapping("/exchange")
    public ResultDto exchange(@RequestBody PrizeDto prizeDto) {
        Prize prize = BeanUtil.toBean(prizeDto, Prize.class);
        Prize byId = prizeService.getById(prize.getId());
        if (byId.getIsExchange()) {
            //已兑换
            return ResultDto.fail("已兑换");
        }
        //兑换 判断积分是否足够
        User user = userService.getById(byId.getUserId());
        if (user.getTotalPrice() < byId.getPrice()) {
            return ResultDto.fail("积分不足");
        }else{
            //扣除积分
            user.setTotalPrice(user.getTotalPrice() - byId.getPrice());
            userService.updateById(user);
        }
        //是否可重复兑换
        if (byId.getIsRepeat()) {
            //可重复兑换,复制一条记录
            byId.setId(null);
            byId.setIsExchange(true);
            byId.setIsRepeat(false);
            prizeService.save(BeanUtil.toBean(byId, Prize.class));
            return ResultDto.successWithoutResult("兑换成功");
        }else{
            //不可重复兑换
            prize.setIsExchange(true);
            prize.setExchangeTime(new Date(System.currentTimeMillis()));
            if (prizeService.updateById(prize)) {
                return ResultDto.successWithoutResult("兑换成功");
            } else {
                return ResultDto.fail("兑换失败");
            }
        }
    }


    //删除奖品
    @PostMapping("/delete")
    public ResultDto delete(@RequestBody PrizeDto prizeDto) {
        Prize prize = BeanUtil.toBean(prizeDto, Prize.class);
        if (prizeService.removeById(prize.getId())) {
            return ResultDto.successWithoutResult("删除成功");
        } else {
            return ResultDto.fail("删除失败");
        }
    }

    //更新奖品
    @PostMapping("/update")
    public ResultDto update(@RequestBody PrizeDto prizeDto) {
        Prize prize = BeanUtil.toBean(prizeDto, Prize.class);
        if (prizeService.updateById(prize)) {
            return ResultDto.successWithoutResult("更新成功");
        } else {
            return ResultDto.fail("更新失败");
        }
    }
}
