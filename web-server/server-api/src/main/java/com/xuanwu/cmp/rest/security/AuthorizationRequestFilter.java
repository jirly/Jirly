package com.xuanwu.cmp.rest.security;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.xuanwu.cmp.rest.reponse.ErrorRespEntityFactory;
import com.xuanwu.cmp.rest.security.error.ErrorHelper;
import com.xuanwu.cmp.rest.security.error.ErrorType;
import com.xuanwu.cmp.rest.security.error.IrestError;
import com.xuanwu.cmp.util.Constants;
import com.xuanwu.cmp.util.SignHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationRequestFilter.class);

    @Context
    private HttpServletRequest httpRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (!validateHeaders()) {
            IrestError irestError = ErrorHelper.genErrorRespEntity(ErrorType.INVALID_HEADS, ErrorHelper
                    .getCurrentLocal().get(), "");
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity(ErrorRespEntityFactory.genRespEntity(irestError))
                            .build());
            System.out.println(irestError);
        }

        if (Constants.SIGN_METHOD_HMAC_SHA1.equals(httpRequest.getParameter("sign_method"))) {
            // TODO
        }

        initLocal();

        String sigParam = httpRequest.getParameter("sign");
        if (StringUtils.isEmpty(sigParam)) {
            // TODO abortWith
        }

        String sourceAuth = SignHelper.decodeBase64(httpRequest.getHeader(HttpHeaders.AUTHORIZATION));
        Iterator<String> iterator = Splitter.on(":")
                .trimResults()
                .omitEmptyStrings()
                .split(sourceAuth).iterator();

        List<String> tempArrays = new ArrayList<>();
        for (; iterator.hasNext(); ) {
            String element = iterator.next();
            tempArrays.add(element);
        }

        if (tempArrays == null || tempArrays.size() < 2) {
            // TODO abortWith
        }

        String sid = tempArrays.get(0);
        String timestamp = tempArrays.get(1);

        // TODO if (timestamp) > current + 1min

        String token = loadToken(sid, timestamp);
        // Check token sign

        String joinStr = Joiner.on("").join(sid, token, timestamp);
        try {
            String sign = SignHelper.hmacSha1Hex(token, joinStr, "HmacSHA1");

            if (!sigParam.equals(sign)) {
                // TODO abortWith
            }
        } catch (Exception e) {
            logger.error("Do sign params with hmacSha1Hex occur error:{}", e.getMessage());
        }

    }

    private void initLocal() {
        String local = httpRequest.getParameter("local");
        if (!StringUtils.isEmpty(local) && Locale.ENGLISH.toString().equals(local)) {
            ErrorHelper.getCurrentLocal().set(Locale.ENGLISH);
        }
    }

    private String loadToken(String sid, String timestamp) {

        // TODO load from local cache
        // TODO locd from remote cache(redis)
        // TODO local from db

        return "abcdef";
    }

    private boolean validateHeaders() {
        if (StringUtils.isEmpty(httpRequest.getHeader(HttpHeaders.CONTENT_TYPE))) {
            return false;
        }
        if (StringUtils.isEmpty(httpRequest.getHeader(HttpHeaders.ACCEPT))) {
            return false;
        }
        if (StringUtils.isEmpty(httpRequest.getHeader(HttpHeaders.AUTHORIZATION))) {
            return false;
        }
        return true;
    }


}
