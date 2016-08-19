package com.xuanwu.cmp.domain.entity;

import java.util.Date;

import com.xuanwu.cmp.domain.AbstractEntity;

/**
 * @Description VoiceDisplayNum.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
public class VoiceDisplayNum extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private String displayNum;// 语音显号号码
	private int enterpriseId;// 企业id
	private State state;// 状态0-未审核，1-已审核，2-未通过审核
	private Date createTime;// 创建时间
	private String certifyFile;// 上传文件名
	private String fileType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisplayNum() {
		return displayNum;
	}

	public void setDisplayNum(String displayNum) {
		this.displayNum = displayNum;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setStateIdx(int idx) {
		this.state = State.getState(idx);
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCertifyFile() {
		return certifyFile;
	}

	public void setCertifyFile(String certifyFile) {
		this.certifyFile = certifyFile;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String certifyFile) {
		String file[]=certifyFile.split("\\.");
		if(file[file.length-1].equals("jpg")||file[file.length-1].equals("png")||file[file.length-1].equals("jpeg")){
			this.fileType = "img";
		}else{
			this.fileType = "doc";
		}
	}

	public static enum State {
		UNAUDITED(0), AUDITED(1), REJECT(2);

		private int index;

		private State(int index) {
			this.index = index;
		}

		public static State getState(int index) {
			for (State state : State.values()) {
				if (state.getIndex() == index) {
					return state;
				}
			}
			return null;
		}

		public int getIndex() {
			return index;
		}
	}
}
