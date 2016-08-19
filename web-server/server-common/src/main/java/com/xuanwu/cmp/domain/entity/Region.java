package com.xuanwu.cmp.domain.entity;

import com.xuanwu.cmp.domain.AbstractEntity;

/**
 * @Description Region——地区实体类
 * @author <a href="jiangziyuan@wxchina.com">ZiYuan.Jiang</a>
 * @date 2016-8-11
 * @version 1.0.0
 */
public class Region extends AbstractEntity{
    private Integer id;
    private Integer parentId;
    private String name;
    private String areaIdentity;
    private Integer type;
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id 要设置的 id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return parentId
     */
    public Integer getParentId() {
        return parentId;
    }
    /**
     * @param parentId 要设置的 parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
     * @return areaIdentity
     */
    public String getAreaIdentity() {
        return areaIdentity;
    }
    /**
     * @param areaIdentity 要设置的 areaIdentity
     */
    public void setAreaIdentity(String areaIdentity) {
        this.areaIdentity = areaIdentity;
    }
    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }
    /**
     * @param type 要设置的 type
     */
    public void setType(Integer type) {
        this.type = type;
    }
}
