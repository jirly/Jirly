package com.xuanwu.cmp.rest.controller.phrase;

import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.domain.entity.PhraseAuditMaterial;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.dto.PageReqt;
import com.xuanwu.cmp.dto.PageResp;
import com.xuanwu.cmp.service.PhraseService;
import com.xuanwu.cmp.utils.PageInfo;
import com.xuanwu.cmp.utils.QueryParameters;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description VoicePhraseController
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-07-07
 * @version 1.0.0
 */
@RestController
@Path("phrase/voice")
public class VoicePhraseController {

	@Autowired
	private PhraseService phraseService;

	@POST
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	public PageResp listVoice(PageReqt req) {
		// TODO: 2016/8/12 添加企业条件信息
		QueryParameters params = new QueryParameters();
		params.addParams(req.getParams());
		params.addParam("enterpriseId", "1");
		params.addParam("parentType",PhraseContacts.PARENT_TYPE_VOICE);
		int total = phraseService.count(params);
		if (total == 0) {
			return PageResp.emptyResult();
		}
		PageInfo pageInfo = new PageInfo(req.getPage(), req.getCount(), total);
		params.setPage(pageInfo);
		Collection<Phrase> phrases = phraseService.list(params);
		return PageResp.success(total, phrases);
	}



	@POST
	@Path("save")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp save(Phrase phrase) {
		// TODO: 2016/8/16 判断账户余额是否大于500
		// TODO: 2016/8/12 获取企业ID
		// TODO: 模板格式后台校验
		Integer enterpriseId = 1;
		if (phrase != null) {
			//默认属性
			if(Phrase.PhraseType.VOICE_NOTIFICATION == phrase.getType()){
				phrase.setMsgType(Phrase.PhraseMsgType.VOICE_NOTIFICATION);
			}else if(Phrase.PhraseType.VOICE_VERIFICATION_CODE == phrase.getType()){
				phrase.setMsgType(Phrase.PhraseMsgType.VOICE_VERIFICATION_CODE);
				if (phrase.getAppType() == PhraseAuditMaterial.AppType.OFFLINE ) { //未上线应用url置空
					phrase.setAppUrl(null);
				}
				if (phrase.getAppType() == PhraseAuditMaterial.AppType.ONLINE ) { //上线应用appLogo,appVerifyPage截图置为空
					phrase.setAppLogo(null);
					phrase.setAppVerifyPage(null);
				}
			}
			String identify = enterpriseId + "_" + phrase.getTitle() + "_" + System.currentTimeMillis();
			phrase.setIdentify(DigestUtils.md2Hex(identify));
			phrase.setIsSMS(Boolean.FALSE);
			phrase.setState(Phrase.PhraseState.CHECKING);
			phrase.setIsTest(Boolean.FALSE);
			phrase.setIsRemove(Boolean.FALSE);
			phrase.setEnterpriseId(enterpriseId);
			phraseService.saveWithSignAndAuditMaterial(phrase);
		}
		return PageResp.success();
	}

	@POST
	@Path("del")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp del(Integer[] ids) {
		// TODO: 2016/8/17
		Integer enterpriseId = 1;
		if (ids == null || ids.length == 0) {
			return JsonResp.fail("请选择需要删除的模板！");
		}

		int count = 0;
		for (int id : ids) {
			count += phraseService.remove(id, enterpriseId);
		}
		if (count == 0) {
			return JsonResp.fail(-1, "删除数据失败！");
		}
		return JsonResp.success();
	}


	@GET
	@Path("detail/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getPhrase(@PathParam("id") Integer id) {
		// TODO: 2016/8/17
		Integer enterpriseId = 1;
		Phrase phrase = phraseService.get(id, enterpriseId);
		return JsonResp.success(phrase);
	}

	@GET
	@Path("identifycomplete/{p}")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp identifyComplete(@PathParam("p") String p) {
		// TODO: 2016/8/17
		Integer enterpriseId = 1;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("identify", p);
		params.put("enterpriseId", enterpriseId);
		params.put("parentType", PhraseContacts.PARENT_TYPE_VOICE);
		List<Phrase> identifys = phraseService.getIdentifys(params);
		return JsonResp.success(identifys);
	}

}
