package cn.ken.question.answering.system.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: TimeUtils <br/>
 */
public class TimeUtils {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final String START_HOUR = " 00:00:00";

    public static final String END_HOUR = " 24:00:00";

    public static final String EARLY_TIME = "1970-01-01";

    public static final String START_TIME = "startTime";

    public static final String TIME = "time";

    public static final String END_TIME = "endTime";

    public static final long DAY_TIME_LENGTH = 1000*60*60*24;

    /**
     * 时间条件处理
     * @param type
     * @param isHour
     * @return
     */
    public static Map timeCondition(int type,boolean isHour){
        Map map = new HashMap();
        String today = DATE_FORMAT.format(getDayTime(0));
        String yesterday = DATE_FORMAT.format(getDayTime(-1));
        String time = today;
        String startTime = today;
        String endTime = today;
        switch (type){
            case 1:
                startTime = EARLY_TIME;
                endTime = today;
                break;
            case 2:
                startTime = today;
                endTime = today;
                break;
            case 3:
                time = yesterday;
                startTime = yesterday;
                endTime = yesterday;
                break;
            case 4:
                startTime = DATE_FORMAT.format(getBeginDayOfWeek());
                endTime = today;
                break;
            case 5:
                startTime = DATE_FORMAT.format(getBeginDayOfMonth());
                endTime = today;
                break;
        }
        if(isHour){
            map.put(START_TIME,startTime+START_HOUR);
            map.put(END_TIME,endTime+END_HOUR);
        }else{
            map.put(START_TIME,startTime);
            map.put(END_TIME,endTime);
        }
        map.put(TIME,time);
        return map;
    }

    public static List<String> getTimeSlot(String startTime,String endTime){
        List<String> timeSlotList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        try {
            Date start = DATE_FORMAT.parse(startTime);
            Date end = DATE_FORMAT.parse(endTime);
            if(start.before(end)){
                if(end.getTime()-start.getTime()>= DAY_TIME_LENGTH){
                    calendar.setTime(start);
                    calendar.add(Calendar.DAY_OF_MONTH,1);
                    for(long i=start.getTime();i<end.getTime();i+= DAY_TIME_LENGTH){
                        timeSlotList.add(DATE_FORMAT.format(i));
                    }
                }else{
                    timeSlotList.add(startTime);
                    timeSlotList.add(endTime);
                }
            }else{
                timeSlotList.add(startTime);
            }
        } catch (Exception e) {
            return timeSlotList;
        }
        return timeSlotList;
    }

    //获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d){
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    //获取本月的开始时间
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    //获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    //获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    //获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取当前时间的前/后几天的时间
     */
    public static Date getDayTime(int num){
        Date dateNow = new Date();
        Calendar day = Calendar.getInstance();
        day.setTime(dateNow);
        day.add(Calendar.DAY_OF_MONTH, num);
        return day.getTime();
    }
}
