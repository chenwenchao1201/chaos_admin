package com.earl.chaos.chaos_admin.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.earl.chaos.chaos_admin.domain.Challenge;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName challenge
 */
@Data
public class ChallengeDto extends Challenge implements Serializable {

}