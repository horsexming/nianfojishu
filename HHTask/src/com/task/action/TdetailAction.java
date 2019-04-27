package com.task.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.TdetailService;
import com.task.entity.Project;
import com.task.entity.Tdetail;
import com.task.util.MKUtil;

public class TdetailAction extends ActionSupport {
	
	private TdetailService tdetailService;
	
	private Tdetail detail;
	private List<Tdetail> details;
	
	public String addInput(){
		return "addInput";
	}
	
	public String add(){
		List<Tdetail> test = testData();
		tdetailService.addAll(test);
		return SUCCESS;
	}
	
	public String listAll(){
		List list = tdetailService.getAll(detail.getProject());
		MKUtil.writeJSON(list);
		return null;
	}
	
	public String getAll(){
		MKUtil.writeJSON(tdetailService.getAll(detail.getProject()));
		return null;
	}
	
	public String getZizhi(){
		details = tdetailService.get(detail.getProject());
		return "getZizhi";
	}
	
	public String getZizhiSelect(){
		List list = null;
		try {
			list = tdetailService.getZizhiSelect(detail.getProject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		MKUtil.writeJSON(list);
		return null;
	}
	
	public String list(){
		if (detail != null && detail.getProject() != null && detail.getProject().getId() != null) {
//			details = tdetailService.
		} else {
			
		}
		
		return "list";
	}
	
	public String listRoot(){
		details = tdetailService.getZongCheng(detail.getProject());
		return "listRoot";
	}
	
	public String getWaigou(){
//		details = tdetailService.getWaigou(detail.getProject());
		return "getWaigou";
	}
	
	public List<Tdetail> testData(){
		List<Tdetail> test = new ArrayList<Tdetail>();
		
		Tdetail t1 = new Tdetail();
		t1.setName("前排气管总成");
		t1.setPartNumber("12030010S00");
		t1.setType("总成");
		Project p = new Project();
		p.setId(1);
		t1.setProject(p);
		test.add(t1);
		
		Tdetail t2 = new Tdetail();
		t2.setXunhao("1");
		t2.setPartNumber("QP-1");
		t2.setName("进气组件");
		t2.setSingleNumber(1);
		t2.setType("组合件");
		test.add(t2);
		
		Tdetail t3 = new Tdetail();
		t3.setXunhao("1.1");
		t3.setPartNumber("QP-2");
		t3.setName("进气法兰组件");
		t3.setSingleNumber(1);
		t3.setType("组合件");
		test.add(t3);
		
		
		Tdetail t4 = new Tdetail();
		t4.setXunhao("1.1.1");
		t4.setPartNumber("QP-3");
		t4.setName("法兰1");
		t4.setSingleNumber(1);
		t4.setType("外购件");
		t4.setMaterial("Q235A");
		t4.setStandard("t=11");
		t4.setSpecification("GB/T700-88");
		test.add(t4);
		
		Tdetail t5 = new Tdetail();
		t5.setXunhao("1.1.2");
		t5.setPartNumber("QP-4");
		t5.setName("进气管");
		t5.setSingleNumber(1);
		t5.setType("自制件");
		t5.setMaterial("SUH409L");
		t5.setStandard("Φ54X1.5");
		t5.setSpecification("JIS G4312-1999");
		test.add(t5);
		
		Tdetail t6 = new Tdetail();
		t6.setXunhao("1.2");
		t6.setPartNumber("4G63 180 471");
		t6.setName("螺纹嘴");
		t6.setSingleNumber(1);
		t6.setType("外购件");
		t6.setMaterial("");
		t6.setStandard("1Cr18Ni9Ti");
		t6.setSpecification("M18x1.5-6H");
		test.add(t6);
		
		Tdetail t7 = new Tdetail();
		t7.setXunhao("2");
		t7.setPartNumber("QP-7");
		t7.setName("净化器主体");
		t7.setSingleNumber(1);
		t7.setType("组合件");
		t7.setMaterial("");
		t7.setStandard("");
		t7.setSpecification("");
		test.add(t7);
		
		Tdetail t8 = new Tdetail();
		t8.setXunhao("2.1");
		t8.setPartNumber("QP-8");
		t8.setName("筒体");
		t8.setSingleNumber(1);
		t8.setType("外购件");
		t8.setMaterial("SUS436L");
		t8.setStandard("t=1.5");
		t8.setSpecification("JIS G4305-1999");
		test.add(t8);
		
		Tdetail t9 = new Tdetail();
		t9.setXunhao("2.2");
		t9.setPartNumber("QP-9");
		t9.setName("端锥");
		t9.setSingleNumber(2);
		t9.setType("外购件");
		t9.setMaterial("SUS436L");
		t9.setStandard("t=1.5");
		t9.setSpecification("JIS G4305-1999");
		test.add(t9);
		
		Tdetail t10 = new Tdetail();
		t10.setXunhao("2.3");
		t10.setPartNumber("QP-10");
		t10.setName("衬垫");
		t10.setSingleNumber(1);
		t10.setType("外购件");
		t10.setMaterial("CD-100  3100");
		t10.setStandard("t=4.9");
		t10.setSpecification("");
		test.add(t10);
		
		Tdetail t11 = new Tdetail();
		t11.setXunhao("2.4");
		t11.setPartNumber("QP-11");
		t11.setName("载体");
		t11.setSingleNumber(1);
		t11.setType("外购件");
		t11.setMaterial("");
		t11.setStandard("");
		t11.setSpecification("");
		test.add(t11);
		
		Tdetail t12 = new Tdetail();
		t12.setXunhao("3");
		t12.setPartNumber("QP-12");
		t12.setName("出气组件");
		t12.setSingleNumber(1);
		t12.setType("组合件");
		t12.setMaterial("");
		t12.setStandard("");
		t12.setSpecification("");
		test.add(t12);
		
		Tdetail t13 = new Tdetail();
		t13.setXunhao("3.1");
		t13.setPartNumber("QP-13");
		t13.setName("连接管");
		t13.setSingleNumber(1);
		t13.setType("自制件");
		t13.setMaterial("SUH409L");
		t13.setStandard("JIS G4312-1999");
		t13.setSpecification("Φ54X1.5");
		test.add(t13);
		
		Tdetail t14 = new Tdetail();
		t14.setXunhao("3.2");
		t14.setPartNumber("QP-15");
		t14.setName("箍圈");
		t14.setSingleNumber(1);
		t14.setType("外购件");
		t14.setMaterial("");
		t14.setStandard("");
		t14.setSpecification("镀铝钢板δ=2.0");
		test.add(t14);

		Tdetail t15 = new Tdetail();
		t15.setXunhao("3.3");
		t15.setPartNumber("QP-16");
		t15.setName("波纹管");
		t15.setSingleNumber(1);
		t15.setType("外购件");
		t15.setMaterial("SUS304");
		t15.setStandard("");
		t15.setSpecification("Φ78×200");
		test.add(t15);
		
		Tdetail t16 = new Tdetail();
		t16.setXunhao("3.4");
		t16.setPartNumber("QP-5");
		t16.setName("出气法兰组件");
		t16.setSingleNumber(1);
		t16.setType("组合件");
		t16.setMaterial("");
		t16.setStandard("");
		t16.setSpecification("");
		test.add(t16);
		
		Tdetail t17 = new Tdetail();
		t17.setXunhao("3.4");
		t17.setPartNumber("QP-5");
		t17.setName("出气法兰组件");
		t17.setSingleNumber(1);
		t17.setType("组合件");
		t17.setMaterial("");
		t17.setStandard("");
		t17.setSpecification("");
		test.add(t17);

		Tdetail t18 = new Tdetail();
		t18.setXunhao("3.4");
		t18.setPartNumber("QP-5");
		t18.setName("出气法兰组件");
		t18.setSingleNumber(1);
		t18.setType("组合件");
		t18.setMaterial("");
		t18.setStandard("");
		t18.setSpecification("");
		test.add(t18);
		
		Tdetail t19 = new Tdetail();
		t19.setXunhao("3.4.1");
		t19.setPartNumber("QP-17");
		t19.setName("出气管");
		t19.setSingleNumber(1);
		t19.setType("自制件");
		t19.setMaterial("SUH409L");
		t19.setStandard("JIS G4312-1999");
		t19.setSpecification("Φ54X1.5");
		test.add(t19);
		
		Tdetail t20 = new Tdetail();
		t20.setXunhao("3.4.2");
		t20.setPartNumber("QP-18");
		t20.setName("法兰2");
		t20.setSingleNumber(1);
		t20.setType("外购件");
		t20.setMaterial("Q235A");
		t20.setStandard("GB/T700-88");
		t20.setSpecification("t=10");
		test.add(t20);
		
		
		Tdetail t21 = new Tdetail();
		t21.setXunhao("3.4.3");
		t21.setPartNumber("QP-19");
		t21.setName("螺栓2");
		t21.setSingleNumber(2);
		t21.setType("外购件");
		t21.setMaterial("");
		t21.setStandard("GB5783-2000   8.8级");
		t21.setSpecification("M12×40");
		test.add(t21);
		
		Tdetail t22 = new Tdetail();
		t22.setXunhao("4");
		t22.setPartNumber("QP-20");
		t22.setName("吊钩1");
		t22.setSingleNumber(1);
		t22.setType("外购件");
		t22.setMaterial("Q235A");
		t22.setStandard("GB/T700-88");
		t22.setSpecification("Φ12表面镀锌");
		test.add(t22);
		
		return test;
	}

	public Tdetail getDetail() {
		return detail;
	}

	public void setDetail(Tdetail detail) {
		this.detail = detail;
	}

	public List<Tdetail> getDetails() {
		return details;
	}

	public void setDetails(List<Tdetail> details) {
		this.details = details;
	}

	public TdetailService getTdetailService() {
		return tdetailService;
	}

	public void setTdetailService(TdetailService tdetailService) {
		this.tdetailService = tdetailService;
	}

}
