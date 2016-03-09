package com.gqhmt.business.architect.loan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author user
 *
 */
@Entity
@Table(name = "t_gq_product_pack_bid_map")
public class ProductPackBidMapEntity {
	// 主键ID
	private Integer id;
	//产品类型：1-优选，2-稳盈，3-直盈，4-新手标，5-散标，9-活动专区
	private Integer productType;
	//对应包装后id
	private Integer packId;
	// 对应标的id
	private Integer bidId;
	// 是否已经删除  未删除 -1 ， 已经删除  -2
	private Integer isDelete;
	// 创建时间
	private Date createTime;
	// 创建人ID
	private long createUserId;
	// 修改时间
	private Date modifyTime;
	// 修改人ID
	private Integer modifyUserId;
	

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "product_type")
	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	@Column(name = "pack_id")
	public Integer getPackId() {
		return packId;
	}

	public void setPackId(Integer packId) {
		this.packId = packId;
	}
	@Column(name = "bid_id")
	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}	
	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	@Column(name = "create_user_id")
	public long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "modify_time")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Column(name = "modify_user_id")
	public Integer getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
	
}
