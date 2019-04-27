package com.task.util;

/**
 * 数据源动态切换类
 * @author miraclerz
 */
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		/* System.out.println("dataSource:"+DataSourceSwitcher.getDataSource()); */
		String d = DataSourceSwitcher.getDataSource();
		System.out.println(d);
		DataSourceSwitcher.clearDataSource();
		return d;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
