package com.task.Server;

import java.util.List;

import com.task.entity.Template;
import com.task.entity.TemplateDetails;

public interface TemplateDetailsServer {

	public boolean addTemplateDetails(TemplateDetails templateDetails,
			Template template);// 添加模板明细

	public TemplateDetails findTemDetailsById(int id);// 通过id查找模板明细

	public Float findSumScore(int templateId, String onLayer);// 通过模板id和上层查询总分

	public List findTemplateDetailsByOnLayer(String onLayer);// 通过上层操作明细

	public Template delTemplateDetails(TemplateDetails templateDetails);// 级联删除模板明细

	public boolean updateTems(TemplateDetails tems); // 修改模板明细

	public boolean chageTdsScore(TemplateDetails tds); // 修改模板明细(更改上层分数)

	public boolean findOldTemplateDetaild(TemplateDetails templateDetails,
			Template template); // 查询明细中是否已经存在明细内容
}
