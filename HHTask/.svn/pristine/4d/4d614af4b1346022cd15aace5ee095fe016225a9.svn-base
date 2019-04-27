package com.task.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	/**
	 * 判断是否是周六周日
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date){
		boolean weekend = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY|| cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){ 
			weekend = true;
		}
		return weekend;
	}
	/**
	 * 判断是否是法定假日
	 * @param date
	 * @return
	 */
	public static boolean isFestival(Date date){
		String[] fDate=new String[]{
			//元旦：1月1日放假1天
			"2014-01-01",
			//春节：1月31日至2月6日放假调休，共7天。1月26日（星期日）、2月8日（星期六）上班。
			"2014-01-31","2014-02-01","2014-02-02","2014-02-03","2014-02-04","2014-02-05","2014-02-06",
			//清明节：4月5日放假，4月7日（星期一）补休。
			"2014-04-05","2014-04-06","2014-04-07",
			//劳动节：5月1日至3日放假调休，共3天。5月4日（星期日）上班。
			"2014-05-01","2014-05-02","2014-05-03",
			//端午节：6月2日放假，与周末连休。
			"2014-06-02",
			//中秋节：9月8日放假，与周末连休。
			"2014-09-08",
			//国庆节：10月1日至7日放假调休，共7天。9月28日（星期日）、10月11日（星期六）上班。
			"2014-10-01","2014-10-02","2014-10-03","2014-10-04","2014-10-05","2014-10-06","2014-10-07"
		};
		boolean festival = false;
		Calendar fcal = Calendar.getInstance();
		Calendar dcal = Calendar.getInstance();
		dcal.setTime(date);
		List<Date> list =null;
		for(int i=0;i<fDate.length;i++){
			Date fdate=DateUtil.parseDate(fDate[i], "yyyy-MM-dd");
			fcal.setTime(fdate);
			//法定节假日判断
			if(fcal.get(Calendar.MONTH)==dcal.get(Calendar.MONTH)&& fcal.get(Calendar.DATE)==dcal.get(Calendar.DATE)) {
				festival = true;
			}
		}
		return festival;
	}
	
	/**
	 * 判断是否是工作日(包含特殊的工作日)
	 * @param date
	 * @return
	 */
	public static boolean isWorkDay(Date date){
		boolean workDay=false;
		//特殊工作日
		String[] wDate=new String[]{
			//元旦
			
			//春节  1月26日（星期日）、2月8日（星期六）上班。
			"2014-01-26","2014-02-8",
			//清明节
				
			//劳动节  5月4日（星期日）上班。
			"2014-05-4",
			//端午节
				
			//中秋节
				
			//国庆节  9月28日（星期日）、10月11日（星期六）上班。
			"2014-09-28","2014-10-11"
		};
		for(int i=0;i<wDate.length;i++){
			String dateStr=DateUtil.formatDate(date, "yyyy-MM-dd");
			if(dateStr.equals(wDate[i])){
				return true;
			}
		}
		
		boolean festival=DateUtil.isFestival(date);
		boolean weekend=DateUtil.isWeekend(date);
		
		if(!festival && !weekend){
			return true;
		}
		return false;
	}
	
	/**
	 * 时间格式转换
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date,String pattern){
		SimpleDateFormat formatter=new SimpleDateFormat(pattern);
		//String y="yyyy";
		//String y_m="yyyy-MM";
		//String y_m_d="yyyy-MM-dd";
		//String y_m_d_h="yyyy-MM-dd hh";
		//String y_m_d_h_m="yyyy-MM-dd hh:mm";
		//String y_m_d_h_m_s="yyyy-MM-dd hh:mm:ss";
		return formatter.format(date);
	}
	public static Date parseDate(String date,String pattern){
		SimpleDateFormat formatter=new SimpleDateFormat(pattern);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
	
	/**
	 * 获取加班工资
	 * @param startDate
	 * @param endDate
	 * @param unit
	 * @return
	 */
	public static double getOvertimeWage(Date startDate,Date endDate,int unit){
		if(unit==0){
			unit=20;
		}
		double overtimeWage=0.0;
		double timesFestival=3.0;
		double timesWeekend=2.0;
		double timesWorkDay=1.5;
		//long startWorkTime=DateUtil.parseDate("2014-04-15 07:30:00", "yyyy-MM-dd hh:mm:ss").getTime();
		//long endWorkTime=DateUtil.parseDate("2014-04-15 07:30:00", "yyyy-MM-dd hh:mm:ss").getTime();
		Calendar startC = Calendar.getInstance();
		startC.setTime(startDate);
		int yearStart = startC.get(Calendar.YEAR);    //获取年
		int monthStart = startC.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
		int dayStart = startC.get(Calendar.DAY_OF_MONTH);    //获取当前天数
		int firstStart = startC.getActualMinimum(Calendar.DAY_OF_MONTH);    //获取本月最小天数
		int lastStart =startC.getActualMaximum(Calendar.DAY_OF_MONTH);    //获取本月最大天数
		int timeStart = startC.get(Calendar.HOUR_OF_DAY);       //获取当前小时
		int minStart = startC.get(Calendar.MINUTE);          //获取当前分钟
		int seStart = startC.get(Calendar.SECOND);          //获取当前秒
		
		Calendar endC = Calendar.getInstance();
		endC.setTime(endDate);
		int yearEnd = startC.get(Calendar.YEAR);    //获取年
		int monthEnd = startC.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
		int dayEnd = startC.get(Calendar.DAY_OF_MONTH);    //获取当前天数
		int firstEnd = startC.getActualMinimum(Calendar.DAY_OF_MONTH);    //获取本月最小天数
		int lastEnd =startC.getActualMaximum(Calendar.DAY_OF_MONTH);    //获取本月最大天数
		int timeEnd = startC.get(Calendar.HOUR_OF_DAY);       //获取当前小时
		int minEnd = startC.get(Calendar.MINUTE);          //获取当前分钟
		int seEnd = startC.get(Calendar.SECOND);          //获取当前秒
		
		for(int year=yearStart;year<=yearEnd;year++){
			for(int month=monthStart;month<=monthEnd;month++){
				for(int day=dayStart;day<=dayEnd;day++){
					String currentDateStr=year+"-"+month+"-"+day;
					String currentNextDateStr=year+"-"+month+"-"+day+1;
					long startWorkTime=DateUtil.parseDate(currentDateStr+" 07:30:00", "yyyy-MM-dd hh:mm:ss").getTime();
					long endWorkTime=DateUtil.parseDate(currentDateStr+" 16:15:00", "yyyy-MM-dd hh:mm:ss").getTime();
					Date currentDate=DateUtil.parseDate(currentDateStr,"yyyy-MM-dd");
					Date currentNextDate=DateUtil.parseDate(currentNextDateStr,"yyyy-MM-dd");
					boolean festival=DateUtil.isFestival(currentDate);
					boolean workDay=DateUtil.isWorkDay(currentDate);
					
					double times=1.0;
					if(festival){
						times=timesFestival;
					}else if(workDay){
						times=timesWorkDay;
					}else{
						times=timesWeekend;
					}
					double currentWorkHours=0;
					long currentWorkMillisecond=0;
					long startTime=startDate.getTime();
					long endTime=endDate.getTime();
						//加班日期为同一天
					//System.out.println(DateUtil.parseDate(currentDateStr+" 07:30:00", "yyyy-MM-dd hh:mm:ss"));
					//System.out.println(DateUtil.parseDate(currentDateStr+" 16:30:00", "yyyy-MM-dd hh:mm:ss"));
					//System.out.println(startDate);	
					//System.out.println(endDate);
					//System.out.println(startWorkTime);
					//System.out.println(endWorkTime);
					//System.out.println(startTime);
					//System.out.println(endTime);
					
					if(dayStart==dayEnd){
							//上班前
							if(endTime<=startWorkTime){
								currentWorkMillisecond= endTime-startTime;
							//加班时间在上班时间之内
							}else if(startTime<startWorkTime && endTime>startWorkTime){
								currentWorkMillisecond= startWorkTime-startTime;
							}else if(startTime<endWorkTime && endTime >endWorkTime){
								currentWorkMillisecond= endTime-endWorkTime;
							//下班后
							}else if(startTime>=endWorkTime){
								currentWorkMillisecond= endTime-startTime;
								System.out.println(currentWorkMillisecond);
							}
							
						}
						//加班日期不为同一天
						if(dayStart!=dayEnd){
							//加班第一天
							if(year==dayStart){
								//上班前
								if(startTime<=startWorkTime){
									currentWorkMillisecond=currentNextDate.getTime()-endWorkTime+startWorkTime-startTime;
								//上班中
								}else if(startTime<=endWorkTime){
									currentWorkMillisecond= currentNextDate.getTime()-endWorkTime;
								//下班后
								}else if(startTime>endWorkTime){
									currentWorkMillisecond= currentNextDate.getTime()-startTime;
								}
							}
							//加班最后一天
							else if(year==dayEnd){
								//上班前
								if(endTime<=startWorkTime){
									currentWorkMillisecond=endTime-currentDate.getTime();
								//上班中
								}else if(endTime<=endWorkTime){
									currentWorkMillisecond= startWorkTime-currentDate.getTime();
								//下班后
								}else if(endTime>endWorkTime){
									currentWorkMillisecond= startWorkTime-currentDate.getTime()+endTime-endWorkTime;
								}
							}
							//隔天按每天8小时计算
							else {
								currentWorkMillisecond=1000*60*60*8;
							}
						}
					

					currentWorkHours=currentWorkMillisecond/(1000*60*30)*1.0d/2;
					overtimeWage+=currentWorkHours*times*unit;
					//System.out.println(currentWorkMillisecond);
					//System.out.println(currentWorkMillisecond/(1000*60*30));
					//System.out.println(currentWorkMillisecond/(1000*60*30)*1.0d/2);
					//System.out.println(currentWorkHours+"*"+times);
				}
			}
		}
		return overtimeWage;
	}
	
	/*public static void main(String[] args) {
		String date1="2014-04-08 9:45:00";
		String date2="2014-04-09 14:30:40";
		Date startDate=DateUtil.parseDate(date1, "yyyy-MM-dd hh:mm:ss");
		Date endDate=DateUtil.parseDate(date2, "yyyy-MM-dd hh:mm:ss");
		double salary=DateUtil.getOvertimeWage(startDate, endDate, 20);
		System.out.println(salary);
		
	}*/
}

