package com.xuanwu.cmp.utils.file;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Description File Upload Config
 * @Author <a href="jiji@javawind.com">Xuefang.Xu</a>
 * @Date 2016-08-19
 * @Version 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "fileUploadConfig")
public class FileUploadConfig {

	private String inputName;
	private HashMap<String, String[]> exts;
	private HashMap<String, Integer> maxSize;

	public static final String ROOT_FOLDER = "d:/cmp_files/upload/";

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public HashMap<String, String[]> getExts() {
		return exts;
	}

	public void setExts(HashMap<String, String[]> exts) {
		this.exts = exts;
	}

	public HashMap<String, Integer> getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(HashMap<String, Integer> maxSize) {
		this.maxSize = maxSize;
	}

	public static enum FileType {
		OTHER(0), IMAGE(1), DOCUMENT(2), AUDIO(3), RAR(4);

		private int index;

		private FileType(int index) {
			this.index = index;
		}

		public static FileType getType(int index) {
			for (FileType type : FileType.values()) {
				if (type.getIndex() == index) {
					return type;
				}
			}
			return OTHER;
		}

		public int getIndex() {
			return index;
		}
	}
}
