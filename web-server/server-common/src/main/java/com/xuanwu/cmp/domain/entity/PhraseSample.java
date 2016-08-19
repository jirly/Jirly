/*   
* Copyright (c) 2016/8/18 by XuanWu Wireless Technology Co., Ltd 
*             All rights reserved  
*/
package com.xuanwu.cmp.domain.entity;

import com.xuanwu.cmp.domain.AbstractEntity;

import java.util.Date;

/**
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @version 1.0.0
 * @date 2016/8/18
 */
public class PhraseSample extends AbstractEntity {

    private static final long serialVersionUID = 480942726995879229L;

    private Integer id;
    private Phrase.PhraseType type;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Boolean isRemove;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Phrase.PhraseType getType() {
        return type;
    }

    public void setType(Phrase.PhraseType type) {
        this.type = type;
    }

    public void setTypeIndex(Integer index) {
        this.type = Phrase.PhraseType.getType(index);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Boolean getRemove() {
        return isRemove;
    }

    public void setRemove(Boolean remove) {
        isRemove = remove;
    }
}
