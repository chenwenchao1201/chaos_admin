package com.earl.chaos.chaos_admin.dto;

import com.earl.chaos.chaos_admin.domain.Challenge;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName challenge
 */
@Data
public class ChallengeDto extends Challenge implements Serializable {

    private PrizeDto prizeDto;
}