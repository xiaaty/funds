package com.gqhmt.sys.beans;


import java.util.Date;

public class Roles{
	private long  id;
    private int status;
    private int isDel;
    private long createId;
    private long modifyId;
	private String roleName;
    private String description;
	private Date createTime;
    private Date modifyDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public long getCreateId() {
        return createId;
    }

    public void setCreateId(long createId) {
        this.createId = createId;
    }

    public long getModifyId() {
        return modifyId;
    }

    public void setModifyId(long modifyId) {
        this.modifyId = modifyId;
    }

    public String getRoleName() {
        return roleName == null?"":roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
