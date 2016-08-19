package com.xuanwu.cmp.domain.entity;

import com.xuanwu.cmp.domain.AbstractEntity;

import java.util.Date;

/**
 *  * @author 林泽强
 *  关键字
 */
public class Keyword extends AbstractEntity {
	
	public enum KewordType {
		/* 0--通道关键字,1--企业关键字,2--全局关键字 */
		CHANNEL_KEYWORD(0), ENT_KEYWORD(1), GBKEYWORD(2), OTHER(3);
		private final Integer index;

		private KewordType(Integer index) {
			this.index = index;
		}

		public Integer getIndex() {
			return index;
		}

		public static KewordType getType(Integer index) {
			switch (index) {
			case 0: 
			   return CHANNEL_KEYWORD;
			case 1:
				return ENT_KEYWORD;
			case 2:
				return GBKEYWORD;
			case 3:
				return OTHER;
			default:
				return OTHER;
			}
		}
		
		public static KewordType getType(String typeName) {
			String tempTypeName = typeName.trim();
			if ("全局关键字".equals(tempTypeName)) {
				return GBKEYWORD;
			} else if ("通道关键字".equals(tempTypeName)) {
				return CHANNEL_KEYWORD;
			}
			return OTHER; 
		}
		
		public static String getTypeName(Integer type) {
			switch (type) {
			case 0:
				return "通道关键字";
			case 1:
				return "企业关键字";
			case 2:
				return "全局关键字";
			default:
				return "全局关键字";
			}
		}
	}

	
	private Integer id;
	private String keywordName;
	private Date handleTime;
	private Boolean isRemoved;
	private Integer userId;
	private Integer type;
	private Integer targetId;
	private String targetName;
	private String userName;
	private String typeName;

	public String getKeywordName() {
		return keywordName;
	}

	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getRemoved() {
		return isRemoved;
	}

	public void setRemoved(Boolean removed) {
		isRemoved = removed;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
