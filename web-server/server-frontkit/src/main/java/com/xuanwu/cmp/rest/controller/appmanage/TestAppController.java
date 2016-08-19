package com.xuanwu.cmp.rest.controller.appmanage;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.domain.entity.UserTestNum;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.dto.PageResp;
import com.xuanwu.cmp.service.UserTestNumService;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * @Description TestAppController.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
@RestController
@Path("appmanage/testapp")
public class TestAppController {

	@Autowired
	private UserTestNumService userTestNumService;

	private final String[] codes = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A",
			"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };

	private static Map<String, String> session = new HashMap<String, String>();

	@GET
	@Path("getTestApp")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getTestApp() {
		// TODO 从session中获取企业id
		Integer enterpriseId = 1;
		App app = userTestNumService.getTestApp(enterpriseId);
		if (app == null) {
			return JsonResp.fail("系统错误");
		}
		return JsonResp.success(app);
	}

	@GET
	@Path("getTestPhrase")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getTestPhrase() {
		Collection<Phrase> phrases = userTestNumService.getTestPhrases();
		if (phrases == null) {
			return JsonResp.fail("系统错误");
		}
		return JsonResp.success(phrases);
	}

	@POST
	@Path("getUsrtTestNumList")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getUserTestNumList() {
		// TODO 从session中获取企业id
		Integer enterpriseId = 1;
		QueryParameters params = new QueryParameters();
		params.addParam("enterpriseId", enterpriseId);
		int total = userTestNumService.count(params);
		if (total == 0) {
			return PageResp.emptyResult();
		}
		Collection<UserTestNum> userTestNums = userTestNumService.list(params);
		return PageResp.success(total, userTestNums);
	}

	@POST
	@Path("del")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp delete(Integer[] ids) {
		// TODO 从session中获取企业id
		Integer enterpriseId = 1;
		if (ids == null || ids.length == 0) {
			return JsonResp.fail("请选择需要删除的用户！");
		}
		int count = 0;
		for (Integer id : ids) {
			count += userTestNumService.deleteById(id, enterpriseId);
		}

		if (count == 0) {
			return JsonResp.fail(-1, "删除数据失败！");
		}
		return JsonResp.success();
	}

	@GET
	@Path("getCode")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getCode(@QueryParam(value = "number")String number,@QueryParam(value = "action")String action) {
		UserTestNum checkNumExist = userTestNumService.getByNumber(number);
		if (checkNumExist != null) {
			return JsonResp.fail("号码已存在！");
		}

		// TODO 从session中获取企业id
		Integer enterpriseId = 1;
		QueryParameters params = new QueryParameters();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("enterpriseId", enterpriseId);
		params.addParams(param);
		int total = userTestNumService.count(params);
		if (total >= 3) {
			return JsonResp.fail("测试号码不能多于三个！");
		}
		// TODO 判断action 1-短信 2-语音
		Random rand = new Random();
		String code = "";
		for (int i = 0; i < 4; i++) {
			int index = rand.nextInt(codes.length - 1);
			code += codes[index];
		}
		// 暂时打印到控制台
		System.out.println(code);
		// TODO 验证码存储到 gsms_verification_code 尚未建立
		session.put(number, code);
		return JsonResp.success();
	}

	@POST
	@Path("save")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp saveUserTestNum(UserTestNum userTestNum) {
		// TODO 从session中获取企业id
		Integer enterpriseId = 1;
		String code = session.get(userTestNum.getNumber());
		if (code == null) {
			return JsonResp.fail("请先发送验证码！");
		}

		if (!code.equals(userTestNum.getCode())) {
			return JsonResp.fail("验证码错误！");
		}

		userTestNum.setEnterpriseId(enterpriseId);
		userTestNum.setCreateTime(new Date());
		userTestNum = userTestNumService.save(userTestNum);
		if (!userTestNum.isSaveSuccess()) {
			return JsonResp.fail(-1, "保存数据失败！");
		}
		session.remove(userTestNum.getNumber());
		return PageResp.success();
	}

}
