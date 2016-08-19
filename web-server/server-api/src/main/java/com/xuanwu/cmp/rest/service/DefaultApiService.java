package com.xuanwu.cmp.rest.service;

import com.google.common.base.Optional;
import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.domain.entity.PlatformEnabler;
import com.xuanwu.cmp.rest.dto.VoiceCodeDTO;
import com.xuanwu.cmp.rest.reponse.ErrorRespEntityFactory;
import com.xuanwu.cmp.rest.reponse.SuccessRespEntityFactory;
import com.xuanwu.cmp.rest.security.error.ErrorHelper;
import com.xuanwu.cmp.rest.security.error.ErrorType;
import com.xuanwu.cmp.rest.security.error.IrestError;
import com.xuanwu.cmp.service.AppService;
import com.xuanwu.cmp.utils.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 * api service impl
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
@Service
public class DefaultApiService implements ApiService {

    @Autowired
    AppService appService;

    public App getApp(final VoiceCodeDTO voiceCodeDTO, boolean isTest) {
        QueryParameters params = new QueryParameters();
        params.addParam("identify", voiceCodeDTO.getApp_id());
        params.addParam("test", isTest);
        params.addParam("state", App.AppState.ONLINE.getIndex());
        params.addParam("enterpriseId", voiceCodeDTO.getSid());
        return appService.getByIdentify(params);
    }

    public Optional<IrestError> verifyEnabler(final App app, final PlatformEnabler.EnablerType enablerType) {
        Optional<IrestError> irestErrorOptional = Optional.absent();
        boolean flag = true;
        for (int i : app.getEnablers()) {
            if (enablerType.getIndex() == i) {
                flag = false;
            }
        }
        if (flag) {
            IrestError error = ErrorHelper.genErrorRespEntity(ErrorType.WITHOUT_ENABLER, ErrorHelper
                    .getCurrentLocal().get(), "ability-" + enablerType.getIndex());
            irestErrorOptional = Optional.of(error);
        }
        return irestErrorOptional;
    }

    public Optional<IrestError> verifyApp(App app) {
        Optional<IrestError> irestErrorOptional = Optional.absent();
        if (app == null) {
            IrestError error = ErrorHelper.genErrorRespEntity(ErrorType.BUSINESS_LOGIC_ERROR, ErrorHelper
                    .getCurrentLocal().get(), "can't find running app by app_id, sid");
            irestErrorOptional = Optional.of(error);
        }
        return irestErrorOptional;
    }

    public Optional<IrestError> verifyIntrustIps(final String[] trustIps, final String requestIp) {
        boolean flag = true;
        for (String ip : trustIps) {
            if (requestIp.equals(ip)) {
                flag = false;
            }
        }

        Optional<IrestError> irestErrorOptional = Optional.absent();
        if (flag) {
            IrestError error = ErrorHelper.genErrorRespEntity(ErrorType.NON_WHITE_LIST_IP, ErrorHelper
                    .getCurrentLocal().get(), requestIp);
            irestErrorOptional = Optional.of(error);
        }
        return irestErrorOptional;
    }

    public Optional<IrestError> verifyPhrase(Phrase phrase) {
        Optional<IrestError> irestErrorOptional = Optional.absent();
        if (phrase == null) {
            IrestError error = ErrorHelper.genErrorRespEntity(ErrorType.BUSINESS_LOGIC_ERROR, ErrorHelper
                    .getCurrentLocal().get(), "no valid phrase found");
            irestErrorOptional = Optional.of(error);
        }
        return irestErrorOptional;
    }

    /**
     * Get request ip
     *
     * @return ip
     */
    public String getIp(final HttpServletRequest httpRequest) {
        String ip = httpRequest.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpRequest.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpRequest.getRemoteAddr();
        }
        return ip;
    }

    public Response getErrorResponse(IrestError error) {
        // @formatter:off
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ErrorRespEntityFactory.genRespEntity(error))
                .build();
        // @formatter:on
    }

    public Response getSuccessResponse() {
        // @formatter:off
        return Response
                .status(Response.Status.OK)
                .entity(SuccessRespEntityFactory.getSuccessRespEntityFactory().genRespEntity())
                .build();
        // @formatter:on
    }

}
