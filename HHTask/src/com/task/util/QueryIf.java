package com.task.util;
import java.util.Date;


public class QueryIf {
	/*
	 * 本类根据要查询的字段和关键字、日期等信息来构造查询语句,下面说明每个参数的意义
	 * table:表名
	 * field[]：表的字段
	 * key[]：要查询的关键字
	 * orderField：要按照本字段排序
	 * order：排序规则    升序或降序
	 * dateField：日期在表中的字段
	 * date[]：要查的日期段
	 * flag:是否需要模糊查询 true为需要 false为不需要
	 * 
	 * 
	 * 说明：日期可以是单个日期 也可以是两个日期 本类对日期只做精确查询
	 * 注意：field 和 key相同下脚的值要对应   最大下脚要相等
	 */
	
	
	//同时查询日期和多个域都行
	public String queryFieldAndDate(String table ,String[] field,String[] key,String dateField,Date[] date,String orderField,String order,boolean flag){
		String hql="from "+table;
		String hqlIf="";
		int	len=field.length;
		//按字段查询
		for(int i=0;i<len;i++){
			if(field[i]!=null && !"".equals(field[i])){
				if(flag){//模糊查询
					hqlIf	+=" and "+field[i]+" like '%"+key[i]+"%'";
				}else{//精确查询
					hqlIf	+=" and "+field[i]+" = "+key[i];
				}
			}
		}
		
		Date oneDay=null;
		oneDay=date[0]!=null?date[0]:date[1];
		
		//按日期查询 考虑到只有单个日期
		if(date[0]!=null && date[1]!=null){
			if(date[0].getTime()>date[1].getTime()){//防止第一个日期比第二个大
				oneDay=date[0];
				date[0]=date[1];
				date[1]=oneDay;
			}
			hqlIf +=" and "+dateField+" between "+date[0]+" and "+date[1];
		}else if(oneDay!=null){
			hqlIf +=" and "+dateField+"="+oneDay;
		}
		
		
		//最后对queryIf处理 除去以“ and”开头的子串
		if(!"".equals(hqlIf)){
			hql+=" where";
			if(" and".equals(hqlIf.substring(0, 4))){
				hqlIf=hqlIf.substring(4,hqlIf.length());
			}
			hql+=hqlIf;
		}
		if(orderField!=null && order !=null && !"".equals(orderField) &&!"".equals(order)){
			hql+=" order by "+orderField+" "+order;
		}
		return hql;
	}

	
	//=======================================================================
	//同时查询多个域都行 并取某个域中的某一段并且这个域是字符串类型的
	public String queryFieldsWithNum(String table ,String[] field,String[] key,String betweenField,String[] betweenFields,String[] orderField,String[] order,String numField,String numIf,boolean flag){
		String hql=" from "+table;
		String hqlIf="";
		int	len=field.length;
		//按字段查询
		for(int i=0;i<len;i++){
			if(key[i]!=null && !"".equals(key[i])){
				if(flag){//模糊查询
					hqlIf	+=" and "+field[i]+" like '%"+key[i]+"%'";
				}else{//精确查询
					hqlIf	+=" and "+field[i]+" = "+key[i];
				}
			}
		}
		
		if(null!=numField && !"".equals(numField) && null!=numIf && !"".equals(numIf)){
			hqlIf	+=" and "+numField+" "+numIf+"0";
		}
		
		String  str=null;
		str=betweenFields[0]!=null?betweenFields[0]:betweenFields[1];
		
		//
		if((betweenFields[0]!=null) &&(!"".equals(betweenFields[0]))&& (betweenFields[1]!=null)&&(!"".equals(betweenFields[1]))){
			if(betweenFields[0].compareTo(betweenFields[1])>0){//防止第一个工号比第二个大
				str=betweenFields[0];
				betweenFields[0]=betweenFields[1];
				betweenFields[1]=str;
			}
			hqlIf +=" and "+betweenField+" between '"+betweenFields[0]+"' and '"+betweenFields[1]+"'";
		}else if(str!=null && !"".equals(str)){
			hqlIf +=" and "+betweenField+" = '"+str+"'";
		}
		
		
		//最后对queryIf处理 除去以“ and”开头的子串
		if(!"".equals(hqlIf)){
			hql+=" where";
			if(" and".equals(hqlIf.substring(0, 4))){
				hqlIf=hqlIf.substring(4,hqlIf.length());
			}
			hql+=hqlIf;
		}
		int lenpaixu=orderField.length;
		if(lenpaixu>0){hql+=" order by ";}
		String orderString="";
		for(int i=0;i<lenpaixu;i++){
			if(orderField[i]!=null && order[i] !=null && !"".equals(orderField[i]) &&!"".equals(order[i])){
				orderString+=" "+orderField[i]+" "+order[i]+" , ";
			}
		}
		if(orderString.lastIndexOf(", ")>0){
			orderString=orderString.substring(0,orderString.lastIndexOf(", "));
		}
		hql+=orderString;
		return hql;
	}
	
	
	//同时查询多个域都行 并取的加入or查询
	public String queryFieldsWithOr(String table ,String[] field,String[] key,String OrField, Integer[] OrKey,String betweenField,String[] betweenFields,String orderField,String order,boolean flag){
		String hql="from "+table;
		String hqlIf="";
		int	len=field.length;
		//按字段查询
		for(int i=0;i<len;i++){
			if(key[i]!=null && !"".equals(key[i])){
				if(flag){//模糊查询
					hqlIf	+=" and "+field[i]+" like '%"+key[i]+"%'";
				}else{//精确查询
					hqlIf	+=" and "+field[i]+" = "+key[i];
				}
			}
		}
		String orQuery=" and";
		int lenlen=OrKey.length;
		for(int i=0;i<lenlen;i++){
			if(OrKey[i]!=null){
				orQuery += " "+OrField+" = "+OrKey[i]+" or ";
			}
		}
		if(orQuery.length()>4){
			if(hqlIf.indexOf("and")<0){//前面的查询没有and字串的话 orQquery最前面就去掉and字串
				orQuery=orQuery.substring(4,orQuery.length());
			}
			//去掉orQquery最后的“or ”字串
			orQuery=orQuery.substring(0,orQuery.length()-3);
		}
		if(orQuery.length()==4){
			orQuery="";
		}
		
		hqlIf +=orQuery;
		String  str=null;
		str=betweenFields[0]!=null?betweenFields[0]:betweenFields[1];
		
		//
		if((betweenFields[0]!=null) &&(!"".equals(betweenFields[0]))&& (betweenFields[1]!=null)&&(!"".equals(betweenFields[1]))){
			if(betweenFields[0].compareTo(betweenFields[1])>0){//防止第一个工号比第二个大
				str=betweenFields[0];
				betweenFields[0]=betweenFields[1];
				betweenFields[1]=str;
			}
			hqlIf +=" and "+betweenField+" between '"+betweenFields[0]+"' and '"+betweenFields[1]+"'";
		}else if(str!=null && !"".equals(str)){
			hqlIf +=" and "+betweenField+" = '"+str+"'";
		}
		
		
		//最后对queryIf处理 除去以“ and”开头的子串
		if(!"".equals(hqlIf)){
			hql+=" where";
			if(" and".equals(hqlIf.substring(0, 4))){
				hqlIf=hqlIf.substring(4,hqlIf.length());
			}
			hql+=hqlIf;
		}
		if(orderField!=null && order !=null && !"".equals(orderField) &&!"".equals(order)){
			hql+=" order by "+orderField+" "+order;
		}
		return hql;
	}
	//同时查询多个域都行 并取某个域中的某一段并且这个域是字符串类型的
	//同时查询多个域都行 并取某个域中的某一段并且这个域是字符串类型的
	public String queryFields(String table ,String[] field,String[] key,String betweenField,String[] betweenFields,String orderField,String order,boolean flag){
		String hql="from "+table;
		String hqlIf="";
		int	len=field.length;
		//按字段查询
		for(int i=0;i<len;i++){
			if(key[i]!=null && !"".equals(key[i])){
				if(flag){//模糊查询
					hqlIf	+=" and "+field[i]+" like '%"+key[i]+"%'";
				}else{//精确查询
					hqlIf	+=" and "+field[i]+" = "+key[i];
				}
			}
		}
		
		String  str=null;
		str=betweenFields[0]!=null?betweenFields[0]:betweenFields[1];
		
		//
		if((betweenFields[0]!=null) &&(!"".equals(betweenFields[0]))&& (betweenFields[1]!=null)&&(!"".equals(betweenFields[1]))){
			if(betweenFields[0].compareTo(betweenFields[1])>0){//防止第一个工号比第二个大
				str=betweenFields[0];
				betweenFields[0]=betweenFields[1];
				betweenFields[1]=str;
			}
			hqlIf +=" and "+betweenField+" between '"+betweenFields[0]+"' and '"+betweenFields[1]+"'";
		}else if(str!=null && !"".equals(str)){
			hqlIf +=" and "+betweenField+" = '"+str+"'";
		}
		
		
		//最后对queryIf处理 除去以“ and”开头的子串
		if(!"".equals(hqlIf)){
			hql+=" where";
			if(" and".equals(hqlIf.substring(0, 4))){
				hqlIf=hqlIf.substring(4,hqlIf.length());
			}
			hql+=hqlIf;
		}
		if(orderField!=null && order !=null && !"".equals(orderField) &&!"".equals(order)){
			hql+=" order by "+orderField+" "+order;
		}
		return hql;
	}
	
	
	public String queryFields(String table ,String[] field,String[] key,String betweenField,Date[] betweenFields,String[] orderField,String[] order,boolean flag){
		String hql="from "+table;
		String hqlIf="";
		int	len=field.length;
		//按字段查询
		for(int i=0;i<len;i++){
			if(key[i]!=null && !"".equals(key[i])){
				if(flag){//模糊查询
					hqlIf	+=" and "+field[i]+" like '%"+key[i]+"%'";
				}else{//精确查询
					hqlIf	+=" and "+field[i]+" = "+key[i];
				}
			}
		}
		
		Date  str=null;
		str=betweenFields[0]!=null?betweenFields[0]:betweenFields[1];
		
		//
		if((betweenFields[0]!=null) &&(!"".equals(betweenFields[0]))&& (betweenFields[1]!=null)&&(!"".equals(betweenFields[1]))){
			if(betweenFields[0].compareTo(betweenFields[1])>0){//防止第一个工号比第二个大
				str=betweenFields[0];
				betweenFields[0]=betweenFields[1];
				betweenFields[1]=str;
			}
			hqlIf +=" and "+betweenField+" between '"+betweenFields[0]+"' and '"+betweenFields[1]+"'";
		}else if(str!=null && !"".equals(str)){
			hqlIf +=" and "+betweenField+" = '"+str+"'";
		}
		
		
		//最后对queryIf处理 除去以“ and”开头的子串
		if(!"".equals(hqlIf)){
			hql+=" where";
			if(" and".equals(hqlIf.substring(0, 4))){
				hqlIf=hqlIf.substring(4,hqlIf.length());
			}
			hql+=hqlIf;
		}
		int lenn=orderField.length;
		String orderIf=" order by ";
		for(int i=0;i<lenn;i++){
			if(orderField[i]!=null && !"".equals(orderField[i])){
				orderIf	+=" "+orderField[i]+" "+order[i]+" , ";
			}
		}
		if(" order by ".equals(orderIf)){
			orderIf="";
		}else if(orderIf.indexOf(",")>0){
			orderIf=orderIf.substring(0,orderIf.length()-2);
		}
			hql+=orderIf;
		return hql;
	}
	//bom查询
	public String selectbomFields(String table ,String[] field,String[] key,String betweenField,Date[] betweenFields,String[] orderField,String[] order,boolean flag){
		String hql="from "+table;
		String hqlIf="";
		int	len=field.length;
		//按字段查询
		for(int i=0;i<len;i++){
			if(key[i]!=null && !"".equals(key[i])){
				if(flag){//模糊查询
					hqlIf	+=" and "+field[i]+" like '%"+key[i]+"%'";
				}else{//精确查询
					hqlIf	+=" and "+field[i]+" = "+key[i];
				}
			}
		}
		
		Date  str=null;
		str=betweenFields[0]!=null?betweenFields[0]:betweenFields[1];
		
		//
		if((betweenFields[0]!=null) &&(!"".equals(betweenFields[0]))&& (betweenFields[1]!=null)&&(!"".equals(betweenFields[1]))){
			if(betweenFields[0].compareTo(betweenFields[1])>0){//防止第一个工号比第二个大
				str=betweenFields[0];
				betweenFields[0]=betweenFields[1];
				betweenFields[1]=str;
			}
			hqlIf +=" and "+betweenField+" between '"+betweenFields[0]+"' and '"+betweenFields[1]+"'";
		}else if(str!=null && !"".equals(str)){
			hqlIf +=" and "+betweenField+" = '"+str+"'";
		}
		
		
		//最后对queryIf处理 除去以“ and”开头的子串
		if(!"".equals(hqlIf)){
			hql+=" where";
			if(" and".equals(hqlIf.substring(0, 4))){
				hqlIf=hqlIf.substring(4,hqlIf.length());
			}
			hql+=hqlIf;
			hql=hql+" and bom_id in (select min(bom_id) from Bom group by bom_productId)";
		}else{
			hql=hql+" where bom_id in (select min(bom_id) from Bom group by bom_productId)";
		}
		
		int lenn=orderField.length;
		String orderIf=" order by ";
		for(int i=0;i<lenn;i++){
			if(orderField[i]!=null && !"".equals(orderField[i])){
				orderIf	+=" "+orderField[i]+" "+order[i]+" , ";
			}
		}
		if(" order by ".equals(orderIf)){
			orderIf="";
		}else if(orderIf.indexOf(",")>0){
			orderIf=orderIf.substring(0,orderIf.length()-2);
		}
			hql+=orderIf;
		return hql;
	}
	//新添处理流水卡片
	public String selectqueryFields(String table ,String[] field,String[] key,String betweenField,Date[] betweenFields,String[] orderField,String[] order,boolean flag){
		String hql="from "+table;
		String hqlIf="";
		int	len=field.length;
		//按字段查询
		for(int i=0;i<len;i++){
			if(key[i]!=null && !"".equals(key[i])){
				if(flag){//模糊查询
					hqlIf	+=" and "+field[i]+" like '%"+key[i]+"%'";
				}else{//精确查询
					hqlIf	+=" and "+field[i]+" = "+key[i];
				}
			}
		}
		
		Date  str=null;
		str=betweenFields[0]!=null?betweenFields[0]:betweenFields[1];
		
		//
		if((betweenFields[0]!=null) &&(!"".equals(betweenFields[0]))&& (betweenFields[1]!=null)&&(!"".equals(betweenFields[1]))){
			if(betweenFields[0].compareTo(betweenFields[1])>0){//防止第一个工号比第二个大
				str=betweenFields[0];
				betweenFields[0]=betweenFields[1];
				betweenFields[1]=str;
			}
			hqlIf +=" and "+betweenField+" between '"+betweenFields[0]+"' and '"+betweenFields[1]+"'";
		}else if(str!=null && !"".equals(str)){
			hqlIf +=" and "+betweenField+" = '"+str+"'";
		}
		
		
		//最后对queryIf处理 除去以“ and”开头的子串
		if(!"".equals(hqlIf)){
			hql+=" where";
			if(" and".equals(hqlIf.substring(0, 4))){
				hqlIf=hqlIf.substring(4,hqlIf.length());
			}
			hql+=hqlIf;
			hql=hql+" and procard_id in (select min(procard_id) from Procard group by procard_markId,procard_selfCard)";
		}else{
			hql=hql+" where procard_id in (select min(procard_id) from Procard group by procard_markId,procard_selfCard)";
		}
		
		int lenn=orderField.length;
		String orderIf=" order by ";
		for(int i=0;i<lenn;i++){
			if(orderField[i]!=null && !"".equals(orderField[i])){
				orderIf	+=" "+orderField[i]+" "+order[i]+" , ";
			}
		}
		if(" order by ".equals(orderIf)){
			orderIf="";
		}else if(orderIf.indexOf(",")>0){
			orderIf=orderIf.substring(0,orderIf.length()-2);
		}
			hql+=orderIf;
		return hql;
	}
	//新添处理卡片模板
	//新添
	public String selectmodelFields(String table ,String[] field,String[] key,String betweenField,Date[] betweenFields,String[] orderField,String[] order,boolean flag){
		String hql="from "+table;
		String hqlIf="";
		int	len=field.length;
		//按字段查询
		for(int i=0;i<len;i++){
			if(key[i]!=null && !"".equals(key[i])){
				if(flag){//模糊查询
					hqlIf	+=" and "+field[i]+" like '%"+key[i]+"%'";
				}else{//精确查询
					hqlIf	+=" and "+field[i]+" = "+key[i];
				}
			}
		}
		
		Date  str=null;
		str=betweenFields[0]!=null?betweenFields[0]:betweenFields[1];
		
		//
		if((betweenFields[0]!=null) &&(!"".equals(betweenFields[0]))&& (betweenFields[1]!=null)&&(!"".equals(betweenFields[1]))){
			if(betweenFields[0].compareTo(betweenFields[1])>0){//防止第一个工号比第二个大
				str=betweenFields[0];
				betweenFields[0]=betweenFields[1];
				betweenFields[1]=str;
			}
			hqlIf +=" and "+betweenField+" between '"+betweenFields[0]+"' and '"+betweenFields[1]+"'";
		}else if(str!=null && !"".equals(str)){
			hqlIf +=" and "+betweenField+" = '"+str+"'";
		}
		
		
		//最后对queryIf处理 除去以“ and”开头的子串
		if(!"".equals(hqlIf)){
			hql+=" where";
			if(" and".equals(hqlIf.substring(0, 4))){
				hqlIf=hqlIf.substring(4,hqlIf.length());
			}
			hql+=hqlIf;
			hql=hql+" and modelId in (select min(modelId) from ModelCard group by modelMarkId)";
		}else{
			hql=hql+" where modelId in (select min(modelId) from ModelCard group by modelMarkId)";
		}
		
		int lenn=orderField.length;
		String orderIf=" order by ";
		for(int i=0;i<lenn;i++){
			if(orderField[i]!=null && !"".equals(orderField[i])){
				orderIf	+=" "+orderField[i]+" "+order[i]+" , ";
			}
		}
		if(" order by ".equals(orderIf)){
			orderIf="";
		}else if(orderIf.indexOf(",")>0){
			orderIf=orderIf.substring(0,orderIf.length()-2);
		}
			hql+=orderIf;
		return hql;
	}
	/*public String getHql(String[] contact,String[] queryIf,String[] fieldsType,String[] fields,String[] keys){
		String hql="";
	}*/
	//最厉害的东东
	public String getSubHql(String head,String[] contact,String[] queryIf,String[] fieldsType,String[] fields,String[] keys){
		/*
		 * head 形如where  between  order by之类头部
		 * contact形如and or , 之类连接词
		 * queryIf形如= > < != like之类运算符
		 * 
		 */
		String subHql=head;
		int len=fields.length;
		for(int i=0;i<len;i++){
			if(keys[i]!=null && !"".equals(keys[i])){
				if("String".equals(fieldsType[i])){
					if(queryIf[i].equals("like")){
						keys[i]="'%"+keys[i]+"%'";
					}else{
						keys[i]="'"+keys[i]+"'";
					}
				}else if("Date".equals(fieldsType[i])){
					keys[i]="'"+keys[i]+"'";
				}
			}
		}
		String lastCantact=null;
		for(int i=0;i<len;i++){
			if(keys[i]!=null && !"".equals(keys[i])){
				subHql +=" "+fields[i]+" "+queryIf[i]+" "+keys[i]+" "+contact[i]+" ";
				lastCantact=contact[i];
			}
		}
		if(head.equals(subHql)){
			subHql="";
		}else{
			subHql=subHql.substring(0,subHql.length()-lastCantact.length()-1);
		}
		return subHql;
	}
	/*
	 * 按照某个字段求和语句
	 * 同时查询多个域都行 并取某个域中的某一段，并且这个域是字符串类型的
	 * 
	 * 
	 */
	public String queryFieldsToSum(String table ,String sumfield,String[] groupBy,String[] field,String[] key,String betweenField,String[] betweenFields,boolean flag){
		//统计字段：sumfield
		String hql="select  sum("+sumfield+") as "+sumfield+" ";
		int lenn=groupBy.length;
		String hqlfield="";
		//分组字段数组：groupBy[]
		for(int i=0;i<lenn;i++){
			if(null!=groupBy[i] && !"".equals(groupBy[i])){
				hqlfield+=" , "+groupBy[i];
			}
		}
		hql +=hqlfield;
		hql +=" from "+table;
		String hqlIf="";
		int	len=field.length;
		//按字段查询
		for(int i=0;i<len;i++){
			if(key[i]!=null && !"".equals(key[i])){
				if(flag){//模糊查询
					hqlIf	+=" and "+field[i]+" like '%"+key[i]+"%'";
				}else{//精确查询
					hqlIf	+=" and "+field[i]+" = "+key[i];
				}
			}
		}
		
		String  str=null;
		str=betweenFields[0]!=null?betweenFields[0]:betweenFields[1];
		
		//
		if(!"".equals(betweenFields[0]) &&!"".equals(betweenFields[1]) && betweenFields[0]!=null && betweenFields[1]!=null){
			if(betweenFields[0].compareTo(betweenFields[1])>0){//防止第一个工号比第二个大
				str=betweenFields[0];
				betweenFields[0]=betweenFields[1];
				betweenFields[1]=str;
			}
			hqlIf +=" and "+betweenField+" between '"+betweenFields[0]+"' and '"+betweenFields[1]+"'";
		}else if(!"".equals(str)&&str!=null){
			hqlIf +=" and "+betweenField+"='"+str+"'";
		}
		//最后对queryIf处理 除去以“ and”开头的子串
		if(!"".equals(hqlIf)){
			hql+=" where";
			if(" and".equals(hqlIf.substring(0, 4))){
				hqlIf=hqlIf.substring(4,hqlIf.length());
			}
			hql+=hqlIf;
		}
		String group=" group by ";
		for(int i=0;i<lenn;i++){
			if(null!=groupBy[i] && !"".equals(groupBy[i])){
				group+=" "+groupBy[i]+", ";
			}
		}
		if(group.indexOf(", ")>0){
			group=group.substring(0,group.length()-2);
			hql+=group;
		}
		return hql;
	}
}
