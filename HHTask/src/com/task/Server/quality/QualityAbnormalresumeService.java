package com.task.Server.quality;

import java.util.Map;

import com.task.entity.quality.QualityAbnormalresume;

public interface QualityAbnormalresumeService {

	String addQualityAbnormalresume(QualityAbnormalresume qualityAbnormalresume);

	QualityAbnormalresume findQualityAbnormalresume(Integer id);

	String deleteQualityAbnormalresume(Integer id);

	Object[] findallQualityAbnormalresume(
			QualityAbnormalresume qualityAbnormalresume, int pageNo,
			int pageSize);

	String updateQualityAbnormalresume(
			QualityAbnormalresume qualityAbnormalresume);
	/**
	 * 质量异常柏拉图数据准备
	 * @param tag
	 * @return
	 */
	public Map<String,Object> findqaforPic(String tag,Integer count);
}
