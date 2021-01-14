package com.weisenz.weiadmin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogUtil {
    //private static Log log = LogFactory.getLog(LogUtil.class);
    private static Logger log = LoggerFactory.getLogger("apires");

    //	private static Logger log = Logger.getLogger("apires");
    public static void error(String message) {
        log.error(message);
    }

    public static void error(String message, Throwable t) {
        log.error(message, t);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void info(String message, Throwable t) {
        log.info(message, t);
    }
}
