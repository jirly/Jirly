package com.xuanwu.cmp.rest.controller;

import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.Phrase;
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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * The rest api of voice notify
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
@Component
@Path("voice_msg")
public class VoiceMsgController {

    @Context
    private HttpServletRequest httpRequest;

    @Autowired
    AppService appService;

    @Autowired
    PhraseService phraseService;

    @Autowired
    private ApiService apiService;

    @POST
    @Path("/send")
    @Consumes({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Response send(@Valid VoiceCodeDTO voiceCodeDTO) {


        return apiService.getSuccessResponse();
    }

}
