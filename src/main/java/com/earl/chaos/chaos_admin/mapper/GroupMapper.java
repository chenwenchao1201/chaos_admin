package com.earl.chaos.chaos_admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.earl.chaos.chaos_admin.domain.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author earl
 * @description 针对表【group】的数据库操作Mapper
 * @createDate 2022-02-23 22:02:01
 * @Entity generator.domain.Group
 */
public interface GroupMapper extends BaseMapper<Group> {
    List<Group> findByCode(@Param("code") String code);
}




