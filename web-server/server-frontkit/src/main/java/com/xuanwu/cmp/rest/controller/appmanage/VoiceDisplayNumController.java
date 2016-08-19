package com.xuanwu.cmp.rest.controller.appmanage;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xuanwu.cmp.domain.entity.VoiceDisplayNum;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.dto.PageReqt;
import com.xuanwu.cmp.dto.PageResp;
import com.xuanwu.cmp.service.VoiceDisplayNumService;
import com.xuanwu.cmp.utils.PageInfo;
import com.xuanwu.cmp.utils.QueryParameters;
import com.xuanwu.cmp.utils.file.FileUploadConfig;

/**
 * @Description VoiceDisplayNumController.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
@RestController
@Path("appmanage/voice-display-num")
public class VoiceDisplayNumController {

	@Autowired
	private VoiceDisplayNumService voiceDisplayNumService;

	@POST
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getDisPlayNumList(PageReqt req) {
		// TODO 从session中获取企业id
		Integer enterpriseId = 1;
		QueryParameters params = new QueryParameters();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("enterpriseId", enterpriseId);
		params.addParams(param);
		int total = voiceDisplayNumService.count(params);
		if (total == 0) {
			return PageResp.emptyResult();
		}
		PageInfo pageInfo = new PageInfo(req.getPage(), req.getCount(), total);
		params.setPage(pageInfo);
		Collection<VoiceDisplayNum> displayNums = voiceDisplayNumService.list(params);
		return PageResp.success(total, displayNums);

	}

	@POST
	@Path("del")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp del(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			return JsonResp.fail("请选择需要删除的用户！");
		}
		// TODO 从session中获取企业id
		Integer enterpriseId = 1;
		int count = 0;
		for (Integer id : ids) {
			count += voiceDisplayNumService.deleteById(id, enterpriseId);
		}
		//删除文件
		if (count == 0) {
			return JsonResp.fail(-1, "删除数据失败！");
		}
		return JsonResp.success();
	}

	@POST
	@Path("save")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp save(@RequestBody VoiceDisplayNum voiceDisplayNum) {
		// TODO 从session中获取企业id
		Integer enterpriseId = 1;
		QueryParameters params = new QueryParameters();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("enterpriseId", enterpriseId);
		params.addParams(param);
		int total = voiceDisplayNumService.count(params);
		if (total >= 10) {
			return JsonResp.fail("测试号码不能多于十个");
		}
		VoiceDisplayNum voiceDisplayNumResult = voiceDisplayNumService.getByNumber(voiceDisplayNum.getDisplayNum());
		if (voiceDisplayNumResult != null) {
			return JsonResp.fail(1, "号码已存在");
		}
		voiceDisplayNum.setEnterpriseId(enterpriseId);
		voiceDisplayNum.setCreateTime(new Date());
		voiceDisplayNum.setStateIdx(0);
		voiceDisplayNum = voiceDisplayNumService.save(voiceDisplayNum);
		if (!voiceDisplayNum.isSaveSuccess()) {
			File file=new File(FileUploadConfig.ROOT_FOLDER+ "\\"+voiceDisplayNum.getEnterpriseId()+"\\"+voiceDisplayNum.getCertifyFile());
			file.delete();
			// 删除上传的文件
			return JsonResp.fail(-1, "保存数据失败！");
		}
		return JsonResp.success();

	}

	@POST
	@Path("update")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp update(@RequestBody VoiceDisplayNum voiceDisplayNum) {
		// TODO 从session中获取企业id
		Integer enterpriseId = 1;
		if (!enterpriseId.equals(voiceDisplayNum.getEnterpriseId())) {
			return PageResp.fail(-1, "更新错误");
		}
		voiceDisplayNum.setStateIdx(0);
		voiceDisplayNum = voiceDisplayNumService.save(voiceDisplayNum);
		if (!voiceDisplayNum.isSaveSuccess()) {
			File file=new File(FileUploadConfig.ROOT_FOLDER+ "\\"+voiceDisplayNum.getEnterpriseId()+"\\"+voiceDisplayNum.getCertifyFile());
			file.delete();
			// 删除上传的文件
			return PageResp.fail(-1, "保存数据失败！");
		}
		return PageResp.success();

	}

}
