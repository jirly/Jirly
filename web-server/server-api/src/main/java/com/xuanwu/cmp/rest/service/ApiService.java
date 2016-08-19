package com.xuanwu.cmp.rest.service;

import com.google.common.base.Optional;
import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.domain.entity.PlatformEnabler;
import com.xuanwu.cmp.rest.dto.VoiceCodeDTO;
import com.xuanwu.cmp.rest.security.error.IrestError;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 * service for the controller
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */

public interface ApiService {

    App getApp(final VoiceCodeDTO voiceCodeDTO, boolean isTest);

    Optional<IrestError> verifyEnabler(final App app, final PlatformEnabler.EnablerType enablerType);

    Optional<IrestError> verifyIntrustIps(final String[] trustIps, final String requestIp);

    Optional<IrestError> verifyApp(App app);

    Optional<IrestError> verifyPhrase(Phrase phrase);

    /**
     * Get request ip
     *
     * @return ip
     */
    String getIp(final HttpServletRequest httpRequest);

    Response getErrorResponse(IrestError error);

    Response getSuccessResponse();
}
