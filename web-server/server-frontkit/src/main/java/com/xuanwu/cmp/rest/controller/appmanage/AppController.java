package com.xuanwu.cmp.rest.controller.appmanage;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.App.AppState;
import com.xuanwu.cmp.domain.entity.App.AppType;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.dto.PageReqt;
import com.xuanwu.cmp.dto.PageResp;
import com.xuanwu.cmp.service.AppService;
import com.xuanwu.cmp.utils.PageInfo;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * @DescriptionAppController.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
@RestController
@Path("appmanage/app")
public class AppController {

	@Autowired
	private AppService appService;

	@POST
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp list(@Valid PageReqt req) {
		QueryParameters params = new QueryParameters();
		params.addParams(req.getParams());
		params.addParam("test", false);

		int total = appService.count(params);
		if (total == 0) {
			return PageResp.emptyResult();
		}

		PageInfo pageInfo = new PageInfo(req.getPage(), req.getCount(), total);
		params.setPage(pageInfo);
		Collection<App> apps = appService.list(params);
		return PageResp.success(total, apps);
	}

	@POST
	@Path("save")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp save(App app) {
		if (app.getId() == null || app.getId() == 0) {
			int enterpriseId = 1;// TODO 企业ID
			String identify = enterpriseId + "_" + app.getName() + "_" + System.currentTimeMillis();
			app.setId(0);
			app.setIdentify(DigestUtils.md2Hex(identify));
			app.setEnterpriseId(enterpriseId);
			app.setType(AppType.CLIENT);
			app.setState(AppState.OFFLINE);
			app.setCreateTime(new Date());
			app.setTest(false);
		}
		app = appService.save(app);
		if (!app.isSaveSuccess()) {
			return JsonResp.fail(-1, "保存数据失败！");
		}

		return JsonResp.success();
	}

	@POST
	@Path("del")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp delete(Integer[] ids) {
		int enterpriseId = 1;// TODO 企业ID

		if (ids == null || ids.length == 0) {
			return JsonResp.fail("请选择需要删除的用户！");
		}

		int count = 0;
		for (int id : ids) {
			count += appService.deleteById(id, enterpriseId);
		}
		if (count == 0) {
			return JsonResp.fail(-1, "删除数据失败！");
		}

		return JsonResp.success();
	}

	@POST
	@Path("updateState")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp updateState(App app) {
		int enterpriseId = 1;// TODO 企业ID

		AppState appState = app.getState();
		if (appState == null) {
			return JsonResp.fail("错误的状态类型！");
		}
		int count = appService.updateState(app.getId(), enterpriseId, appState);
		if (count == 0) {
			return JsonResp.fail(-1, "更改应用状态失败！");
		}

		return JsonResp.success();
	}

	@GET
	@Path("get")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp get(@QueryParam("id") Integer id) {
		int enterpriseId = 1;// TODO 企业ID

		App app = appService.get(id, enterpriseId);
		return JsonResp.success(app);
	}

}
