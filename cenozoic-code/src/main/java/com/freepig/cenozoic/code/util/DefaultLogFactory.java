package com.freepig.cenozoic.code.util;

import org.apache.log4j.Logger;

public class DefaultLogFactory implements LogFactory {

	private final static DefaultLogFactory newInstance = new DefaultLogFactory();

	private final Logger logger = Logger.getLogger(this.getClass());

	public final static DefaultLogFactory newInstance() {
		return newInstance;
	}

	@SuppressWarnings("static-access")
	private Logger getLogger(Class<?> clazz) {
		return logger.getLogger(clazz);
	}

	public void debug(Class<?> clazz, String message) {
		getLogger(clazz).debug(message);

	}

	public void debug(Class<?> clazz, String message, Throwable e) {
		getLogger(clazz).debug(message, e);
	}

	public void info(Class<?> clazz, String message) {
		getLogger(clazz).info(message);
	}

	public void info(Class<?> clazz, String message, Throwable e) {
		getLogger(clazz).info(message, e);
	}

	public void warn(Class<?> clazz, String message) {
		getLogger(clazz).warn(message);
	}

	public void warn(Class<?> clazz, String message, Throwable e) {
		getLogger(clazz).warn(message, e);
	}

	public void error(Class<?> clazz, String message, Throwable e) {
		getLogger(clazz).error(message, e);
	}

	public void fatal(Class<?> clazz, String message, Throwable e) {
		getLogger(clazz).fatal(message, e);
		e.printStackTrace();
	}

	public void error(Class<?> clazz, String message) {
		getLogger(clazz).error(message);
	}

	public void fatal(Class<?> clazz, String message) {
		getLogger(clazz).fatal(message);
	}

}
