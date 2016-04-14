package com.freepig.cenozoic.code.util;

public interface LogFactory {

	public void debug(Class<?> clazz, String msg);

	public void debug(Class<?> clazz, String msg, Throwable e);

	public void info(Class<?> clazz, String msg);

	public void info(Class<?> clazz, String msg, Throwable e);

	public void warn(Class<?> clazz, String msg);

	public void warn(Class<?> clazz, String msg, Throwable e);

	public void error(Class<?> clazz, String msg);

	public void error(Class<?> clazz, String msg, Throwable e);

	public void fatal(Class<?> clazz, String msg);

	public void fatal(Class<?> clazz, String msg, Throwable e);

}
