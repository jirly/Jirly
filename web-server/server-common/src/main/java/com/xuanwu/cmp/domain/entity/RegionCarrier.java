package com.xuanwu.cmp.domain.entity;

/**
 * @Description RegionCarrier——区域通道实体类
 * @author <a href="jiangziyuan@wxchina.com">ZiYuan.Jiang</a>
 * @date 2016-8-11
 * @version 1.0.0
 */
public class RegionCarrier {

    private int id;
    private int carrierId;
    private int regionId;
    private String name;
    private String remark;
    /**
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id 要设置的 id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return carrierId
     */
    public int getCarrierId() {
        return carrierId;
    }
    /**
     * @param carrierId 要设置的 carrierId
     */
    public void setCarrierId(int carrierId) {
        this.carrierId = carrierId;
    }
    /**
     * @return regionId
     */
    public int getRegionId() {
        return regionId;
    }
    /**
     * @param regionId 要设置的 regionId
     */
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
    /**
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name 要设置的 name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }
    /**
     * @param remark 要设置的 remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
