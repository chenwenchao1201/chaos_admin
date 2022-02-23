package com.earl.chaos.chaos_admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName challenge
 */
@TableName(value ="challenge")
@Data
public class Challenge implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 挑战名
     */
    private String name;

    /**
     * 挑战描述
     */
    private String description;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 积分值
     */
    private Integer price;

    /**
     * 是否完成
     */
    private Boolean isDone;

    /**
     * 外部创建id
     */
    private Integer outCreateId;

    /**
     * 外部是否确认完成
     */
    private Integer isOutDone;

    /**
     * 奖品id
     */
    private Integer prizeId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 完成时间
     */
    private Date doneTime;

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
        Challenge other = (Challenge) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getIsDone() == null ? other.getIsDone() == null : this.getIsDone().equals(other.getIsDone()))
            && (this.getOutCreateId() == null ? other.getOutCreateId() == null : this.getOutCreateId().equals(other.getOutCreateId()))
            && (this.getIsOutDone() == null ? other.getIsOutDone() == null : this.getIsOutDone().equals(other.getIsOutDone()))
            && (this.getPrizeId() == null ? other.getPrizeId() == null : this.getPrizeId().equals(other.getPrizeId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getDoneTime() == null ? other.getDoneTime() == null : this.getDoneTime().equals(other.getDoneTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getIsDone() == null) ? 0 : getIsDone().hashCode());
        result = prime * result + ((getOutCreateId() == null) ? 0 : getOutCreateId().hashCode());
        result = prime * result + ((getIsOutDone() == null) ? 0 : getIsOutDone().hashCode());
        result = prime * result + ((getPrizeId() == null) ? 0 : getPrizeId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getDoneTime() == null) ? 0 : getDoneTime().hashCode());
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
        sb.append(", description=").append(description);
        sb.append(", userId=").append(userId);
        sb.append(", price=").append(price);
        sb.append(", isDone=").append(isDone);
        sb.append(", outCreateId=").append(outCreateId);
        sb.append(", isOutDone=").append(isOutDone);
        sb.append(", prizeId=").append(prizeId);
        sb.append(", createTime=").append(createTime);
        sb.append(", doneTime=").append(doneTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}