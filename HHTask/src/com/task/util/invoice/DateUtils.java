package com.task.util.invoice;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.util.StringUtils;

import com.sun.istack.internal.Nullable;

public class DateUtils extends PropertyEditorSupport {
	// 各种时间格式
	public static final SimpleDateFormat date_sdf = new SimpleDateFormat(
			"yyyy-MM-dd");
	// 各种时间格式
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
			"yyyyMMdd");
	public static final SimpleDateFormat yyyyMM = new SimpleDateFormat(
			"yyyyMM");
	// 各种时间格式
	public static final SimpleDateFormat date_sdf_wz = new SimpleDateFormat(
			"yyyy年MM月dd日");
	public static final SimpleDateFormat time_sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat(
	"yyyyMMddHHmmss");
	public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat(
			"HH:mm");
	public static final  SimpleDateFormat datetimeFormat = new SimpleDateFormat(
	"yyyy-MM-dd HH:mm:ss");

	public static final  SimpleDateFormat yyyymmddthhmmssz = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

	public static final  SimpleDateFormat yyyymmddthhmmssSSS = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");
	public static final  SimpleDateFormat yyyy_MM = new SimpleDateFormat(
			"yyyy-MM");
	// 以毫秒表示的时间
	private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
	private static final long HOUR_IN_MILLIS = 3600 * 1000;
	private static final long MINUTE_IN_MILLIS = 60 * 1000;
	private static final long SECOND_IN_MILLIS = 1000;
	// 指定模式的时间格式
	private static SimpleDateFormat getSDFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	/**
	 * 当前日历，这里用中国时间表示
	 * 
	 * @return 以当地时区表示的系统当前日历
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 指定毫秒数表示的日历
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日历
	 */
	public static Calendar getCalendar(long millis) {
		Calendar cal = Calendar.getInstance();
		// --------------------cal.setTimeInMillis(millis);
		cal.setTime(new Date(millis));
		return cal;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// getDate
	// 各种方式获取的Date
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 当前日期
	 * 
	 * @return 系统当前时间
	 */
	public static Date getDate() {
		return new Date();
	}

	public static Date toDate(String pattern){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(getDate());
        try {
            Date parse = formatter.parse(dateString);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date str2Date(String date){
		return str2Date(date, date_sdf);
	}

	public static String formatStr2YYYYMM(String date){
    	try {
			return formatDate(str2Date(date, yyyy_MM), "yyyyMM");
		}catch (Exception ignore){
    		return formatDate(getDate(), "yyyyMM");
		}
	}

	public static String formatDate2YYYY_MM(Date date){
		return formatDate(date, "yyyy-MM");
	}

	public static @Nullable String formatYYYYMM2YYYY_MM(String date){
		try{
			return formatDate(str2Date(date, yyyyMM), "yyyy-MM");
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 指定毫秒数表示的日期
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日期
	 */
	public static Date getDate(long millis) {
		return new Date(millis);
	}

	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamptoStr(Timestamp time) {
		Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return date2Str(date_sdf);
	}

	/**
	 * 字符串转换时间戳
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {
		Date date = str2Date(str, date_sdf);
		return new Timestamp(date.getTime());
	}
	/**
	 * 字符串转换成日期
	 * @param str
	 * @param sdf
	 * @return
	 */
	public static Date str2Date(String str, SimpleDateFormat sdf) {
		if (null == str || "".equals(str)) {
			return null;
		}
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期转换为字符串
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(SimpleDateFormat date_sdf) {
		Date date=getDate();
		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}



	/**
	 * 格式化时间
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateformat(String date,String format)
	{
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Date _date=null;
		try {
			 _date=sformat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sformat.format(_date);
	}
	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @return 字符串
	 */
	public static String date2Str(Date date, SimpleDateFormat date_sdf) {
		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}
	/**
	 * 日期转换为字符串
	 * 
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String getDate(String format) {
		Date date=new Date();
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 指定毫秒数的时间戳
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数的时间戳
	 */
	public static Timestamp getTimestamp(long millis) {
		return new Timestamp(millis);
	}

	/**
	 * 以字符形式表示的时间戳
	 * 
	 * @param time
	 *            毫秒数
	 * @return 以字符形式表示的时间戳
	 */
	public static Timestamp getTimestamp(String time) {
		return new Timestamp(Long.parseLong(time));
	}

	/**
	 * 系统当前的时间戳
	 * 
	 * @return 系统当前的时间戳
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 指定日期的时间戳
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期的时间戳
	 */
	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 指定日历的时间戳
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的时间戳
	 */
	public static Timestamp getCalendarTimestamp(Calendar cal) {
		// ---------------------return new Timestamp(cal.getTimeInMillis());
		return new Timestamp(cal.getTime().getTime());
	}

	public static Timestamp gettimestamp() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		Timestamp buydate = Timestamp.valueOf(nowTime);
		return buydate;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// getMillis
	// 各种方式获取的Millis
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 系统时间的毫秒数
	 * 
	 * @return 系统时间的毫秒数
	 */
	public static long getMillis() {
		return new Date().getTime();
	}

	/**
	 * 指定日历的毫秒数
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的毫秒数
	 */
	public static long getMillis(Calendar cal) {
		// --------------------return cal.getTimeInMillis();
		return cal.getTime().getTime();
	}

	/**
	 * 指定日期的毫秒数
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期的毫秒数
	 */
	public static long getMillis(Date date) {
		return date.getTime();
	}

	/**
	 * 指定时间戳的毫秒数
	 * 
	 * @param ts
	 *            指定时间戳
	 * @return 指定时间戳的毫秒数
	 */
	public static long getMillis(Timestamp ts) {
		return ts.getTime();
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatDate
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：年-月-日
	 * 
	 * @return 默认日期按“年-月-日“格式显示
	 */
	public static String formatDate() {
		return date_sdf.format(getCalendar().getTime());
	}
	/**
	 * 获取时间字符串
	 */
	public static String getDataString(SimpleDateFormat formatstr) {
		return formatstr.format(getCalendar().getTime());
	}
	/**
	 * 指定日期的默认显示，具体格式：年-月-日
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“年-月-日“格式显示
	 */
	public static String formatDate(Calendar cal) {
		return date_sdf.format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“年-月-日“格式显示
	 */
	public static String formatDate(Date date) {
		if(date == null)return "";
		return date_sdf.format(date);
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：年-月-日
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“年-月-日“格式显示
	 */
	public static String formatDate(long millis) {
		return date_sdf.format(new Date(millis));
	}

	/**
	 * 默认日期按指定格式显示
	 * 
	 * @param pattern
	 *            指定的格式
	 * @return 默认日期按指定格式显示
	 */
	public static String formatDate(String pattern) {
		return getSDFormat(pattern).format(getCalendar().getTime());
	}

	/**
	 * 指定日期按指定格式显示
	 * 
	 * @param cal
	 *            指定的日期
	 * @param pattern
	 *            指定的格式
	 * @return 指定日期按指定格式显示
	 */
	public static String formatDate(Calendar cal, String pattern) {
		return getSDFormat(pattern).format(cal.getTime());
	}

	/**
	 * 指定日期按指定格式显示
	 * 
	 * @param date
	 *            指定的日期
	 * @param pattern
	 *            指定的格式
	 * @return 指定日期按指定格式显示
	 */
	public static String formatDate(Date date, String pattern) {
		return getSDFormat(pattern).format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatTime
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：年-月-日 时：分
	 * 
	 * @return 默认日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime() {
		return time_sdf.format(getCalendar().getTime());
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(long millis) {
		return time_sdf.format(new Date(millis));
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(Calendar cal) {
		return time_sdf.format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(Date date) {
		return time_sdf.format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatShortTime
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：时：分
	 * 
	 * @return 默认日期按“时：分“格式显示
	 */
	public static String formatShortTime() {
		return short_time_sdf.format(getCalendar().getTime());
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：时：分
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“时：分“格式显示
	 */
	public static String formatShortTime(long millis) {
		return short_time_sdf.format(new Date(millis));
	}

	/**
	 * 指定日期的默认显示，具体格式：时：分
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“时：分“格式显示
	 */
	public static String formatShortTime(Calendar cal) {
		return short_time_sdf.format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：时：分
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“时：分“格式显示
	 */
	public static String formatShortTime(Date date) {
		return short_time_sdf.format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// parseDate
	// parseCalendar
	// parseTimestamp
	// 将字符串按照一定的格式转化为日期或时间
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws ParseException
	 */
	public static Date parseDate(String src, String pattern)
			throws ParseException {
		return getSDFormat(pattern).parse(src);

	}

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws ParseException
	 */
	public static Calendar parseCalendar(String src, String pattern)
			throws ParseException {

		Date date = parseDate(src, pattern);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static String formatAddDate(String src, String pattern, int amount)
			throws ParseException {
		Calendar cal;
		cal = parseCalendar(src, pattern);
		cal.add(Calendar.DATE, amount);
		return formatDate(cal);
	}

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的时间戳
	 * @throws ParseException
	 * @throws
	 */
	public static Timestamp parseTimestamp(String src, String pattern)
			throws ParseException {
		Date date = parseDate(src, pattern);
		return new Timestamp(date.getTime());
	}

	// ////////////////////////////////////////////////////////////////////////////
	// dateDiff
	// 计算两个日期之间的差值
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 计算两个时间之间的差值，根据标志的不同而不同
	 * 
	 * @param flag
	 *            计算标志，表示按照年/月/日/时/分/秒等计算
	 * @param calSrc
	 *            减数
	 * @param calDes
	 *            被减数
	 * @return 两个日期之间的差值
	 */
	public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

		long millisDiff = getMillis(calSrc) - getMillis(calDes);

		if (flag == 'y') {
			return (calSrc.get(calSrc.YEAR) - calDes.get(calDes.YEAR));
		}

		if (flag == 'd') {
			return (int) (millisDiff / DAY_IN_MILLIS);
		}

		if (flag == 'h') {
			return (int) (millisDiff / HOUR_IN_MILLIS);
		}

		if (flag == 'm') {
			return (int) (millisDiff / MINUTE_IN_MILLIS);
		}

		if (flag == 's') {
			return (int) (millisDiff / SECOND_IN_MILLIS);
		}

		return 0;
	}
    /**
     * String类型 转换为Date,
     * 如果参数长度为10 转换格式”yyyy-MM-dd“
     *如果参数长度为19 转换格式”yyyy-MM-dd HH:mm:ss“
     * * @param text
	 *             String类型的时间值
     */
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				if (text.indexOf(":") == -1 && text.length() == 10) {
					setValue(this.date_sdf.parse(text));
				} else if (text.indexOf(":") > 0 && text.length() == 19) {
					setValue(this.datetimeFormat.parse(text));
				} else {
					throw new IllegalArgumentException(
							"Could not parse date, date format is error ");
				}
			} catch (ParseException ex) {
				IllegalArgumentException iae = new IllegalArgumentException(
						"Could not parse date: " + ex.getMessage());
				iae.initCause(ex);
				throw iae;
			}
		} else {
			setValue(null);
		}
	}
	public static int getYear(){
	    GregorianCalendar calendar=new GregorianCalendar();
	    calendar.setTime(getDate());
	    return calendar.get(Calendar.YEAR);
	  }
	public static int getYear(Date date){
	    GregorianCalendar calendar=new GregorianCalendar();
	    calendar.setTime(date);
	    return calendar.get(Calendar.YEAR);
	  }
	
	 /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }
    /**
     * 获取明年的第一天
     * @return
     */
    public static Date getNextYearFirst(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear+1);
    }
     
    /**
     * 获取当年的最后一天
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }
     
    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
     
    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
         
        return currYearLast;
    }
    
    /**
     * 获取当月最后一天日期
     * @param dateStr yyyyMM
     * @return String
     */
    public static String getMonthLastByStr(String dateStr){
    	SimpleDateFormat mat = new SimpleDateFormat("yyyyMM");
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar ca = Calendar.getInstance();   
    	try {
			ca.setTime(mat.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = format.format(ca.getTime());
        return last;
    }

	/**
	 * @描述: 获取某个月的最后一天，返回格式yyyy年mm月dd日
	 * @return
	 */
	public static String getLastDayByFormat(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDayOfMonth = calendar.getTime();
		return new SimpleDateFormat("yyyy年MM月dd日").format(lastDayOfMonth);
	}
    
    /**
     * 获取某月最后一天日期
     * @return String
     */
    public static String getMonthLast(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = format.format(ca.getTime());
        return last;
    }
    
    /**
     * 获取当月第一天日期
     * @return String
     */
    public static String getMonthFirst(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String first = format.format(c.getTime());
        return first;
    }
    
    /**
     * 获取某月第一天日期
     * @param dateStr yyyyMM
     * @return String
     */
    public static String getMonthFirstByStr(String dateStr){
    	SimpleDateFormat mat = new SimpleDateFormat("yyyyMM");
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar c = Calendar.getInstance();    
    	try {
			c.setTime(mat.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String first = format.format(c.getTime());
        return first;
    }
    
    public static String getPastMonthFirst(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String first = format.format(c.getTime());
        return first;
    }
    
    public static String getYear(String date) {
    	String year = "";
    	try {
    		year = String.valueOf(getYear(parseDate(date, "yyyy-MM-dd")));
		} catch (ParseException e) {
		}
    	return year;
    }
	public static Date addDate(Date date, int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, amount);
		return cal.getTime();
	}

	public static Date addHour(Date date, int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, amount);
		return cal.getTime();
	}

    public static String getTodayStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowStr = sdf.format(new Date());
        return nowStr;
    }

    public static boolean isToday(Date date){
        SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
        if(fmt.format(date).toString().equals(fmt.format(new Date()).toString())){//格式化为相同格式
            return true;
        }else {
            return false;
        }
    }

	public static Date getSearchDate(String sDate){
		return str2Date(sDate,date_sdf);
	}
	
	public static Date getYyyyMMddDate(String sDate){
		return str2Date(sDate,yyyyMMdd);
	}
	
	public static Date getSearchDateAdd1(String sDate){
		return addDate(str2Date(sDate,date_sdf),1);
	}

    public static String strToDateFormat(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false);
        Date newDate= null;
        try {
            newDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(newDate);
    }

    /**
     * 返回一个当前的时间，并按格式转换为字符串
     * 
     * @return String
     */
    public static String getDateTime(String... pattern){   

//        GregorianCalendar gcNow = new GregorianCalendar();
//        return DateFormatUtils.format(gcNow,pattern);
    	String sPtn = "yyyy-MM-dd HH:mm:ss";
		if (pattern != null&&pattern.length>0) {
			sPtn = pattern[0];
		}	
        SimpleDateFormat  formatter = new SimpleDateFormat (sPtn);
        return formatter.format(new Date());
    }

    public static String toChinese(String dateString){
        String s_nd = dateString.substring(0, 4); // 年份
        String s_yf = dateString.substring(5, 7); // 月份
        String s_rq = dateString.substring(8, 10);
        return s_nd + "年" + s_yf + "月" + s_rq + "日";
    }

        public static List<String> getDayListByMonth(String month){
    	List<String> dayList = new ArrayList<String>();
		SimpleDateFormat mat = new SimpleDateFormat("yyyyMM");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(mat.parse(month));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.MONTH, 0);
		int day = c.getActualMaximum(Calendar.DATE);
		for (int i = 1; i <= day; i++) {
			String dayStr = String.valueOf(i);
			dayList.add(dayStr.length()<2?("0" + dayStr):dayStr);
		}
		return dayList;
	}

	public static List<String> getDayList(int dayNum){
		List<String> dayList = new ArrayList<String>();
		for (int i = 1; i <= dayNum; i++) {
			String dayStr = String.valueOf(i);
			dayList.add(dayStr.length()<2?("0" + dayStr):dayStr);
		}
		return dayList;
	}

	public static String getYesterDay(){
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
        return yesterday;
    }

	public static void main(String[] args) {
		/*List<String> list = DateUtils.getDayList(15);
		for(String str:list) {
			System.out.println(str);
		}*/
        System.out.println(DateUtils.getYesterDay());

	}
	
	  /** 
	    * @Title: 获取月的天数 
	    * @param @param dateStr
	    * @param @return    设定文件 
	    * @return int    返回类型 
	    * @throws 
	    */
	    public static int getMonthDays(String dateStr){
	    	 Calendar calendar = Calendar.getInstance();
	     	try {
	 			calendar.setTime(new SimpleDateFormat("yyyyMM").parse(dateStr));
	 		} catch (ParseException e) {
	 			e.printStackTrace();
	 		}
	     	calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1);
	     	calendar.roll(Calendar.DATE, false);
	     	return calendar.get(Calendar.DATE);
	     }

	/**
	 * 验证日期格式
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static boolean isValidDate(String str,String pattern) {
		boolean convertSuccess=true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess=false;
		}
		return convertSuccess;
	}

	public static boolean isBeyondOneYear(Date d) {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.YEAR,1);
		Date proxyNow = c.getTime();
		return now.after(proxyNow);
	}

}