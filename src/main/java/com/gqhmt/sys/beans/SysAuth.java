package com.gqhmt.sys.beans;

import java.util.Date;

public class SysAuth {
	private long id;
    private long roleId;
    private long funcId;
    private long createId;
    private long modifyId;
	private String remark;
	private Date createTime;
    private Date modifyDate;
	public SysAuth(){
		
	}
	public SysAuth(long roleId,long funcId,long createId){
		this.roleId=roleId;
		this.funcId=funcId;
		this.createId=createId;
	}

	public String getRemark() {
		return remark==null?"":remark.trim();
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime==null?new Date():createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyDate() {
		return modifyDate==null?new Date():modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getFuncId() {
        return funcId;
    }

    public void setFuncId(long funcId) {
        this.funcId = funcId;
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
}
