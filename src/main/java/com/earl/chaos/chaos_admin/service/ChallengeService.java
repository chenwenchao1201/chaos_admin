package com.earl.chaos.chaos_admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.earl.chaos.chaos_admin.domain.Challenge;

import java.util.List;

/**
 * @author earl
 * @description 针对表【challenge】的数据库操作Service
 * @createDate 2022-03-22 16:41:24
 */
public interface ChallengeService extends IService<Challenge> {

    List<Challenge> getListByUserId(Integer id);

    List<Challenge> getWeeklyList(Integer userId);
}
