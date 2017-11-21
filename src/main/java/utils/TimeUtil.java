///*
// * To change this templatemethod, choose Tools | Templates
// * and open the templatemethod in the editor.
// */
//package utils;
//
//import java.sql.Timestamp;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.TimeZone;
//import org.apache.log4j.Logger;
//
///**
// * A class for time convertion
// *
// * @author lizhinian
// */
//public class TimeUtil {
//
//    public static final Logger log = Logger.getLogger(TimeUtil.class.getSimpleName());
//    // dd/MM/yyyy HH:mm:ss
//    public static final String TIME_FORMATE_1 = "dd/MM/yyyy HH:mm:ss";
//    // ddMMyyyyHHmmss
//    public static final String TIME_FORMATE_ddMMyyyyHHmmss = "ddMMyyyyHHmmss";
//    // yyyyMMddHHmmss
//    public static final String TIME_FORMATE_yyyyMMddHHmmss = "yyyyMMddHHmmss";
//    // yyyy-MM-dd HH:mm:ss.SSS
//    public static final String TIME_FORMATE_yyyyMMddHHmmssSSS = "yyyy-MM-dd HH:mm:ss.SSS";
//
//    /**
//     * Parse UNIX time to UNIX Millis Time.
//     * If an error occurred {@code 0L} is returned.
//     *
//     * @param time UTC time
//     * @param utcTimeFormat
//     * @return
//     */
//    public static long getTimeFromUTCtime(String time, String utcTimeFormat) {
//	long timeInMillis = 0L;
//	Calendar dCalendar = Calendar.getInstance();
//	try {
//	    SimpleDateFormat dateFormater = new SimpleDateFormat(utcTimeFormat);
//	    dateFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
//	    Date date = dateFormater.parse(time);
//	    dCalendar.setTime(date);
//	    timeInMillis = dCalendar.getTimeInMillis();
//	} catch (Exception e) {
//	    log.error("exception", e);
//	}
//	return timeInMillis;
//    }
//
//    /**
//     * Parse UNIX time to String format(The parsed time is still UTC time).
//     * If an error occurred {@code null} is returned.
//     *
//     * @param timeStamp
//     * @param utcTimeFormat
//     * @return
//     */
//    public static String getUTCtime(Long timeStamp, String utcTimeFormat) {
//	String time = null;
//	try {
//	    if (null == timeStamp || 0 == timeStamp) {
//		log.error("time is illegal.");
//		return time;
//	    }
//	    Date date = new Date(timeStamp);
//	    SimpleDateFormat sdf = new SimpleDateFormat(utcTimeFormat);
//	    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//	    time = sdf.format(date);
//	} catch (Exception e) {
//	    log.error("", e);
//	}
//	return time;
//    }
//
//    /**
//     * Parse local time to UTC time.
//     * If an error occurred {@code null} is returned.
//     *
//     * @param localTime
//     * @param localFormat such as "ddMMyyyyHHmmss"
//     * @param utcFormat such as "ddMMyyyyHHmmss"
//     * @return
//     */
//    public static String getUTCtimeFromLocal(String localTime, String localFormat, String utcFormat) {
//	String result = null;
//	if (null == localTime || localTime.isEmpty()) {
//	    log.warn("time is illegal, localTime=" + localTime);
//	    return result;
//	}
//	try {
//	    SimpleDateFormat InFormater = new SimpleDateFormat(localFormat);
//	    InFormater.setTimeZone(Calendar.getInstance().getTimeZone());
//	    //Get localtime
//	    Date date = InFormater.parse(localTime);
//	    //Parse localtime to UTC
//	    SimpleDateFormat outFormater = new SimpleDateFormat(utcFormat);
//	    outFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
//	    result = outFormater.format(date);
//	} catch (Exception ex) {
//	    log.error("parse exception", ex);
//	}
//	return result;
//    }
//
//    /**
//     * Parse UTC time to local time.
//     * If an error occurred {@code null} is returned.
//     *
//     * @param utcTime
//     * @param utcTimeFormat
//     * @param localTimeFormat
//     * @return
//     */
//    public static String getLocalTimeFromUTC(String utcTime, String utcTimeFormat, String localTimeFormat) {
//	String result = null;
//	try {
//	    SimpleDateFormat inFormater = new SimpleDateFormat(utcTimeFormat);
//	    inFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
//	    //Get UTC date
//	    Date date = inFormater.parse(utcTime);
//	    //Set to local timezone
//	    SimpleDateFormat outFormater = new SimpleDateFormat(localTimeFormat);
//	    outFormater.setTimeZone(Calendar.getInstance().getTimeZone());
//	    result = outFormater.format(date);
//	} catch (Exception ex) {
//	    log.error("parse exception", ex);
//	}
//	return result;
//    }
//
//    /**
//     * Parse SQL Timestamp to local time.
//     * If an error occurred {@code null} is returned.
//     *
//     * @param timestamp
//     * @param localTimeFormat
//     * @return String formatted local time
//     */
//    public static String getLocalTimeFromTimeStamp(Timestamp timestamp, String localTimeFormat) {
//	try {
//	    if (null == timestamp) {
//		return null;
//	    }
//	    return new SimpleDateFormat(localTimeFormat).format(timestamp);
//	} catch (Exception e) {
//	    log.error("", e);
//	    return null;
//	}
//    }
//
//    /**
//     * Get time from Timestamp.
//     * If an error occurred {@code 0L} is returned.
//     *
//     * @param timestamp
//     * @return
//     */
//    public static long getTimeFromTimestamp(Timestamp timestamp) {
//	if (null == timestamp) {
//	    return 0L;
//	}
//	return timestamp.getTime();
//    }
//
//    /**
//     * Parse local time to SQL Timestamp.
//     * If an error occurred {@code null} is returned.
//     *
//     * @param localTime
//     * @param localTimeFormat
//     * @return SQL Timestamp, such as "2015-03-26 14:58:37.152"
//     */
//    public static Timestamp getTimeStampFromLocalTime(String localTime, String localTimeFormat) {
//	Timestamp timestamp = null;
//	try {
//	    long startTime = getTimeFromLocalTime(localTime, localTimeFormat);
//	    log.debug("startTime=" + startTime);
//	    // new Timestamp(0) will be "1970-01-01 08:00:00.0"
//	    if (0 == startTime) {
//		return timestamp;
//	    }
//	    timestamp = new Timestamp(startTime);
//	} catch (Exception e) {
//	    log.error("", e);
//	}
//	return timestamp;
//    }
//
//    /**
//     * Parse UNIX time to SQL Timestamp.
//     * If an error occurred {@code null} is returned.
//     *
//     * @param time
//     * @return
//     */
//    public static Timestamp getTimestamp(Long time) {
//	Timestamp timestamp = null;
//	try {
//	    if (null == time || 0 == time) {
//		return timestamp;
//	    }
//	    return new Timestamp(time);
//	} catch (Exception e) {
//	    log.error("", e);
//	}
//	return timestamp;
//    }
//
//    /**
//     * Get UNIX time from local time.
//     * If an error occurred {@code 0L} is returned.
//     *
//     * @param localTime
//     * @param localTimeFormat
//     * @return
//     */
//    public static long getTimeFromLocalTime(String localTime, String localTimeFormat) {
//	long timeInMillis = 0L;
//	if (null == localTime) {
//	    log.warn("time is illegal.");
//	    return timeInMillis;
//	}
//
//	try {
//	    SimpleDateFormat dateFormater = new SimpleDateFormat(localTimeFormat);
//	    dateFormater.setTimeZone(Calendar.getInstance().getTimeZone());
//	    log.error("time zone=" + Calendar.getInstance().getTimeZone());
//	    //Get localtime
//	    Date date = dateFormater.parse(localTime);
//	    timeInMillis = date.getTime();
//	} catch (ParseException e) {
//	    log.error("exception", e);
//	}
//	return timeInMillis;
//    }
//
//    /**
//     * Parse UTC time to local time.
//     * If an error occurred {@code null} is returned.
//     *
//     * @param unixTime accurate to millisecond
//     * @param localTimeFormat
//     * @return local time
//     */
//    public static String getLocalTime(Long unixTime, String localTimeFormat) {
//	String result = null;
//	try {
//	    if (null == unixTime || 0 == unixTime) {
//		log.error("time is illegal.");
//		return result;
//	    }
//	    Date date = new Date(unixTime);
//	    //The above date is UTC time
//	    SimpleDateFormat formater = new SimpleDateFormat(localTimeFormat);
//	    formater.setTimeZone(Calendar.getInstance().getTimeZone());
//	    result = formater.format(date);
//	} catch (Exception ex) {
//	    log.error("parse exception", ex);
//	}
//	return result;
//    }
//
//    /**
//     * Parse local time's time format.
//     * If an error occurred {@code null} is returned.
//     *
//     * @param localTime
//     * @param inputFormat
//     * @param outputFormat
//     * @return
//     */
//    public static String parseLocalTime(String localTime, String inputFormat, String outputFormat) {
//	String result = null;
//	try {
//	    SimpleDateFormat inFormater = new SimpleDateFormat(inputFormat);
//	    inFormater.setTimeZone(Calendar.getInstance().getTimeZone());
//	    Date date = inFormater.parse(localTime);
//
//	    SimpleDateFormat outFormater = new SimpleDateFormat(outputFormat);
//	    outFormater.setTimeZone(Calendar.getInstance().getTimeZone());
//	    result = outFormater.format(date);
//	} catch (Exception ex) {
//	    log.error("parse exception", ex);
//	}
//	return result;
//    }
//}
