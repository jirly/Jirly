package com.xuanwu.cmp.domain.entity;

import com.xuanwu.cmp.domain.AbstractEntity;

/**
 * 运营商名称
 * @author 林泽强
 *  
 */
public class Carrier extends AbstractEntity{
	private static final long serialVersionUID = 1L;

	/**
	 * 运营商ID
	 */
	private int id;
	/**
	 * 运营商名称
	 */
	private String name;
	/**
	 * 排序值
	 */
	private int sort;
	/**
	 * 是否显示
	 */
	private int showed;
	
	private boolean selected;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getShowed() {
		return showed;
	}

	public void setShowed(int showed) {
		this.showed = showed;
	}

	/**
	* @param selected 要设置的 selected
	*/
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	* @return selected
	*/
	public boolean isSelected() {
		return selected;
	}


}
