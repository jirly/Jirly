package com.xuanwu.cmp.rest.controller;

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
import com.xuanwu.cmp.rest.service.ApiService;
import com.xuanwu.cmp.service.AppService;
import com.xuanwu.cmp.service.PhraseService;
import com.xuanwu.cmp.utils.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * The rest api of voice verification code
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
@Component
@Path("voice_code")
public class VoiceCodeController {

    @Context
    private HttpServletRequest httpRequest;

    @Autowired
    AppService appService;

    @Autowired
    PhraseService phraseService;

    @Autowired
    private ApiService apiService;

    @GET
    public String ping() {
        return "Hello voice code!";
    }

    @POST
    @Path("/test/send")
    @Consumes({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Response testsend(@Valid VoiceCodeDTO voiceCodeDTO) {
        Optional<IrestError> irestErrorOptional;

        // TODO 企业的sid 不是 enterpriseId，后续需要调整

        final App app = apiService.getApp(voiceCodeDTO, true);
        irestErrorOptional = apiService.verifyApp(app);
        if (irestErrorOptional.isPresent()) {
            return apiService.getErrorResponse(irestErrorOptional.get());
        }

        // TODO 待确认 check ip
        irestErrorOptional = apiService.verifyIntrustIps(app.getTrustIps(), apiService.getIp(httpRequest));
        if (irestErrorOptional.isPresent()) {
            return apiService.getErrorResponse(irestErrorOptional.get());
        }

        // TODO 待确认 check enabler
        irestErrorOptional = apiService.verifyEnabler(app, PlatformEnabler.EnablerType.VOICE_CODE);
        if (irestErrorOptional.isPresent()) {
            return apiService.getErrorResponse(irestErrorOptional.get());
        }

        // load template
        final Phrase phrase = getPhrase(voiceCodeDTO, true);
        irestErrorOptional = apiService.verifyPhrase(phrase);
        if (irestErrorOptional.isPresent()) {
            return apiService.getErrorResponse(irestErrorOptional.get());
        }

        // TODO TODO send to the gate

        return apiService.getSuccessResponse();
    }


    @POST
    @Path("/send")
    @Consumes({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Response send(@Valid VoiceCodeDTO voiceCodeDTO) {

        Optional<IrestError> irestErrorOptional;

        final App app = apiService.getApp(voiceCodeDTO, false);
        irestErrorOptional = apiService.verifyApp(app);
        if (irestErrorOptional.isPresent()) {
            return apiService.getErrorResponse(irestErrorOptional.get());
        }

        // check ip
        irestErrorOptional = apiService.verifyIntrustIps(app.getTrustIps(), apiService.getIp(httpRequest));
        if (irestErrorOptional.isPresent()) {
            return apiService.getErrorResponse(irestErrorOptional.get());
        }

        // check enabler
        irestErrorOptional = apiService.verifyEnabler(app, PlatformEnabler.EnablerType.VOICE_CODE);
        if (irestErrorOptional.isPresent()) {
            return apiService.getErrorResponse(irestErrorOptional.get());
        }

        // load template
        final Phrase phrase = getPhrase(voiceCodeDTO, false);
        irestErrorOptional = apiService.verifyPhrase(phrase);
        if (irestErrorOptional.isPresent()) {
            return apiService.getErrorResponse(irestErrorOptional.get());
        }


        // TODO send to the gate


        return apiService.getSuccessResponse();
    }


    private Phrase getPhrase(final VoiceCodeDTO voiceCodeDTO, boolean isTest) {
        QueryParameters params = new QueryParameters();
        params.addParam("appId", voiceCodeDTO.getApp_id());
        params.addParam("enterpriseId", voiceCodeDTO.getSid());
        params.addParam("test", isTest);
        params.addParam("state", Phrase.PhraseState.IS_CHECKED.getValue());
        params.addParam("type", Phrase.PhraseType.VOICE_VERIFICATION_CODE.getValue());
        return phraseService.findByEnterpriseApp(params);
    }

}
