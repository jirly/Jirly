package com.xuanwu.cmp.rest.security;

import com.xuanwu.cmp.rest.security.error.ErrorHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Load Init IrestError Tags
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
@Component
@Order(value = 1)
public class LoadInitErrorTagRunner implements CommandLineRunner {

    /**
     * resource location
     */
    private static final String I18N_ROP_ERROR = "i18n/error_tag/error";

    /**
     * log for this class
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * init loading msg source
     */
    private void initMessageSource() {
        HashSet<String> baseNamesSet = new HashSet<>();
        baseNamesSet.add(I18N_ROP_ERROR);
        String[] totalBaseNames = baseNamesSet.toArray(new String[0]);
        if (logger.isInfoEnabled()) {
            logger.info("Load international error code resources succes：{}", StringUtils.join(totalBaseNames, ","));
        }
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setBasenames(totalBaseNames);
        MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(bundleMessageSource);
        ErrorHelper.setErrorMessageSourceAccessor(messageSourceAccessor);
    }

    @Override
    public void run(String... strings) throws Exception {
        initMessageSource();
    }
}
