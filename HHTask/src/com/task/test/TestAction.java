package com.task.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.task.util.DateUtil;

public class TestAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String firstDate = "2014-12-01 12:00:00";
//		String endDate = "2014-12-01 16:00:00";
//		long zongDate = 0;
//		Date f = DateUtil.parseDate(firstDate, "yyyy-MM-dd HH:mm:ss");
//		Date e = DateUtil.parseDate(endDate, "yyyy-MM-dd HH:mm:ss");
//		zongDate= e.getTime() - f.getTime();
//		System.out.println("总时长(H):"+zongDate/(1000 * 60 * 60 * 24));
		
//		String aaa = "KVP-Assess-2014-001";
//		 String[] data1 = aaa.split("-");
//		 String mark1= data1[0];
//		 System.out.println(mark1);
//		 String mark2= data1[1];
//		 String mark3= data1[2];
//		 String mark4= data1[3];
//		  int upData1 = Integer.parseInt(mark4);
//		  upData1++;
//		 System.out.println(mark1+"-"+mark2+"-"+mark3+"-"+upData1); 
		 
		 
//		Integer a1 = aaa.lastIndexOf("-");
//		String aaa2 = aaa.substring(a1+1);
//		String mark1 = aaa2.split("_")[0];
//		
//		long number2 = Long.parseLong(aaa2) + 1;
//		String number3 = Long.toString(number2);
//		String aaa3 = "KVP-Assess-2014-"+number3+"";
//		System.out.println(aaa3);
//		String value = "map";
//		Map<String,String> map = new HashMap<String,String>();//HashMap
//		map.put("0", "what"); //增加值
//		map.put("1", value);
//		map.put("2", "asdf");
//		
//		Set<String> mapSet =  map.keySet();	//获取所有的key值 为set的集合
//		Iterator<String> itor =  mapSet.iterator();//获取key的Iterator便利
//		while(itor.hasNext()){//存在下一个值
//			String key = itor.next();//当前key值
//		if(map.get(key).equals(value)){//获取value 与 所知道的value比较
//			System.out.println("你要找的key ："+key);//相等输出key
//			}
//		}
//		Double a = 0.3;
//		Double b = 0.2;
		float f=3.14f;
		double d=(double)f; 
		System.out.println(d);
		
		
		
		
	}

}
