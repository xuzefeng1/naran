package com.naran.foundation.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 持久化对象统一顶级类（用于被继承）
 * 
 * @author QuCeng
 */
public class BaseEntityPO implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Long id; // 主键

    private Date createTime; // 数据创建时间

    private Date updateTime; // 数据更新时间

    private Boolean expired; // 数据失效时间（描述数据逻辑删除）

    public BaseEntityPO() {
    }

    public BaseEntityPO(Long id) {
	this.id = id;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public Date getUpdateTime() {
	return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
    }

    public Boolean getExpired() {
	return expired;
    }

    public void setExpired(Boolean expired) {
	this.expired = expired;
    }

}
