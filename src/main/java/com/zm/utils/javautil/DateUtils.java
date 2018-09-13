package com.zm.utils.javautil;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.util.StringUtils;

public class DateUtils {
	
	public static String DATE_FORMAT = "yyyy-MM-dd";

	public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String DATE_TIME_FORMAT2 = "yyyyMMddHHmmss";

	public static String DATE_FORMAT2 = "yyyyMMdd";

	public static String DATE_TIME_FORMAT3 = "yyyy/MM/dd HH:mm:ss";

	public static String DATE_TIME_FORMAT4 = "yyyy/MM/dd";

	public static String TIME_FORMAT_START = "000000";

	public static String TIME_FORMAT_END = "235959";

	public static String TIME_FORMAT = "HHmmss";

	public static String HQL_DATETIME_FORMAT = "%Y-%m-%d-%H-%h-%i";
	public static String WEEKS[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
			"星期六" };

	public final static int TIME_DAY_MILLISECOND = 86400000;

	public static final SimpleDateFormat format = new SimpleDateFormat(
			DATE_FORMAT);
	public static final SimpleDateFormat date_time_format = new SimpleDateFormat(
			DATE_TIME_FORMAT);
	
	/**
	 * 耗时计算
	 * 
	 * @return 返回所用时间类型
	 */
	public static long getUseSecond(Date startDate, Date endDate) {
		long t1 = startDate.getTime(); // 开始时间
		long t2 = endDate.getTime(); // 结束时间
		return (t2 - t1)/1000;
	}
	
	/**
	 * 时间比较
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static int getCompareDate(Date startDate, Date endDate) {
		 Calendar c1=Calendar.getInstance();
		 Calendar c2=Calendar.getInstance();
		 c1.setTime(startDate);
		 c2.setTime(endDate);
		 int result=c1.compareTo(c2);
		 return result;
	}
	
	/**
	 * <p>描述： </p>
	 * <p>日期：2015-3-21 下午8:13:39 </p>
	 * @param startDate
	 * @param format
	 * @return
	 */
	private static String strToDate(Date startDate, String format) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static String getToday(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(currentTime);
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		try {
			currentTime = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return currentTime;
	}
	
	/**
	 * 获取一个月的第一天的日期yyyy-MM-dd 00:00:00
	 * 
	 * @param dat
	 * @return
	 */
	public static Date getStartDateOfMonth(Date date) {// yyyy-MM-dd
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, -2);
		//c.add(Calendar.MONTH, 1);
		//c.add(Calendar.DAY_OF_MONTH, -1);
		return DateUtils.getStartOfDate(c.getTime());
	}


	/**
	 * 
	 * <p>描述:获取当天日期</p>
	 * <p>日期：2012-10-25</p>
	 * @author  ldz
	 * @return
	 */
	public static int getDayOfMonth(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 获取现在时间
	 * 
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}
	
