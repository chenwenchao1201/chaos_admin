package com.earl.chaos.chaos_admin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.earl.chaos.chaos_admin.domain.User;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author earl
 */
@Data
public class UserDto extends User implements Serializable {

    private String code;
}