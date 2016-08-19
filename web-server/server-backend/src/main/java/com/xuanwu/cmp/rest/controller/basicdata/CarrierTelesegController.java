package com.xuanwu.cmp.rest.controller.basicdata;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.xuanwu.cmp.domain.entity.CarrierTeleseg;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.dto.PageReqt;
import com.xuanwu.cmp.dto.PageResp;
import com.xuanwu.cmp.service.CarrierTelesegService;
import com.xuanwu.cmp.utils.PageInfo;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * Created by 林泽强 on 2016/8/10.
 */
@RestController
@Path("basicdata/carrierteleseg")
public class CarrierTelesegController {

	@Autowired
	private CarrierTelesegService carrierTelesegService;

	@POST
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp list(@Valid PageReqt req) {
		QueryParameters params = new QueryParameters();
		params.addParams(req.getParams());
		params.addSorts(req.getSorts());

		int total = carrierTelesegService.count(params);
		if (total == 0) {
			return PageResp.emptyResult();
		}

		PageInfo pageInfo = new PageInfo(req.getPage(), req.getCount(), total);
		params.setPage(pageInfo);
		Collection<CarrierTeleseg> apps = carrierTelesegService.list(params);
		return PageResp.success(total, apps);
	}

	@POST
	@Path("save")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp save(@Valid CarrierTeleseg carrierTeleseg) {
		carrierTeleseg.setShowed(1);
		carrierTeleseg.setHandleTime(new Date());
		carrierTeleseg = carrierTelesegService.save(carrierTeleseg);
		if (!carrierTeleseg.isSaveSuccess()) {
			return JsonResp.fail(-1, "保存数据失败！");
		}

		return PageResp.success();
	}

	@POST
	@Path("del")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp delete(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			return JsonResp.fail("请选择需要删除的运营商号段！");
		}

		int count = 0;
		for (int id : ids) {
			count += carrierTelesegService.deleteById(id, 0);
		}
		if (count == 0) {
			return JsonResp.fail(-1, "删除数据失败！");
		}

		return JsonResp.success();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getCarrierTeleseg(@PathParam("id") Integer id) {
		CarrierTeleseg app = carrierTelesegService.get(id, 0);
		return JsonResp.success(app);
	}
}
