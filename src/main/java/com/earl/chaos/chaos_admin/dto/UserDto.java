package com.earl.chaos.chaos_admin.dto;

import com.earl.chaos.chaos_admin.domain.User;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author earl
 */
@Data
public class UserDto extends User implements Serializable {

    private String code;

    /**
     * 分组列表中是否显示可删除按钮
     */
    private boolean canDelete;

    private boolean isNewUser = false;


    public static List<UserDto> convert2List(List<User> users) {
        List<UserDto> list = new ArrayList<>(users.size());
        for (User user : users) {
            UserDto userDto = convert(user);
            list.add(userDto);
        }
        return list;
    }

    public static UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSex(user.getSex());
        userDto.setWeixinId(user.getWeixinId());
        userDto.setGroupId(user.getGroupId());
        userDto.setPriceUnitName(user.getPriceUnitName());
        userDto.setTotalPrice(user.getTotalPrice());
        return userDto;
    }
}