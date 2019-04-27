package com.task.Server;

import java.util.List;

import com.task.entity.ZhFlow;
import com.task.entity.ZhMetric;

public interface MetricService {
	public void add(ZhMetric metric);

	/** 生成Fid 
	 * @param flow */
	public String generatedXh(ZhFlow flow);

	/**
	 * 根据fid来搜索有几个配件
	 * @param fid
	 */
	public List<ZhMetric> queryByFid( String fid);

	/**
	 * 添加
	 * @param metric
	 */
	public void save(ZhMetric metric);

}
