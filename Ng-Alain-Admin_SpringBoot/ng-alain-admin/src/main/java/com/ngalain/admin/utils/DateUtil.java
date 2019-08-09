package com.ngalain.admin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateUtil {
    //private static Log log = LogFactory.getLog(DateUtil.class);

    public static final String FORMATER_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMATER_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATER_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 返回java.sql.Date类型的当前时间
     *
     * @return
     */
    public static java.sql.Date getSqlDate() {
        return getSqlDate(new Date());
    }

    /**
     * 返回java.sql.Date类型的时间
     *
     * @param date
     * @return
     */
    public static java.sql.Date getSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 以"yyyy-MM-dd"格式来格式化日期
     *
     * @param date
     * @return
     */
    public static String formatFromDate(Date date) {
        return formatFromDate(FORMATER_YYYY_MM_DD, date);
    }

    public static Date getCurrentDate() {
        Date now = getCurrentDateTime();
        return strToDate(dateToStr(now));
    }

    public static String dateToStr(Date date) {
        String s = "";
        if (date == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            s = sdf.format(date);
        } catch (Exception e) {

        }

        return s;
    }

    public static Date strToDate(String s) {
        Date d = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d = sdf.parse(s);
        } catch (Exception e) {

        }

        return d;
    }

    /**
     * @return
     */
    public static Date getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static String getCurrentDateTimeToString() {
        return formatFromDate(FORMATER_YYYY_MM_DD_HH_MM_SS, Calendar.getInstance().getTime());
    }

    public static String getCurrentDateTimeToSimpleString() {
        return formatFromDate(FORMATER_YYYYMMDDHHMMSS, Calendar.getInstance().getTime());
    }

    public static String getCurrentDateToString() {
        return formatFromDate(FORMATER_YYYY_MM_DD, Calendar.getInstance().getTime());
    }

    /**
     * 按照给定的格式，格式化日期
     *
     * @param formater 需要的格式，常用的例如"yyyy-MM-dd HH:mm:ss"
     * @param date     日期
     * @return
     */
    public static String formatFromDate(String formater, Date date) {
        DateFormat df = new SimpleDateFormat(formater);
        return df.format(date);
    }


    /**
     * 按照给定的格式，格式化日期
     *
     * @param formater 需要的格式，常用的例如"yyyy-MM-dd HH:mm:ss"
     * @param s        可格式化为日期的字符串
     * @return
     */
    public static String formatFromString(String formater, String s) {
        DateFormat df = new SimpleDateFormat(formater);
        return df.format(s);
    }

    public static void main(String[] args) {
        System.out.println(formatFromString("yyyy-MM-dd", "2012-01-05"));
    }

    /**
     * 字符串转化为日期</br>
     *
     * @param str    需要被转换为日期的字符串
     * @param format 格式，常用的为 yyyy-MM-dd HH:mm:ss
     * @return java.util.Date，如果出错会返回null
     */
    public static Date StringToDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            LogUtil.error("将字符串转换成日期时出错", e);
        }
        return date;
    }

    /**
     * 字符串时间转solr时间格式
     *
     * @param str
     * @return
     */
    public static String StringToSolrDate(String str) {
        SimpleDateFormat sdfSolrDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = StringToDate(str, "yyyy-MM-dd HH:mm:ss");
        return sdfSolrDate.format(date);
    }

    /**
     * 计算两个日期之间的天数</br>
     * 任何一个参数传空都会返回-1</br>
     * 返回两个日期的时间差，不关心两个日期的先后</br>
     *
     * @param dateStart
     * @param dateEnd
     * @return
     */
    public static long getDaysBetweenTwoDate(Date dateStart, Date dateEnd) {
        if (null == dateStart || null == dateEnd) {
            return -1;
        }
        long l = Math.abs(dateStart.getTime() - dateEnd.getTime());
        l = l / (1000 * 60 * 60 * 24l);
        return l;
    }

    public static Date AddDays(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        ;
        calendar.setTime(date);
        calendar.add(calendar.DATE, count);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar.getTime();   //这个时间就是日期往后推一天的结果
    }

    public static Date AddMonth(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        ;
        calendar.setTime(date);
        calendar.add(calendar.MONTH, count);
        return calendar.getTime();
    }

    /**
     * 判断某字符串是否是日期类型
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取上周五时间
     */
    public static Date lastFirday() {
        //作用防止周日得到本周日期
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 7 - dayOfWeek;
        calendar.add(Calendar.DATE, offset - 9);

        return getFirstDayOfWeek(calendar.getTime(), 6);//这是从上周日开始数的到本周五为6
    }

    /**
     * 获取上周一时间
     */
    public static Date lastMonday() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 1 - dayOfWeek;
        calendar.add(Calendar.DATE, offset - 7);
        return getFirstDayOfWeek(calendar.getTime(), 2);
    }


    /**
     * 得到某一天的该星期的第一日 00:00:00
     *
     * @param date
     * @param firstDayOfWeek 一个星期的第一天为星期几
     * @return
     */
    public static Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        cal.setFirstDayOfWeek(firstDayOfWeek);//设置一星期的第一天是哪一天
        cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);//指示一个星期中的某天
        cal.set(Calendar.HOUR_OF_DAY, 0);//指示一天中的小时。HOUR_OF_DAY 用于 24 小时制时钟。例如，在 10:04:15.250 PM 这一时刻，HOUR_OF_DAY 为 22。
        cal.set(Calendar.MINUTE, 0);//指示一小时中的分钟。例如，在 10:04:15.250 PM 这一时刻，MINUTE 为 4。
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
