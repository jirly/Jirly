package com.xuanwu.cmp.rest.controller.basicdata;

import com.xuanwu.cmp.domain.entity.CarrierTeleseg;
import com.xuanwu.cmp.domain.entity.Keyword;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.dto.PageReqt;
import com.xuanwu.cmp.dto.PageResp;
import com.xuanwu.cmp.service.KeywordService;
import com.xuanwu.cmp.utils.PageInfo;
import com.xuanwu.cmp.utils.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Date;

/**
 * Created by 林泽强 on 2016/8/17.
 */
@RestController
@Path("basicdata/illegalkey")
public class IllegalkeyController {

    @Autowired
    private KeywordService keywordService;

    @POST
    @Path("list")
    @Produces({ MediaType.APPLICATION_JSON })
    public JsonResp list(@Valid PageReqt req) {
        QueryParameters params = new QueryParameters();
        params.addParams(req.getParams());

        int total = keywordService.count(params);
        if (total == 0) {
            return PageResp.emptyResult();
        }

        PageInfo pageInfo = new PageInfo(req.getPage(), req.getCount(), total);
        params.setPage(pageInfo);
        Collection<Keyword> keywords = keywordService.list(params);
        return PageResp.success(total, keywords);
    }

    @POST
    @Path("save")
    @Produces({ MediaType.APPLICATION_JSON })
    public JsonResp save(@Valid Keyword illegalkey) {
        illegalkey.setRemoved(false);
        illegalkey.setHandleTime(new Date());
        illegalkey.setTargetId(0);
        illegalkey = keywordService.save(illegalkey);
        if (!illegalkey.isSaveSuccess()) {
            return JsonResp.fail(-1, "保存数据失败！");
        }

        return PageResp.success();
    }
}
