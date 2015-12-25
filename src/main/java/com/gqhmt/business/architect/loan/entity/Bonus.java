package com.gqhmt.business.architect.loan.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.business.architect.loan.entity.Bonus
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/24 21:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/24  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_bonus")
public class Bonus {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer promoteRuleId;
    private Integer promoteType;
    private Integer userId;
    private Integer fromUserId;
    private String uuid;
    private String name;
    private BigDecimal deadline;
    private BigDecimal amount;
    private BigDecimal startInvestmentAmount;
    private BigDecimal startInvestmentPeriod;
    private Integer isEffect;
    private String description;
    private Date startEffectDate;
    private Date endEffectDate;
    private Integer useClientType;
    private Integer type;
    private Integer isCanTogether;
    private Integer status;
    private String version;
    protected Integer createBy; // 创建者
    protected Date createTime;// 创建日期
    protected Integer updateBy; // 更新者
    protected Date updateTime;// 更新日期

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "promote_rule_id")
    public Integer getPromoteRuleId() {
        return promoteRuleId;
    }

    public void setPromoteRuleId(Integer promoteRuleId) {
        this.promoteRuleId = promoteRuleId;
    }

    @Column(name = "promote_type")
    public Integer getPromoteType() {
        return promoteType;
    }

    public void setPromoteType(Integer promoteType) {
        this.promoteType = promoteType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "from_user_id")
    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDeadline() {
        return deadline;
    }

    public void setDeadline(BigDecimal deadline) {
        this.deadline = deadline;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "start_investment_amount")
    public BigDecimal getStartInvestmentAmount() {
        return startInvestmentAmount;
    }

    public void setStartInvestmentAmount(BigDecimal startInvestmentAmount) {
        this.startInvestmentAmount = startInvestmentAmount;
    }

    @Column(name = "start_investment_period")
    public BigDecimal getStartInvestmentPeriod() {
        return startInvestmentPeriod;
    }

    public void setStartInvestmentPeriod(BigDecimal startInvestmentPeriod) {
        this.startInvestmentPeriod = startInvestmentPeriod;
    }

    @Column(name = "is_effect")
    public Integer getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(Integer isEffect) {
        this.isEffect = isEffect;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "start_effect_date")
    public Date getStartEffectDate() {
        return startEffectDate;
    }

    public void setStartEffectDate(Date startEffectDate) {
        this.startEffectDate = startEffectDate;
    }

    @Column(name = "end_effect_date")
    public Date getEndEffectDate() {
        return endEffectDate;
    }

    public void setEndEffectDate(Date endEffectDate) {
        this.endEffectDate = endEffectDate;
    }

    @Column(name = "use_client_type")
    public Integer getUseClientType() {
        return useClientType;
    }

    public void setUseClientType(Integer useClientType) {
        this.useClientType = useClientType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "is_can_together")
    public Integer getIsCanTogether() {
        return isCanTogether;
    }

    public void setIsCanTogether(Integer isCanTogether) {
        this.isCanTogether = isCanTogether;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "create_by")
    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_by")
    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

   /* @Transient
    public String getCreateTimeStr() {
        return CommonUtil.dateToString(createTime);
    }
    @Transient
    public String getStartEffectTimeStr() {
        return CommonUtil.dateToString(startEffectDate);
    }

    @Transient
    public String getEndEffectTimeStr() {
        return CommonUtil.dateToString(endEffectDate);
    }*/
}
