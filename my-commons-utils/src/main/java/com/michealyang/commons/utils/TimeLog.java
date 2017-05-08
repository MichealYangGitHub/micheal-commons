package com.michealyang.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by michealyang on 17/5/8.
 */
public class TimeLog {
    private static final Logger logger = LoggerFactory.getLogger(TimeLog.class);

    public static void log(String func, String desc, long start) {
        StringBuilder sb = new StringBuilder();
        sb.append("[TIME]").append(func).append(" ").append(desc).append(": [{}] ms");
        logger.info(sb.toString(), System.currentTimeMillis() - start);
    }
}
