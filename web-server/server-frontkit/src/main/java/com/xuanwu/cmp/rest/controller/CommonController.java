package com.xuanwu.cmp.rest.controller;

import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.PlatformEnabler;
import com.xuanwu.cmp.domain.entity.UserSign;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.service.AppService;
import com.xuanwu.cmp.service.PlatformEnablerService;
import com.xuanwu.cmp.service.UserSignService;
import com.xuanwu.cmp.utils.QueryParameters;
import com.xuanwu.cmp.utils.file.FileUploadConfig;
import com.xuanwu.cmp.utils.file.FileUploadInfo;
import com.xuanwu.cmp.utils.file.FileUploader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * CommonController
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-11
 * @version 1.0.0
 */
@RestController
@Path("common")
public class CommonController {

	@Autowired
	private PlatformEnablerService platformEnablerService;

	@Autowired
	private UserSignService userSignService;

	@Autowired
	private AppService appService;

	@Autowired
	private FileUploader fileUploader;


	// 获取平台能力列表
	@GET
	@Path("enablers")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getEnablers() {
		Collection<PlatformEnabler> enablers = platformEnablerService.list(null);
		return JsonResp.success(enablers);
	}

    //获取当前企业所有签名列表
	@GET
	@Path("signs")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getSigns() {
		// TODO: 2016/8/12 获取当前用户企业ID
		int enterpriseId = 1;
		List<UserSign> userSigns = userSignService.findByEnterprise(enterpriseId);
		return JsonResp.success(userSigns);
	}

	//获取当前企业所有应用列表
	@GET
	@Path("apps")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getApps() {
		// TODO: 2016/8/16 获取当前用户企业ID
		Integer enterpriseId = 1;
		QueryParameters params = new QueryParameters();
		params.addParam("enterpriseId", enterpriseId);
		List<App> apps = (List<App>) appService.list(params);
		return JsonResp.success(apps);
	}


	/**
	 * 上载附件，表单变量名file;  ng-file-upload变量名file
	 * */
	@POST
	@Path("upload" )
	public FileUploadInfo uploadFile(@Context HttpServletRequest request) {
        // TODO: 2016/8/16 获取当前用户企业ID
		Integer enterpriseId = 1;
		List<FileUploadConfig.FileType> types = new ArrayList<FileUploadConfig.FileType>();
		types.add(FileUploadConfig.FileType.IMAGE);
		types.add(FileUploadConfig.FileType.DOCUMENT);
		types.add(FileUploadConfig.FileType.RAR);
		FileUploadInfo info = fileUploader.uploadFiles(request, types, 1, enterpriseId);
		return  info;
	}


	/**
	 * 下载附件
	 * get参数path为所要获取文件路径，该路径不包含企业ID信息
	 * */
	@GET
	@Path("download" )
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadFile(@QueryParam("path") String path){
		// TODO: 2016/8/16 获取当前用户企业ID
		Integer enterpriseId = 1;
		String realPath = "";
		if (StringUtils.isNotBlank(path)) {
			realPath = FileUploadConfig.ROOT_FOLDER + enterpriseId + "/" + path;
		}else {
			throw new WebApplicationException(404);
		}
		File file = new File(realPath);
		if (!file.exists()) {
			throw new WebApplicationException(404);
		}
		String mt = new MimetypesFileTypeMap().getContentType(file);
		return Response
				.ok(file, mt)
				.header("Content-disposition","attachment;filename="+ file.getName())
				.header("Cache-Control", "no-cache").build();
	}


}
