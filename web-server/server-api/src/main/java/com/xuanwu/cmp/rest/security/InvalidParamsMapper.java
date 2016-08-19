package com.xuanwu.cmp.rest.security;

import com.xuanwu.cmp.rest.reponse.entity.ErrorRespEntity;
import com.xuanwu.cmp.rest.reponse.ErrorRespEntityFactory;
import com.xuanwu.cmp.rest.security.error.ErrorHelper;
import com.xuanwu.cmp.rest.security.error.ErrorType;
import com.xuanwu.cmp.rest.security.error.IrestError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;

/**
 * If the parameter check is not passed, will unified throw an error reponse
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
@Provider
public class InvalidParamsMapper implements ExceptionMapper<ConstraintViolationException> {

    /**
     * log for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationRequestFilter.class);

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> set = exception.getConstraintViolations();

        if (exception instanceof ConstraintViolationException) {
            final StringBuilder strBuilder = new StringBuilder();
            for (ConstraintViolation invalidValue : set) {
                if (strBuilder.length() > 1) {
                    strBuilder.append("|");
                }
                strBuilder.append(invalidValue.getMessage());
            }
            if (logger.isDebugEnabled()) {
                logger.debug("invalidValue info : {}", strBuilder.toString());
            }

            IrestError error = ErrorHelper.genErrorRespEntity(ErrorType.INVALID_ARGUMENTS,
                    ErrorHelper.getCurrentLocal().get(), strBuilder.toString());
            ErrorRespEntity errorRespEntity = ErrorRespEntityFactory.genRespEntity(error);

            return Response.status(Response.Status.BAD_REQUEST).entity(errorRespEntity).build();
        }

        return Response.serverError().entity(exception.getMessage()).build();
    }
}
