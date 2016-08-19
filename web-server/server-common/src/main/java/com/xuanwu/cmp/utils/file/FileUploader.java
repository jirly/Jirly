package com.xuanwu.cmp.utils.file;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.xuanwu.cmp.utils.file.FileUploadConfig.FileType;
import com.xuanwu.cmp.utils.file.FileUploadInfo.UploadResult;

/**
 * @Description File Uploader
 * @Author <a href="jiji@javawind.com">Xuefang.Xu</a>
 * @Date 2013-11-06
 * @Version 1.0.0
 */
@Component
public class FileUploader {

	private static final Logger logger = LoggerFactory.getLogger(FileUploader.class);

	@Autowired
	private FileUploadConfig fileUploadConfig;

	/**
	 * 上传文件
	 * 
	 * @param request
	 *            请求对象
	 * @param types
	 *            允许的文件类型
	 * @param size
	 *            允许上传个数
	 * @param enterpriseId
	 *            企业ID
	 * @return
	 */
	public FileUploadInfo uploadFiles(HttpServletRequest request, List<FileType> types, Integer size,
			int enterpriseId) {
		List<MultipartFile> list = this.getFiles(request, types);
		if (list == null) {
			return new FileUploadInfo(UploadResult.EMPTY_REQUEST, null);
		}
		if (list.isEmpty()) {
			return new FileUploadInfo(UploadResult.EMPTY_FILES, null);
		}
		if (size != null && list.size() > size) {
			return new FileUploadInfo(UploadResult.TOO_MANY_FILES, null);
		}

		List<FileInfo> files = new ArrayList<FileInfo>();
		for (MultipartFile file : list) {
			try {
				FileInfo info = this.saveFile(file, enterpriseId);
				files.add(info);
			} catch (Exception e) {
				logger.error("An error occurred by {}", e);
				return new FileUploadInfo(UploadResult.ERROR, null);
			}
		}

		if (files.isEmpty()) {
			return new FileUploadInfo(UploadResult.EMPTY_FILES, null);
		}
		return new FileUploadInfo(UploadResult.SUCCESS, files);
	}

	/**
	 * 读取文件数据
	 * 
	 * @param request
	 *            请求对象
	 * @param types
	 *            允许的文件类型
	 * @return
	 */
	private List<MultipartFile> getFiles(HttpServletRequest request, List<FileType> types) {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());

		MultipartHttpServletRequest req = null;
		if (resolver.isMultipart(request)) {
			req = resolver.resolveMultipart(request);
		}

		List<MultipartFile> list = req.getFiles(fileUploadConfig.getInputName());
		List<MultipartFile> files = new ArrayList<MultipartFile>();
		for (MultipartFile file : list) {
			FileType type = this.getFileType(file);
			if (this.validateFile(file.getSize(), types, type)) {
				files.add(file);
			}
		}
		return files;
	}

	/**
	 * 保存文件
	 *
	 * @param mpFile
	 * @param enterpriseId
	 * @return
	 * @throws Exception
	 */
	private FileInfo saveFile(MultipartFile mpFile, int enterpriseId) throws Exception {
		String folder = this.getFolder(enterpriseId);
		File temp = new File(FileUploadConfig.ROOT_FOLDER + folder.replace("/", File.separator));
		if (!temp.exists()) {
			temp.mkdirs();
		}

		String name = mpFile.getOriginalFilename();// 原始文件名
		String suffix = name.substring(name.lastIndexOf(".") + 1).toLowerCase();// 文件后缀
		String fileName = UUID.randomUUID().toString() + "." + suffix;// 新文件名

		String path = folder + fileName;
		String filePath = FileUploadConfig.ROOT_FOLDER + path.replace("/", File.separator);
		File file = new File(filePath);
		FileCopyUtils.copy(mpFile.getBytes(), file);

		String relativePath = path.substring(path.indexOf("/", 1));// 去掉企业ID的路径
		FileInfo info = new FileInfo();
		info.setName(name);
		info.setSuffix(suffix);
		info.setPath(relativePath);
		info.setSize(this.getSize(mpFile.getSize()));
		info.setUdate(new Date());
		// info.setType(type);
		return info;
	}

	/**
	 * 保存文件夹
	 *
	 * @param enterpriseId
	 * @return
	 */
	private String getFolder(int enterpriseId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		StringBuffer folder = new StringBuffer();
		folder.append("/");
		folder.append(String.valueOf(enterpriseId));
		folder.append("/");
		folder.append(sdf.format(new Date()));
		folder.append("/");
		return folder.toString();
	}

	/**
	 * 获取当前文件类型
	 * 
	 * @param file
	 * @return
	 */
	private FileType getFileType(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		Iterator<Entry<String, String[]>> iter = fileUploadConfig.getExts().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iter.next();
			String name = entry.getKey();
			String[] exts = entry.getValue();
			Arrays.sort(exts);
			if (Arrays.binarySearch(exts, suffix) >= 0) {
				return FileType.valueOf(name.toUpperCase());
			}
		}

		return FileType.OTHER;
	}

	/**
	 * 验证文件类型和大小
	 *
	 * @param fileSize
	 *            文件大小
	 * @param types
	 *            限制的文件类型
	 * @param type
	 *            当前文件类型
	 * @return
	 */
	private boolean validateFile(long fileSize, List<FileType> types, FileType type) {
		if (type == FileType.OTHER) {
			return false;
		}

		// 判断是否在允许上传的文件列表当中
		boolean validate = false;
		for (FileType fileType : types) {
			if (fileType == type) {
				validate = true;
				break;
			}
		}
		if (!validate) {
			return false;
		}

		Integer maxSize = fileUploadConfig.getMaxSize().get(type.name().toLowerCase());
		if (maxSize == null) {// 文件类型没有配置限制大小
			return false;
		}

		if (fileSize <= 0 || fileSize > maxSize * 1024 * 1024) {// 文件为空或者超大
			return false;
		}

		return true;
	}

	/**
	 * 文件大小转换为KB
	 *
	 * @param size
	 * @return
	 */
	private long getSize(long size) {
		BigDecimal bg = new BigDecimal(size * 1.0 / 1024);
		return bg.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
	}

}
