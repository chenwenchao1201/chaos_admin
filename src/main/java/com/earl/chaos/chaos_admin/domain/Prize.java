package com.earl.chaos.chaos_admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName prize
 */
@TableName(value ="prize")
@Data
public class Prize implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 奖品名
     */
    private String name;

    /**
     * 奖品价格
     */
    private Integer price;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 外部用户id
     */
    private Integer outUserId;

    /**
     * 是否已兑换
     */
    private Boolean isExchange;

    /**
     * 
     */
    private Boolean isRepeat;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Prize other = (Prize) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOutUserId() == null ? other.getOutUserId() == null : this.getOutUserId().equals(other.getOutUserId()))
            && (this.getIsExchange() == null ? other.getIsExchange() == null : this.getIsExchange().equals(other.getIsExchange()))
            && (this.getIsRepeat() == null ? other.getIsRepeat() == null : this.getIsRepeat().equals(other.getIsRepeat()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOutUserId() == null) ? 0 : getOutUserId().hashCode());
        result = prime * result + ((getIsExchange() == null) ? 0 : getIsExchange().hashCode());
        result = prime * result + ((getIsRepeat() == null) ? 0 : getIsRepeat().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", userId=").append(userId);
        sb.append(", outUserId=").append(outUserId);
        sb.append(", isExchange=").append(isExchange);
        sb.append(", isRepeat=").append(isRepeat);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}