	public static String getYMD(String val) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String value = "";
		try{
			Date date  = formatter.parse(val);
			value = formatter.format(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return value;
	}
	
//	public static void main(String[] args) {
//		String getYMD = getYMD("2015-05-09 00:00:00");
//		System.out.println(getYMD);
		
//		System.out.println(getYearMonth());
		
		
//		 //获取前月的最后一天
//	    Calendar cale = Calendar.getInstance();  
//	    cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
//	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//	    String lastDay = format.format(cale.getTime());
//	    System.out.println("-----2------lastDay:"+lastDay);
		
		//System.out.println("0----------------");
//	}
	
	public static String findLastDay(){
		 //获取前月的最后一天
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();   
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
	    return format.format(ca.getTime());
	}
	
	/**
	 * 获取当前年月
	 * <p>描述： </p>
	 * <p>日期：2015-5-28 下午7:23:17 </p>
	 * @return
	 */
	public static String getYear() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime()); 
	}
	
	/**
	 * 
	 * <p>描述:获取某日期的开始时间：2013-5-27 00:00:00</p>
	 * <p>日期：2013-5-27</p>
	 * @author  ldz
	 * @param date
	 * @return
	 */
	public static Date getStartOfDate(Date date){
		if(date != null){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String endTime = df.format(date)+" 00:00:00";
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = df2.parse(endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
	/**
	 * <p>描述：根据指定日期,标识，加减的（年，月，日，时，分，秒）数，返回对应的日期 </p>
	 * <p>日期：2014-12-3 上午8:47:04 </p>
	 * @param date 指定日期
	 * @param flag 标识（1：年，2：月，3：日，4：时，5：分，6：秒）
	 * @param num 加减的（年，月，日，时，分，秒）数
	 * 				正数代表加，负数代表减
	 * @return 加减后的日期
	 */
	public static Date getAddOfSubDate(Date date, Integer num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, num);
		return calendar.getTime();
	}
	
	/**
	 * <p>描述：根据指定日期,标识，加减的（年，月，日，时，分，秒）数，返回对应的日期 </p>
	 * <p>日期：2014-12-3 上午8:47:04 </p>
	 * @param date 指定日期
	 * @param flag 标识（1：年，2：月，3：日，4：时，5：分，6：秒）
	 * @param num 加减的（年，月，日，时，分，秒）数
	 * 				正数代表加，负数代表减
	 * @return 加减后的日期
	 */
	public static Date getAddOfFZDate(Date date, Integer num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, num);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * <p>描述:获取某日期所在周，周一的日期</p>
	 * <p>日期：Jan 13, 2014</p>
	 * @author  ldz
	 * @param date
	 * @return
	 */
	public static Date getDayOfWeek(Date date,int num){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (num == 1) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num == 2) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num == 3) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num == 4) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num == 5) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num == 6) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num  == 0) {// 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
		return c.getTime();
	}
	
	/**
	 * 获取一个月的最后一天的日期yyyy-MM-dd 23:59:59
	 * 
	 * @param dat
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {// yyyy-MM-dd
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return DateUtils.getEndOfDay(c.getTime());
	}
	
	/**
	 * 
	 * <p>描述:将日期转换为指定格式</p>
	 * <p>日期：2012-12-22</p>
	 * @author  ldz
	 * @param date
	 * @param formate
	 * @return
	 */
	public static Date getFormatDate(Date date,String formate){
		SimpleDateFormat formatter = new SimpleDateFormat(formate);
		String dateString = formatter.format(date);
		ParsePosition pos = new ParsePosition(0);
		Date curDate = formatter.parse(dateString, pos);
		return curDate;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy年MM月dd日 HH:mm:ss
	 */
	public static String getCHStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日 HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy年MM月dd日 HH:mm:ss
	 */
	public static String getCHStringDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日 HH:mm:ss");
		String dateString = formatter.format(date == null?new Date() : date);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 获取字符串时间
	 */
	public static String getFormateDate(Date date ,String formate) {
		Date currentTime =date;
		SimpleDateFormat formatter = new SimpleDateFormat(formate);
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 
	 * <p>描述:获取某天最后时间yyyy-MM-dd HH:mm:ss</p>
	 * <p>日期：2012-12-20</p>
	 * @author  ldz
	 * @param d
	 * @return
	 */
	public static Date getEndOfDay(Date d){
		if(d == null){
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(d);
		try {
			d =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString +" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 获取当前时间以指定格式字符串
	 * <p>时间：2012-12-4 下午01:40:33</p>
	 * <p>说明: </p>
	 * @author  chenrongxiang
	 * @return	
	 */
	public static String getDateStringByFormat(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(new Date());
		return dateString;
	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 时间格式化（今天、昨天、前天、月月-日日 时时:分分）
	 * @param dateStr 年年年年-月月-日日 时时:分分:秒秒
	 * @param format HH:mm:ss HH:mm HH
	 * @return
	 */
	public static String getFormatTimeByOther(String dateStr, String format) {
		String retStr;
		Date date = strToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
		long lastDay = getDays(date, new Date());
		switch (Long.valueOf(lastDay).intValue()) {
		case 0:
			retStr = "今天" + getFormateDate(date, format);
			break;
		case 1:
			retStr = "昨天" + getFormateDate(date, format);
			break;
		case 2:
			retStr = "前天" + getFormateDate(date, format);
			break;
		default:
			retStr =  getFormateDate(date, "MM-dd " + format);
			break;
		}
		return retStr;		
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		if (strDate != null) {
			Date strtodate = formatter.parse(strDate, pos);
			return strtodate;
		}
		return null;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		if(dateDate == null){
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(Date dateDate) {
		if(dateDate == null){
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		if (strDate == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	/**
	 * 将时间格式字符串转换为时间 yyyy-MM-dd hh:mm
	 * @param strDate
	 * @return
	 */
	public static Date strToDateYYYYmmddhhmm(String strDate) {
		if (strDate == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	/**
	 * 将时间格式字符串转换为时间对象
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date strToDate(String dateStr, String format) {
		if (dateStr == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		return sdf.parse(dateStr, pos);
	}

	/**
	 * 得到现在时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	public static Date afterDate(Date date){
		Date afterDate = new Date(date.getTime() + 60000);
		return afterDate;
	}
	
	public static Date afterDate1(Date date){
		Date afterDate = new Date(date.getTime() + 60000);
		return afterDate;
	}
	
//	public static void main(String[] args) {
		
		String str = "klsjf.iasoiqupr.iqwer.jpg";
//		System.out.println(str.substring(str.lastIndexOf("."), str.length()));
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("123", new Date());
		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
//		System.out.println(getCompareDate(afterDate((Date)map.get("123")),new Date()));
		//System.out.println(formatter.format(getFirstTwoMin(new Date())));
		
		//System.out.println(SHA512.encode("123"));
//	}
	//ujJTh2rta8ItSm/1PYQGxq2GQZXtFEq1yHYhtsIztUi66uaVbfNG7IwX9eoQ817jy8UUeX7X3dMUVGTioLq0Ew==
	
	/**
	 * 提取一个月中的最后一天
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 HHmmss
	 */
	public static String getHHmmss() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 HHmmss
	 */
	public static String getDDMMYY() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("DDMMYY");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 * 
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
					/ 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
					/ 60;
			if ((y - u) > 0)
				return y - u + "";
			else
				return "0";
		}
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 时间前推或后推分钟,其中JJ表示分钟.
	 */
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
					* 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 判断是否润年
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 返回美国时间格式 26 Apr 2006
	 * 
	 * @param str
	 * @return
	 */
	public static String getEDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	}

	/**
	 * 获取一个月的最后一天
	 * 
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * 判断二个时间是否在同一个周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * 
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号； 这里星期天是作为一周的开始计算的。
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = DateUtils.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = DateUtils.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static String getWeekStr(String sdate) {
		String str = "";
		str = DateUtils.getWeek(sdate);
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str)) {
			str = "星期四";
		} else if ("6".equals(str)) {
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}
	
	/**
	 * 返回两个日期之间的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;
		// 转换为标准时间
		date1 = getFormatDate(date1, "yyyy-MM-dd");
		date2 = getFormatDate(date2, "yyyy-MM-dd");
		return (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
	}

	/**
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
	 * 此函数返回该日历第一行星期日所在的日期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getNowMonth(String sdate) {
		// 取该时间所在月的一号
		sdate = sdate.substring(0, 8) + "01";

		// 得到这个月的1号是星期几
		Date date = DateUtils.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = DateUtils.getNextDay(sdate, (1 - u) + "");
		return newday;
	}

	/**
	 * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
	 * 
	 * @param k
	 *            表示是取几位随机数，可以自己定
	 */

	public static String getNo(int k) {

		return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	}

	/**
	 * 返回一个随机数
	 * 
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();
		// int suiJiShu = jjj.nextInt(9);
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	/**
	 * 
	 * @param args
	 */
	public static boolean RightDate(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (date == null)
			return false;
		if (date.length() > 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			sdf.parse(date);
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	/**
	 * 将formBean 里的字符时间(yyyy-MM-dd) 转换成Date类型
	 * 
	 * @param formDate
	 * @return
	 */
	public static Date formBeanDateToPODate(String formDate) {
		try {
			if (formDate != null) {
				if (!formDate.trim().equals("")) {
					return java.sql.Date.valueOf(formDate);
				}
			}
		} catch (Exception e) {
//			System.out.println("DateUtils:时间转换异常");
			return new Date();
		}
		return null;

	}

	/**
	 * 
	 * <p>
	 * 创建时间：2011 Apr 7, 2011 10:55:02 AM
	 * </p>
	 * <p>
	 * 说明:两个时间间隔毫秒数
	 * </p>
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static long getMillisecondsBetween(Date startTime, Date endTime) {
		long milliseconds = (endTime.getTime() - startTime.getTime());
		return milliseconds;
	}

	/**
	 * 获取最近几周，起始日期,以周一作为一周第一天。
	 * 
	 * @param weeks
	 *            周数 （参数举例：获取最近8周，传参数-7）
	 * @return
	 */
	public static Date getWeekDay(int weeks, List<Weeks> weekList) {
		Calendar c = Calendar.getInstance();
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			dayOfWeek = 8;
		}
		c.add(Calendar.DAY_OF_YEAR, (dayOfWeek - 2) * -1);
		Weeks w = null;
		for (int i = 0; i >= weeks; i--) {
			if (i != 0) {
				c.add(Calendar.WEEK_OF_YEAR, -1);
			}
			w = new Weeks();
			w.setIndex(-1 * i + 1);
			w.setStartDate(DateFormatUtils.format(c.getTime(), "yyyy-MM-dd"));
			c.add(Calendar.DAY_OF_YEAR, 6);
			w.setEndDate(DateFormatUtils.format(c.getTime(), "yyyy-MM-dd"));
			c.add(Calendar.DAY_OF_YEAR, -6);
			int wks = c.get(Calendar.WEEK_OF_YEAR);
			if (wks < 10) {
				w.setWeekOfYear("0" + c.get(Calendar.WEEK_OF_YEAR));
			} else {
				w.setWeekOfYear(c.get(Calendar.WEEK_OF_YEAR) + "");
			}
			if (weekList != null) {
				weekList.add(w);
			}
		}
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		Date rd = c.getTime();
		c.clear();
		return rd;

	}
	
	/**
     * 当月第一天
     * @return
	 * @throws ParseException 
     */
	public static Date getFirstDay() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return  sdf.parse(str.toString());
    }
    
    /**
     * 当月最后一天
     * @return
     * @throws ParseException 
     */
	public static Date getLastDay() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String s = df.format(calendar.getTime());
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.parse(str.toString());
    }
	
	/**
	 * 
	 * <p>描述:获取当前距取前/之后几个月的日期</p>
	 * <p>日期：2012-12-10</p>
	 * @author  ldz
	 * @param month 大于0表示当前日期后几个月的日期，小于0表示之前几个月的日期
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDatePreMonth(int month) throws ParseException{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String s = formatter.format(c.getTime());
		StringBuffer str = new StringBuffer().append(s).append(" 00:00:00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.parse(str.toString());
	}

	/**
	 * 在时间上增加制定的毫秒数
	 * <p>时间：2013-1-9 上午10:59:05</p>
	 * <p>说明: </p>
	 * @author  chenrongxiang
	 * @param date 待处理的时间
	 * @param millisecond 需要增加的毫秒数
	 * @return 增加后的时间
	 */
	public static Date addMillisecondsOnDate(Date date, long millisecond){
		long currTime = date.getTime();
		long adjustTime = currTime + millisecond;
		return new Date(adjustTime);
	}
	
	/** 
	* 获得本季度第一天 
	* 
	* @param month 
	* @return 
	*/ 

	public static String getThisSeasonFirstTime(int month) { 
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } }; 
		int season = 1; 
		if (month >= 1 && month <= 3) { 
		season = 1; 
		} 
		if (month >= 4 && month <= 6) { 
		season = 2; 
		} 
		if (month >= 7 && month <= 9) { 
		season = 3; 
		} 
		if (month >= 10 && month <= 12) { 
		season = 4; 
		} 
		int start_month = array[season - 1][0]; 
		int end_month = array[season - 1][2]; 

		Date date = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式 
		String years = dateFormat.format(date); 
		int years_value = Integer.parseInt(years); 

		int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month); 
		@SuppressWarnings("unused") 
		int end_days = getLastDayOfMonth(years_value, end_month); 
		String seasonDate = years_value + "-" + start_month + "-" + start_days; 
		return seasonDate; 

		} 

	/** 
	* 获得本季度最后一天 
	* 
	* @param month 
	* @return 
	*/ 
	@SuppressWarnings("unused") 
	public static String getThisSeasonFinallyTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int start_month = array[season - 1][0];
		int end_month = array[season - 1][2];

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);

		int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
		int end_days = getLastDayOfMonth(years_value, end_month);
		String seasonDate = years_value + "-" + end_month + "-" + end_days;
		return seasonDate;

	} 

	/**
	 * 
	 * <p>
	 * 时间：2013-4-16 下午03:16:52
	 * </p>
	 * <p>
	 * 说明: 最后一天
	 * </p>
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * 
	 * <p>
	 * 时间：2013-4-16 下午03:15:59
	 * </p>
	 * <p>
	 * 说明: 是否闰年
	 * </p>
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * 
	 * <p>
	 * 时间：2013-4-16 下午03:15:44
	 * </p>
	 * <p>
	 * 说明: 获取当前月
	 * </p>
	 * 
	 * @return
	 */
	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static String getHalfYearFirstTime(int month) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String years = dateFormat.format(date);
		String firstTime = "";
		if (month < 7) {
			firstTime = years + "-1-1 00:00:00";
		} else {
			firstTime = years + "-7-1 00:00:00";
		}
		return firstTime;
	}

	public static String getHalfYearFinallyTime(int month) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String years = dateFormat.format(date);
		String lastTime = "";
		if (month < 7) {
			lastTime = years + "-6-30 23:59:59";
		} else {
			lastTime = years + "-12-31 23:59:59";
		}
		return lastTime;
	}

	public static Date getEndDate(Date queryEndTime) {
		if (queryEndTime != null && !("").equals(queryEndTime)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String endTime = df.format(queryEndTime) + " 23:59:59";
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				queryEndTime = df2.parse(endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return queryEndTime;
	}
	
	//获取本周第一天
	public static String getThisWeekfirst(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// 获取本周一的日期
		return df.format(cal.getTime());
	}
		
	//获取本周最后一天
	public static String getThisWeeklast(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return df.format(cal.getTime());
	}
	
	public static String getDateToStr(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	public static Date getDateByLong(Long tLong){
		if (StringUtils.isEmpty(tLong)){
			return null;
		}
		Date d = new Date(tLong);
		return d;
	}
	
	/**
	 * 获取时间差
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static String getDDHHmmssDateSplit(Date beginDate, Date endDate) {
		if (beginDate == null || endDate == null) {
			return null;
		}
		long time1 = beginDate.getTime();
		long time2 = endDate.getTime();
		long diff = Math.abs(time2 - time1);
		long day = diff / (24 * 60 * 60 * 1000);
		String dayStr = String.valueOf(day);
		long hour = (diff / (60 * 60 * 1000) - day * 24);
		String hourStr = String.valueOf(hour);
		if (hour < 9){
			hourStr = "0" + hourStr;
		}
		long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		String minStr = String.valueOf(min);
		if (min < 9){
			minStr = "0" + minStr;
		}
		long sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String secStr = String.valueOf(sec);
		if (sec < 9){
			secStr = "0" + secStr;
		}
		String result = "";
		if (day > 0) {
			result = dayStr + "天" + hourStr + ":" + minStr + ":" + secStr;
		} else {
			result = hourStr + ":" + minStr + ":" + secStr;
		}
		return result;
	}
	
	/**
	 * 获取时间差
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static String getHHmmssDateSplit(Date beginDate, Date endDate) {
		if (beginDate == null || endDate == null) {
			return null;
		}
		long time1 = beginDate.getTime();
		long time2 = endDate.getTime();
		long diff = Math.abs(time2 - time1);
		long day = diff / (24 * 60 * 60 * 1000);
		long hour = (diff / (60 * 60 * 1000) - day * 24);
		String hourStr = String.valueOf(hour);
		if (hour < 9){
			hourStr = "0" + hourStr;
		}
		long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		String minStr = String.valueOf(min);
		if (min < 9){
			minStr = "0" + minStr;
		}
		long sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String secStr = String.valueOf(sec);
		if (sec < 9){
			secStr = "0" + secStr;
		}
		return hourStr + ":" + minStr + ":" + secStr;
	}
	/**
	 * 把日期字符串转成长整形
	 * @param s
	 * @param sFormat
	 * @return
	 * @throws ParseException 
	 */
	public static long getLongDateTime(String s,String sFormat) throws ParseException{
		//转成长整形
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		GregorianCalendar cal=new GregorianCalendar();
		SimpleDateFormat SDateFormat =new SimpleDateFormat(sFormat);
		cal.setTime(SDateFormat.parse(s));
		return cal.getTimeInMillis();
	}
	/**
	 * 把长整形转换成日期字符串
	 * @param l
	 * @param sFormat
	 * @return
	 * @throws ParseException
	 */
	public static String getStringDateTime(long l,String sFormat) throws ParseException{
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		GregorianCalendar cal=new GregorianCalendar();
		SimpleDateFormat SDateFormat =new SimpleDateFormat(sFormat);
		cal.setTimeInMillis(l);		
		return SDateFormat.format(cal.getTime());
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtils.getStringDate(DateUtils.getDateByLong(1464344038000L)));
		/*Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String d = df.format(date);
		try {
			Date da = df.parse(d);
			System.out.println(da);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
	}
	
}
