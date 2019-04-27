package com.task.util;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.entity.OaAppDetail;

public class TestAnnotation {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Parent c = new Parent(OaAppDetail.class);
		// List<SortableField> list = c.init();// 获取泛型中类里面的注解
		// // 输出结果
		// for (SortableField l : list) {
		// System.out.println("字段名称：" + l.getName() + "\t字段类型：" + l.getType()
		// + "\t注解名称：" + l.getMeta().name() + "\t注解描述："
		// + l.getMeta().description());
		// }
		OaAppDetail oa = new OaAppDetail();
		oa.setDetailOrdNumber("test123");
		oa.setDetailChildClass("办公用品");
		try {
			Class c = Class.forName("com.task.entity.OaAppDetail");
			Object o = c.newInstance();
			BeanUtils.copyProperties(oa, o);
			
			Parent p = new Parent();
			List<SortableField> list = p.getSortableField(0);// 获取泛型中类里面的注解
			// 输出结果
			String message = "";
			for (SortableField l : list) {
				message += l.getMeta().name() + ":" + l.getValue() + ",";
			}
			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}