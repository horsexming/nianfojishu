package com.task.entity.sop;

import java.util.Map;

/**
 * 版本序号
 * @author txb
 *
 */
public class Banbenxuhao implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String banbenNumber;
	private Integer banbenxuhao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBanbenNumber() {
		return banbenNumber;
	}
	public void setBanbenNumber(String banbenNumber) {
		this.banbenNumber = banbenNumber;
	}
	public Integer getBanbenxuhao() {
		return banbenxuhao;
	}
	public void setBanbenxuhao(Integer banbenxuhao) {
		this.banbenxuhao = banbenxuhao;
	}
	/**
	 * 判断版本1是否比版本2(-1为小0为相等1为大于,2为版本序号相同但后缀不同,3,4表示版本不在系统承认范围)
	 * @param banben1
	 * @param banben2
	 * @param banBenNumber
	 * @return
	 */
	public static int comparebanben(String banben1,String banben2, Map<String,Integer> banBenNumber){
		if ((banben1 == null || banben1.length() == 0) && banben2 != null
				&& banben2.length() > 0) {
			return -1;
		}
		if ((banben2 == null || banben2.length() == 0) && banben1 != null
				&& banben1.length() > 0) {
			return 1;
		}
		if ((banben1 == null || banben1.length() == 0) && (banben2 == null || banben2.length() == 0)) {
			return 0;
		}
		if(banben1.equals(banben2)){
			return 0;
		}
		if(banBenNumber==null){
			return 5;
		}
		Integer xuhao1 = banBenNumber.get(banben1.substring(0));
		Integer xuhao2 = banBenNumber.get(banben2.substring(0));
		if(xuhao1==null){
			return 3;
		}
		if(xuhao2==null){
			return 4;
		}
		if(xuhao1>xuhao2){
			return 1;
		}
		if(xuhao1<xuhao2){
			return -1;
		}
		if(xuhao1.equals(xuhao2)){//表示有小班次差别
			return 2;
		}
		return 0;
	}
	
}
