package com.xuanwu.cmp.rest.controller.channelmgmt;

import com.xuanwu.cmp.domain.entity.Carrier;
import com.xuanwu.cmp.domain.entity.CarrierChannel;
import com.xuanwu.cmp.domain.entity.Region;
import com.xuanwu.cmp.dto.JsonResp;
import com.xuanwu.cmp.dto.PageReqt;
import com.xuanwu.cmp.dto.PageResp;
import com.xuanwu.cmp.service.ChannelInfoService;
import com.xuanwu.cmp.utils.PageInfo;
import com.xuanwu.cmp.utils.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通道基本信息 ChannelInfoController
 * @author <a href="jiangziyuan@wxchina.com">ZiYuan.Jiang</a>
 * @date 2016-8-11
 * @version 1.0.0
 */
@RestController
@Path("channelmgmt/info")
public class ChannelInfoController {
    @Autowired
    private ChannelInfoService channelInfoService;

    @POST
    @Path("list")
    @Produces({ MediaType.APPLICATION_JSON })
    public JsonResp list(@Valid PageReqt req) {
        QueryParameters params = new QueryParameters();
        params.addParams(req.getParams());

        //int total = channelInfoService.count(params);//以后再改
        int total = 10;
/*        if (total == 0) {
            return PageResp.emptyResult();
        }*/

        PageInfo pageInfo = new PageInfo(req.getPage(), req.getCount(), total);
        params.setPage(pageInfo);
        List<CarrierChannel> info = channelInfoService.list(params);
        return PageResp.success(total, info);
    }

    @POST
    @Path("save")
    @Produces({ MediaType.APPLICATION_JSON })
    public JsonResp save(@Valid CarrierChannel carrierChannel) {
        carrierChannel = channelInfoService.save(carrierChannel);
        if (!carrierChannel.isSaveSuccess()) {
            return JsonResp.fail(-1, "保存数据失败！");
        }

        return PageResp.success();
    }

    @POST
    @Path("del")
    @Produces({ MediaType.APPLICATION_JSON })
    public JsonResp delete(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return JsonResp.fail("请选择需要删除的用户！");
        }

        int count = 0;
        for (int id : ids) {
            //count += channelInfoService.deleteById(id);
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
        CarrierChannel carrierChannel = channelInfoService.get(id, enterpriseId);
        return JsonResp.success(carrierChannel);
    }

    @GET
    @Path("regions")
    @Produces({ MediaType.APPLICATION_JSON })
    public JsonResp getRegions() {
        List<Region> regions = channelInfoService.findAllRegions();
        return JsonResp.success(regions);
    }

    @GET
    @Path("regions/initCity/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public JsonResp getCitys(@PathParam("id") Integer id) {
        QueryParameters params = new QueryParameters();
        Map<String, Object> myParams = new HashMap<>();
        myParams.put("parentId",id);
        params.addParams(myParams);
        List<Region> citys = channelInfoService.findAllCitys(params);
        return JsonResp.success(citys);
    }
}
