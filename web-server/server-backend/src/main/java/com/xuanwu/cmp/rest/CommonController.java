package com.xuanwu.cmp.rest;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.xuanwu.cmp.domain.entity.Region;
import com.xuanwu.cmp.service.RegionService;
import com.xuanwu.cmp.utils.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.xuanwu.cmp.domain.entity.Carrier;
import com.xuanwu.cmp.domain.entity.PlatformEnabler;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.service.CarrierService;
import com.xuanwu.cmp.service.PlatformEnablerService;

/**
 * @Description CommonController
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
	private CarrierService carrierService;

	@Autowired
	private RegionService regionService;

	// 获取平台能力列表
	@GET
	@Path("enablers")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getEnablers() {
		Collection<PlatformEnabler> enablers = platformEnablerService.list(null);
		return JsonResp.success(enablers);
	}

	@GET
	@Path("carriers")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getCarriers() {
		Collection<Carrier> carriers = carrierService.list(null);
		return JsonResp.success(carriers);
	}

	@GET
	@Path("provinces")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getProvinces() {
		QueryParameters queryParameters = new QueryParameters();
		queryParameters.addParam("type",'0');
		Collection<Region> regions = regionService.list(queryParameters);
		return JsonResp.success(regions);
	}
}
