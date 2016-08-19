package com.xuanwu.cmp.domain.entity;

import com.xuanwu.cmp.domain.AbstractEntity;

/**
 * @Description 区域号段
 * 林泽强
 */
public class RegionCarrierMap extends AbstractEntity {


	private static final long serialVersionUID = 1L;
	private Integer id;
	private String phone;
	private String brand;
	private Integer regionId;
	private Integer carrierId;
	private Boolean isRemoved;
	private Integer removeId;
	private Integer sort;
	private Boolean showed;
	private String carrierName;
	private String regionName;
	private String areaIdentity;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(Integer carrierId) {
		switch(carrierId){
			case 1:this.carrierName="移动";break;
			case 2:this.carrierName="联通";break;
			case 3:this.carrierName="电信小灵通";break;
			case 4:this.carrierName="电信CDMA";break;
			default:this.carrierName="";break;
		}
		this.carrierId = carrierId;
	}

	public Boolean getRemoved() {
		return isRemoved;
	}

	public void setRemoved(Boolean removed) {
		isRemoved = removed;
	}

	public Integer getRemoveId() {
		return removeId;
	}

	public void setRemoveId(Integer removeId) {
		this.removeId = removeId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getShowed() {
		return showed;
	}

	public void setShowed(Boolean showed) {
		this.showed = showed;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getAreaIdentity() {
		return areaIdentity;
	}

	public void setAreaIdentity(String areaIdentity) {
		this.areaIdentity = areaIdentity;
	}

}
