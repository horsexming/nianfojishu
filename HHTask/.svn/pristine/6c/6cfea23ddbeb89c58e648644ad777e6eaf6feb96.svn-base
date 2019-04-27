package com.task.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestLog4j {

	protected final static Log log = LogFactory.getLog(TestLog4j.class);

	public static void main(String[] args) {
		if (log.isDebugEnabled()) {
			log.debug("111");
		}
		if (log.isInfoEnabled()) {
			log.info("222");
		}
		if (log.isWarnEnabled()) {
			log.warn("333");
		}
		if (log.isErrorEnabled()) {
			log.error("444");
		}
		if (log.isFatalEnabled()) {
			log.fatal("555");
		}
		// [%p] [%d{yyy-MM-dd HH:mm:ss}] -File:[%c] -Line:[%l] -Message[%m] %n
	}
}
