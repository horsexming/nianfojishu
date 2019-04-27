package com.task.Server;

import java.util.List;
import com.task.entity.Template;
import com.task.entity.Users;
import com.task.entity.zgkh.AssessPersonnel;

public interface TemplateServer {

	public boolean addTemplate(Template template);// 添加模板

	public Template findTemplateById(int id);// 通过ID查找模板

	public boolean updateTemplate(Template template);// 修改模板

	// public boolean updateTemplate(Template template, Set<Users> users);//
	// 模板绑定

	public Object[] findAllTemplate(int pageNo, int pageSize, String status);// 查询模板(审核中或历史模板以及所属定制人)

	public String PreviewTemplate(Template template);// 预览模板

	public boolean delTemplate(Template template);// 删除模板

	public List<Users> findUsersByDept(Template template);// 根据模板部门名称查询部门所有人员(登录人除外)

	public List<Users> findUsersByDept();// 根据登录用户部门名称查询部门所有人员(登录人除外)

	public List findTDsByCondition(Template template, int pageNo, int pageSize,
			String status);// 条件查询模板明细

	public Integer getCountBtCondition(Template template, String status);// 通过条件得到数量

	public List<Template> findTemplateListByUserId(AssessPersonnel ap);// 通过用户id查询用户绑定模板

	public boolean updateTemplateFinsh(Template template);// 更新模板转状态为结束

	public boolean useHirstory(Template template, String status);// 使用历史模板

	// 查询定制人是否存在状态为审核的模版
	public Integer getCountBtCondition();

	public boolean updateExamTemplate(Integer[] detailSelect, String tag);

	public Object[] findAllTemplate1(int parseInt, int pageSize);

}
