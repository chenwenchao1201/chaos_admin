package com.earl.chaos.chaos_admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earl.chaos.chaos_admin.domain.User;
import com.earl.chaos.chaos_admin.dto.UserDto;
import com.earl.chaos.chaos_admin.mapper.UserMapper;
import com.earl.chaos.chaos_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author earl
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2022-02-23 22:02:01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Value("${AppId}")
    private String appid;

    @Value("${AppSecret}")
    private String secret;

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    UserMapper userMapper;

    @Override
    public void register(UserDto user) {
        userMapper.insert(BeanUtil.toBean(user, User.class));
    }

    @Override
    public boolean login(UserDto user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("weixin_id", user.getWeixinId());
        User one = getOne(wrapper);
        if (one != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findByWeixinId(String weixinId) {
        return userMapper.findByWeixinId(weixinId);
    }

    @Override
    public boolean clearGroupId(Integer userId) {
        return userMapper.updateGroupIdById(userId) > 0;
    }

    @Override
    public User getWxUser(String openId) {
        // 本地数据库查询是否存在该用户
        return userMapper.findByWeixinId(openId);
    }

    @Override
    public String getOpenId(String weixinId) {
        // 调用微信接口，获取对应openid
        String url = baseUrl + "?appid=" + appid + "&secret=" + secret + "&js_code=" + weixinId + "&grant_type=authorization_code";
        String result = HttpUtil.get(url);
        System.out.println(result);
        return JSONUtil.parse(result).getByPath("openid").toString();
    }
}




