package com.xuanwu.cmp.rest.security.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.util.Assert;

import java.util.Locale;

/**
 * The error helper
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
public class ErrorHelper {

    private ErrorHelper() {
    }

    /**
     * init chinese simple local
     */
    private static ThreadLocal<Locale> currentLocal = new ThreadLocal<Locale>() {
        protected Locale initialValue() {
            return Locale.SIMPLIFIED_CHINESE;
        }
    };

    /**
     * log for this class
     */
    private static Logger logger = LoggerFactory.getLogger(ErrorHelper.class);

    /**
     * Helper class for easy access to messages from a Internationalization error information
     */
    private static MessageSourceAccessor errorMessageSourceAccessor;

    /**
     * code perfix
     */
    private static final String ERROR_CODE_PREFIX = "ERROR_";

    /**
     * suggest subfix which append at the code value
     */
    private static final String ERROR_SUGGEST_SUBFIX = "_SUGGEST";

    /**
     * Get the RestError, and replace the error messgae
     *
     * @param errorType ErrorType
     * @param locale    locale
     * @param params    Array params
     * @return ErrorRespEntity
     */
    public static RestError genErrorRespEntity(final ErrorType errorType, final Locale locale, Object... params) {
        final String errorMessage = getErrorMessage(ERROR_CODE_PREFIX + errorType.value(), locale, params);
        final String errorSuggest = getErrorSuggest(ERROR_CODE_PREFIX + errorType.value() + ERROR_SUGGEST_SUBFIX,
                locale);
        return RestError.getRestError(errorType.value(), errorMessage, errorSuggest);
    }

    /**
     * Load the error msg from the resource
     *
     * @param code   the code of errorType
     * @param locale locale
     * @param params Array params
     * @return error msg
     */
    private static String getErrorMessage(final String code, final Locale locale, Object... params) {
        try {
            Assert.notNull(errorMessageSourceAccessor, "Please set up the error msg for internationalized " +
                    "resource.");
            return errorMessageSourceAccessor.getMessage(code, params, locale);
        } catch (NoSuchMessageException e) {
            logger.error("The corresponding error key does not exist：{}，please check whether in i18n/error_tag/error " +
                    "resources", code);
            throw e;
        }
    }

    /**
     * Load the error suggest from the resource
     *
     * @param code   the code of errorType
     * @param locale locale
     * @return error msg
     */
    private static String getErrorSuggest(final String code, final Locale locale) {
        try {
            Assert.notNull(errorMessageSourceAccessor, "Please set an international resource for error suggestion.");
            return errorMessageSourceAccessor.getMessage(code, new Object[]{}, locale);
        } catch (NoSuchMessageException e) {
            logger.error("The corresponding error key does not exist：{}，please check whether in i18n/error_tag/error " +
                    "resources", code);
            throw e;
        }
    }

    /**
     * when set up the server, will autowired errorMessageSourceAccessor
     *
     * @param errorMessageSourceAccessor MessageSourceAccessor
     */
    public static void setErrorMessageSourceAccessor(final MessageSourceAccessor errorMessageSourceAccessor) {
        ErrorHelper.errorMessageSourceAccessor = errorMessageSourceAccessor;
    }

    /**
     * get current Local
     *
     * @return Locale
     */
    public static ThreadLocal<Locale> getCurrentLocal() {
        return currentLocal;
    }
}
