package com.xuanwu.cmp.rest.controller.basicdata;

import java.util.Collection;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.xuanwu.cmp.domain.entity.Blacklist;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.dto.PageReqt;
import com.xuanwu.cmp.dto.PageResp;
import com.xuanwu.cmp.service.BlacklistService;
import com.xuanwu.cmp.utils.PageInfo;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * Created by 林泽强 on 2016/8/16.
 */
@RestController
@Path("basicdata/blacklist")
public class BlacklistController {

	@Autowired
	private BlacklistService blacklistService;

	@POST
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp list(@Valid PageReqt req) {
		QueryParameters params = new QueryParameters();
		params.addParams(req.getParams());

		int total = blacklistService.count(params);
		if (total == 0) {
			return PageResp.emptyResult();
		}

		PageInfo pageInfo = new PageInfo(req.getPage(), req.getCount(), total);
		params.setPage(pageInfo);
		Collection<Blacklist> apps = blacklistService.list(params);
		return PageResp.success(total, apps);
	}
}
