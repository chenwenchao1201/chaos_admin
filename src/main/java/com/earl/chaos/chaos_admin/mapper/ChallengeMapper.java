package com.earl.chaos.chaos_admin.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.earl.chaos.chaos_admin.domain.Challenge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author earl
* @description 针对表【challenge】的数据库操作Mapper
* @createDate 2022-03-22 16:41:24
* @Entity com.earl.chaos.chaos_admin.domain.Challenge
*/
public interface ChallengeMapper extends BaseMapper<Challenge> {

    List<Challenge> findByUserIdAndIsDoneOrderByCreateTime(@Param("userId")Integer userId,@Param("isDone")Boolean isDone);

    List<Challenge> findByIsLoopAndUserId(@Param("isLoop") Boolean isLoop, @Param("userId") Integer userId);
}




