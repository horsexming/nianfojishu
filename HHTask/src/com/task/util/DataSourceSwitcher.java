package com.task.util;

/**
 * 数据源选择类
 * @author miraclerz
 */
import org.springframework.util.Assert;

public class DataSourceSwitcher {
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal contextHolder = new ThreadLocal();

	@SuppressWarnings("unchecked")
	public static void setDataSource(String dataSource) {
//		System.out.println(DataSourceSwitcher.getDataSource());
		Assert.notNull(dataSource, "dataSource cannot be null");
		contextHolder.set(dataSource);
	}

	public static void setMaster() {
		clearDataSource();
		//setDataSource("master");
	}

	public static void setSlave() {
		setDataSource("slave");
	}

	public static String getDataSource() {

		return (String) contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}
}