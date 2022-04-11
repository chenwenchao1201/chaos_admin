package com.earl.chaos.chaos_admin.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.earl.chaos.chaos_admin.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author earl
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-03-12 22:24:30
* @Entity com.earl.chaos.chaos_admin.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    List<User> findByWeixinId(@Param("weixinId") String weixinId);

    List<User> findByGroupId(@Param("groupId") Integer groupId);

    int updateGroupIdById(@Param("id") Integer id);
}




