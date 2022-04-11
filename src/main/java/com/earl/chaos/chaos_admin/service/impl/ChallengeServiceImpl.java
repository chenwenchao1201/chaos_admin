package com.earl.chaos.chaos_admin.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earl.chaos.chaos_admin.domain.Challenge;
import com.earl.chaos.chaos_admin.service.ChallengeService;
import com.earl.chaos.chaos_admin.mapper.ChallengeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author earl
* @description 针对表【challenge】的数据库操作Service实现
* @createDate 2022-03-22 16:41:24
*/
@Service
public class ChallengeServiceImpl extends ServiceImpl<ChallengeMapper, Challenge>
    implements ChallengeService{

    @Autowired
    ChallengeMapper challengeMapper;

    @Override
    public List<Challenge> getListByUserId(Integer id) {
        List<Challenge> challengeList = challengeMapper.findByUserIdAndIsDoneOrderByCreateTime(id, false);
        if (challengeList.size()>0) {
            return challengeList;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<Challenge> getWeeklyList(Integer userId) {
        List<Challenge> returnList = new ArrayList<>();
        List<Challenge> challengeList = challengeMapper.findByIsLoopAndUserId(true, userId);
        challengeList.forEach(challenge -> {
            if (challenge.getDoneTime() == null) {
                challenge.setPrice(RandomUtil.randomInt(challenge.getPrice(), challenge.getPrice()*2));
                returnList.add(challenge);
            } else {
                if (DateUtil.offsetDay(challenge.getDoneTime(), challenge.getLoopTime()).before(DateUtil.date())) {
                    challenge.setPrice(RandomUtil.randomInt(challenge.getPrice(), challenge.getPrice()*2));
                    returnList.add(challenge);
                }
            }
        });
        return returnList;
    }
}




