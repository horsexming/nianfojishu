package com.task.entity.caiwu.baobiao.laozi;

import java.io.Serializable;

/**
 * 
 * @author 王晓飞 
 * 表名:国防科工局月报表 (ta_baobiao_laozi_Mrondsaeb)
 *
 */
public class Mrondsaeb implements Serializable{

	private Integer id;
	//人员月末数
	private Integer ryyms1;
	private Integer ryyms2;
	//1.在岗职工
	private Integer zgzg1;
	private Integer zgzg2;
	//（1）管理人员
	private Integer glry1;
	private Integer glry2;
	//（2）技术人员
	private Integer jsry1;
	private Integer jsry2;
	//  其中：工程技术人员
	private Integer gcjsry1;
	private Integer gcjsry2;
	//其中：从事军品科研生产人员
	private Integer csjpkyscry1;//
	private Integer csjpkyscry2;//
	//（3）技能人员
	private Integer jnry1;
	private Integer jnry2;
	/**职称*/
	//正高
	private Integer zg1;
	private Integer zg2;
	//副高
	private Integer fg1;
	private Integer fg2;
	//中级
	private Integer zj1;
	private Integer zj2;
	//初级职称
	private Integer cjzc1;//
	private Integer cjzc2;//
	//其他
	private Integer qt1;//
	private Integer qt2;//
	/**
	 * 技能等级
	 */
	//高级技师
	private Integer gjjs1;//
	private Integer gjjs2;//
	//技师
	private Integer js1;//
	private Integer js2;//
	//高级工
	private Integer gjg1;//
	private Integer gjg2;//
	//中级工
	private Integer zjg1;//
	private Integer zjg2;//
	//初级工
	private Integer cjg1;//
	private Integer cjg2;//
	//其他
	private Integer qtjndj1;//
	private Integer qtjndj2;//
	/**
	 * 学历
	 */
	//博士研究生
	private Integer doctor1;
	private Integer doctor2;
	//硕士研究生
	private Integer master1;//
	private Integer master2;//
	//本科生
	private Integer Undergraduate1;
	private Integer Undergraduate2;
	//大专
	private Integer juniorCollege1;
	private Integer juniorCollege2;
	//中专
	private Integer sss1;
	private Integer sss2;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRyyms1() {
		return ryyms1;
	}
	public void setRyyms1(Integer ryyms1) {
		this.ryyms1 = ryyms1;
	}
	public Integer getRyyms2() {
		return ryyms2;
	}
	public void setRyyms2(Integer ryyms2) {
		this.ryyms2 = ryyms2;
	}
	public Integer getZgzg1() {
		return zgzg1;
	}
	public void setZgzg1(Integer zgzg1) {
		this.zgzg1 = zgzg1;
	}
	public Integer getZgzg2() {
		return zgzg2;
	}
	public void setZgzg2(Integer zgzg2) {
		this.zgzg2 = zgzg2;
	}
	public Integer getGlry1() {
		return glry1;
	}
	public void setGlry1(Integer glry1) {
		this.glry1 = glry1;
	}
	public Integer getGlry2() {
		return glry2;
	}
	public void setGlry2(Integer glry2) {
		this.glry2 = glry2;
	}
	public Integer getJsry1() {
		return jsry1;
	}
	public void setJsry1(Integer jsry1) {
		this.jsry1 = jsry1;
	}
	public Integer getJsry2() {
		return jsry2;
	}
	public void setJsry2(Integer jsry2) {
		this.jsry2 = jsry2;
	}
	public Integer getGcjsry1() {
		return gcjsry1;
	}
	public void setGcjsry1(Integer gcjsry1) {
		this.gcjsry1 = gcjsry1;
	}
	public Integer getGcjsry2() {
		return gcjsry2;
	}
	public void setGcjsry2(Integer gcjsry2) {
		this.gcjsry2 = gcjsry2;
	}
	public Integer getCsjpkyscry1() {
		return csjpkyscry1;
	}
	public void setCsjpkyscry1(Integer csjpkyscry1) {
		this.csjpkyscry1 = csjpkyscry1;
	}
	public Integer getCsjpkyscry2() {
		return csjpkyscry2;
	}
	public void setCsjpkyscry2(Integer csjpkyscry2) {
		this.csjpkyscry2 = csjpkyscry2;
	}
	public Integer getJnry1() {
		return jnry1;
	}
	public void setJnry1(Integer jnry1) {
		this.jnry1 = jnry1;
	}
	public Integer getJnry2() {
		return jnry2;
	}
	public void setJnry2(Integer jnry2) {
		this.jnry2 = jnry2;
	}
	public Integer getZg1() {
		return zg1;
	}
	public void setZg1(Integer zg1) {
		this.zg1 = zg1;
	}
	public Integer getZg2() {
		return zg2;
	}
	public void setZg2(Integer zg2) {
		this.zg2 = zg2;
	}
	public Integer getFg1() {
		return fg1;
	}
	public void setFg1(Integer fg1) {
		this.fg1 = fg1;
	}
	public Integer getFg2() {
		return fg2;
	}
	public void setFg2(Integer fg2) {
		this.fg2 = fg2;
	}
	public Integer getZj1() {
		return zj1;
	}
	public void setZj1(Integer zj1) {
		this.zj1 = zj1;
	}
	public Integer getZj2() {
		return zj2;
	}
	public void setZj2(Integer zj2) {
		this.zj2 = zj2;
	}
	public Integer getCjzc1() {
		return cjzc1;
	}
	public void setCjzc1(Integer cjzc1) {
		this.cjzc1 = cjzc1;
	}
	public Integer getCjzc2() {
		return cjzc2;
	}
	public void setCjzc2(Integer cjzc2) {
		this.cjzc2 = cjzc2;
	}
	public Integer getQt1() {
		return qt1;
	}
	public void setQt1(Integer qt1) {
		this.qt1 = qt1;
	}
	public Integer getQt2() {
		return qt2;
	}
	public void setQt2(Integer qt2) {
		this.qt2 = qt2;
	}
	public Integer getGjjs1() {
		return gjjs1;
	}
	public void setGjjs1(Integer gjjs1) {
		this.gjjs1 = gjjs1;
	}
	public Integer getGjjs2() {
		return gjjs2;
	}
	public void setGjjs2(Integer gjjs2) {
		this.gjjs2 = gjjs2;
	}
	public Integer getJs1() {
		return js1;
	}
	public void setJs1(Integer js1) {
		this.js1 = js1;
	}
	public Integer getJs2() {
		return js2;
	}
	public void setJs2(Integer js2) {
		this.js2 = js2;
	}
	public Integer getGjg1() {
		return gjg1;
	}
	public void setGjg1(Integer gjg1) {
		this.gjg1 = gjg1;
	}
	public Integer getGjg2() {
		return gjg2;
	}
	public void setGjg2(Integer gjg2) {
		this.gjg2 = gjg2;
	}
	public Integer getZjg1() {
		return zjg1;
	}
	public void setZjg1(Integer zjg1) {
		this.zjg1 = zjg1;
	}
	public Integer getZjg2() {
		return zjg2;
	}
	public void setZjg2(Integer zjg2) {
		this.zjg2 = zjg2;
	}
	public Integer getCjg1() {
		return cjg1;
	}
	public void setCjg1(Integer cjg1) {
		this.cjg1 = cjg1;
	}
	public Integer getCjg2() {
		return cjg2;
	}
	public void setCjg2(Integer cjg2) {
		this.cjg2 = cjg2;
	}
	public Integer getQtjndj1() {
		return qtjndj1;
	}
	public void setQtjndj1(Integer qtjndj1) {
		this.qtjndj1 = qtjndj1;
	}
	public Integer getQtjndj2() {
		return qtjndj2;
	}
	public void setQtjndj2(Integer qtjndj2) {
		this.qtjndj2 = qtjndj2;
	}
	public Integer getDoctor1() {
		return doctor1;
	}
	public void setDoctor1(Integer doctor1) {
		this.doctor1 = doctor1;
	}
	public Integer getDoctor2() {
		return doctor2;
	}
	public void setDoctor2(Integer doctor2) {
		this.doctor2 = doctor2;
	}
	public Integer getMaster1() {
		return master1;
	}
	public void setMaster1(Integer master1) {
		this.master1 = master1;
	}
	public Integer getMaster2() {
		return master2;
	}
	public void setMaster2(Integer master2) {
		this.master2 = master2;
	}
	public Integer getUndergraduate1() {
		return Undergraduate1;
	}
	public void setUndergraduate1(Integer undergraduate1) {
		Undergraduate1 = undergraduate1;
	}
	public Integer getUndergraduate2() {
		return Undergraduate2;
	}
	public void setUndergraduate2(Integer undergraduate2) {
		Undergraduate2 = undergraduate2;
	}
	public Integer getJuniorCollege1() {
		return juniorCollege1;
	}
	public void setJuniorCollege1(Integer juniorCollege1) {
		this.juniorCollege1 = juniorCollege1;
	}
	public Integer getJuniorCollege2() {
		return juniorCollege2;
	}
	public void setJuniorCollege2(Integer juniorCollege2) {
		this.juniorCollege2 = juniorCollege2;
	}
	public Integer getSss1() {
		return sss1;
	}
	public void setSss1(Integer sss1) {
		this.sss1 = sss1;
	}
	public Integer getSss2() {
		return sss2;
	}
	public void setSss2(Integer sss2) {
		this.sss2 = sss2;
	}

	
	
}
