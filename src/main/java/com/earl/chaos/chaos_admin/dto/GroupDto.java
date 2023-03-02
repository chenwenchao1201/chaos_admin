package com.earl.chaos.chaos_admin.dto;

import com.earl.chaos.chaos_admin.domain.Group;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName group
 */
@Data
public class GroupDto extends Group implements Serializable {

    private Integer userId;
}