package com.xuanwu.cmp.rest.controller.phrase;

import com.xuanwu.cmp.domain.entity.PhraseSample;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.dto.PageReqt;
import com.xuanwu.cmp.service.PhraseSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Description PhraseSampleController
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-07-07
 * @version 1.0.0
 */
@RestController
@Path("phrase/sample")
public class PhraseSampleController {

	@Autowired
	private PhraseSampleService phraseSampleService;

	@GET
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp listSample(PageReqt req) {
		List<PhraseSample> users = phraseSampleService.findAll();
		return JsonResp.success(users);
	}

	@GET
	@Path("get/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public JsonResp getPhraseSample(@PathParam("id") Integer id) {
		PhraseSample phraseSample = phraseSampleService.get(id);
		return JsonResp.success(phraseSample);
	}



}
