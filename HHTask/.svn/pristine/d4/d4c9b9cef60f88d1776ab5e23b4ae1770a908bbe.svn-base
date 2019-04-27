package com.task.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class LinkedAndArrayList {
	/*
	 * 
	 * 344 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 218
	 * 
	 * 
	 * 94 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 234
	 */

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List arrayList = new ArrayList();
		@SuppressWarnings("unused")
		List linkedList = new LinkedList();
		System.out
				.println("************************添加测试Add()***************************************");
		long time1 = new Date().getTime();
		for (int i = 0; i < 200000; i++) {
			arrayList.add(i, null);
		}
		long time2 = new Date().getTime();
		System.out.println("arrayList的添加时间:" + (time2 - time1));
//
//		long time3 = new Date().getTime();
//		for (int i = 0; i < 200000; i++) {
//			linkedList.add(i, null);
//		}
//		long time4 = new Date().getTime();
//		System.out.println("linkedList的添加时间:" + (time4 - time3));

		System.out
				.println("******************************查询测试get()*********************************");

		long time9 = new Date().getTime();
		for (int i = 0,len=arrayList.size(); i < len; i++) {
			arrayList.get(i);
		}
		long time10 = new Date().getTime();
		System.out.println("arrayList的查询时间:" + (time10 - time9));
//		long time11 = new Date().getTime();
//		for (int i = 0; i < linkedList.size(); i++) {
//			linkedList.get(i);
//		}
//		long time12 = new Date().getTime();
//		System.out.println("linkedList的查询时间:" + (time12 - time11));

		// System.out
		// .println("******************************删除测试remove()*********************************");
		//
		// long time5 = new Date().getTime();
		// for (int i = 0; i < 20000; i++) {
		// arrayList.remove(i);
		// }
		// long time6 = new Date().getTime();
		// System.out.println("arrayList的删除时间:" + (time6 - time5));
		// long time7 = new Date().getTime();
		// for (int i = 0; i < 20000; i++) {
		// linkedList.remove(i);
		// }
		// long time8 = new Date().getTime();
		// System.out.println("linkedList的删除时间:" + (time8 - time7));

	}

}
