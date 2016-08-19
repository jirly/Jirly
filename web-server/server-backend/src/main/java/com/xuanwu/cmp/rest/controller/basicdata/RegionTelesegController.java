package com.xuanwu.cmp.rest.controller.basicdata;

import java.util.Collection;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.xuanwu.cmp.domain.entity.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.xuanwu.cmp.domain.entity.RegionCarrierMap;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.dto.PageReqt;
import com.xuanwu.cmp.dto.PageResp;
import com.xuanwu.cmp.service.RegionCarrierMapService;
import com.xuanwu.cmp.utils.PageInfo;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * Created by 林泽强 on 2016/8/15.
 */
@RestController
@Path("basicdata/regionteleseg")
public class RegionTelesegController {

	@Autowired
	private RegionCarrierMapService regionCarrierMapService;

	@POST
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp list(@Valid PageReqt req) {
		QueryParameters params = new QueryParameters();
		params.addParams(req.getParams());
		params.addSorts(req.getSorts());

		Integer total = regionCarrierMapService.count(params);
		if (total == 0) {
			return PageResp.emptyResult();
		}

		PageInfo pageInfo = new PageInfo(req.getPage(), req.getCount(), total);
		params.setPage(pageInfo);
		Collection<RegionCarrierMap> apps = regionCarrierMapService.list(params);
		return PageResp.success(total, apps);
	}

	@POST
	@Path("save")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp save(@Valid RegionCarrierMap regionCarrierMap) {
		regionCarrierMap = regionCarrierMapService.save(regionCarrierMap);
		if (!regionCarrierMap.isSaveSuccess()) {
			return JsonResp.fail(-1, "保存数据失败！");
		}
		return PageResp.success();
	}


	@POST
	@Path("del")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp delete(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			return JsonResp.fail("请选择需要删除的区域号段！");
		}

		int count = 0;
		for (int id : ids) {
			count += regionCarrierMapService.deleteById(id, 0);
		}
		if (count == 0) {
			return JsonResp.fail(-1, "删除数据失败！");
		}

		return JsonResp.success();
	}

	@GET
	@Path("get")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp get(@QueryParam("id") Integer id) {
		int enterpriseId = 1;// TODO 企业ID

		RegionCarrierMap regionCarrierMap = regionCarrierMapService.get(id, enterpriseId);
		return JsonResp.success(regionCarrierMap);
	}
}
