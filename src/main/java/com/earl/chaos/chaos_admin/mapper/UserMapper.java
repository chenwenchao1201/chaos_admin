package com.earl.chaos.chaos_admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.earl.chaos.chaos_admin.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author earl
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2022-03-12 22:24:30
 * @Entity com.earl.chaos.chaos_admin.domain.User
 */
public interface UserMapper extends BaseMapper<User> {

    User findByWeixinId(@Param("weixinId") String weixinId);

    List<User> findByGroupId(@Param("groupId") Integer groupId);

    int updateGroupIdById(@Param("id") Integer id);
}




