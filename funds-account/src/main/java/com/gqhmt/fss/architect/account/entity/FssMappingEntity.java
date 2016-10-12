package com.gqhmt.fss.architect.account.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 红包账户实体类
 */
@Entity
@Table(name = "t_gq_fss_mapping")
public class FssMappingEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cust_id")
    private long custId;    //客户编号

    @Column(name = "remark")
    private String remark;//备注

    @Column(name = "mapping_type")
    private String mappingType;      //映射类型

    @Column(name = "trade_type")
    private String tradeType;   //交易类型

    @Column(name = "create_time")
    private Date createTime; //创建时间

    @Column(name = "creater")
    private String creater; //创建人

    @Column(name = "modify_time")
    private Date modifyTime; //修改时间

    @Column(name = "updater")
    private String updater; //修改人

    @Column(name = "is_valid")
    private String isValid; //是否有效

    @Column(name = "sort")
    private String sort; //排序

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMappingType() {
        return mappingType;
    }

    public void setMappingType(String mappingType) {
        this.mappingType = mappingType;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
