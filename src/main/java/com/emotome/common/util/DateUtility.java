/**
 * 
 */
package com.emotome.common.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.emotome.common.location.model.TimeZoneModel;
import com.emotome.common.logger.LoggerService;
import com.emotome.common.setting.model.SettingModel;

/**
 * @author Dhruvang
 *
 */
public class DateUtility {

	public static String DD_MM_YYYY = "dd/MM/yyyy";
	public static String DD_MM_YYYY_HH_MM_A = "dd/MM/yyyy hh:mm a";
	public static String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

	private DateUtility() {
	}

	public static LocalDateTime getLocalDateTime(Long date) {
		if (SettingModel.getDefaultTimeZoneId() != null
				|| (TimeZoneModel.getMAP() != null && !TimeZoneModel.getMAP().isEmpty())) {
			if (date != null && date != 0l) {
				return LocalDateTime.ofInstant(Instant.ofEpochSecond(date), ZoneId.of(TimeZoneModel.getMAP()
						.get(Integer.valueOf(SettingModel.getDefaultTimeZoneId())).getTimezone()));
			}
		}
		return null;
	}

	public static LocalDateTime getLocalDateTimeWithDefaultTimeZone(Long date) {
		if (SettingModel.getDefaultTimeZoneId() != null
				|| (TimeZoneModel.getMAP() != null && !TimeZoneModel.getMAP().isEmpty())) {
			if (date != null && date != 0l) {
				return LocalDateTime.ofInstant(Instant.ofEpochSecond(date), ZoneId.of(TimeZoneModel.getMAP()
						.get(Integer.valueOf(SettingModel.getDefaultTimeZoneId())).getTimezone()));
			}
		}
		return null;
	}

	public static String formateLocalDateTime(LocalDateTime localDateTime, String format) {
		return localDateTime.format(DateTimeFormatter.ofPattern(format));
	}

	public static long getCurrentEpoch() {
		return Instant.now().getEpochSecond();
	}

	public static long getEpochAfterDays(LocalDateTime localDateTime, int day, Long hospitalId) {
		if (SettingModel.getDefaultTimeZoneId() != null
				|| (TimeZoneModel.getMAP() != null && !TimeZoneModel.getMAP().isEmpty())) {
			return localDateTime.plusDays(day).atZone(ZoneId
					.of(TimeZoneModel.getMAP().get(Integer.valueOf(SettingModel.getDefaultTimeZoneId())).getTimezone()))
					.toEpochSecond();
		}
		return 0;
	}

	public static LocalDate stringToDate(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return LocalDate.parse(date, formatter);
		} catch (Exception exception) {
			LoggerService.exception(exception);
			return null;
		}
	}

	public static LocalDate stringToDateTime(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			return LocalDate.parse(date, formatter);
		} catch (Exception exception) {
			LoggerService.exception(exception);
			return null;
		}
	}

	public static String dateToString(LocalDate localDate) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return localDate.format(formatter);
		} catch (Exception exception) {
			LoggerService.exception(exception);
			return null;
		}
	}

	public static long getEpochFromDateTimeString(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return dateTime
				.atZone(ZoneId.of(
						TimeZoneModel.getMAP().get(Integer.valueOf(SettingModel.getDefaultTimeZoneId())).getTimezone()))
				.toEpochSecond();
	}

	public static long getEpochFromDateString(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dateTime = LocalDate.parse(date, formatter);
		return dateTime
				.atStartOfDay(ZoneId.of(
						TimeZoneModel.getMAP().get(Integer.valueOf(SettingModel.getDefaultTimeZoneId())).getTimezone()))
				.toEpochSecond();
	}

	public static int getCurrentYear() {
		return LocalDateTime.now().getYear();
	}

	public static Long getStartAndEndDayEpoch(boolean endDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if (endDay) {
			return (calendar.getTimeInMillis() + (24 * 60 * 60 * 1000)) / 1000;
		} else {
			return calendar.getTimeInMillis() / 1000;
		}
	}

	public static Long getStartAndEndDayOfMonthEpoch(boolean endday) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if (endday) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			return calendar.getTimeInMillis() / 1000;
		} else {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			return calendar.getTimeInMillis() / 1000;
		}
	}

	public static String getEpochtoLocalDate(Long dateEpoch) {
		return formateLocalDateTime(
				LocalDateTime
						.ofInstant(Instant.ofEpochSecond(dateEpoch),
								ZoneId.of(TimeZoneModel.getMAP()
										.get(Integer.valueOf(SettingModel.getDefaultTimeZoneId())).getTimezone())),
				"dd/MM/yyyy HH:mm").toString();
	}

	public static String convert12to24time(String time) {
		try {
			DateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
			DateFormat parseFormat = new SimpleDateFormat("hh:mm a");
			Date date = parseFormat.parse(time);
			return displayFormat.format(date);
		} catch (Exception exception) {
			LoggerService.exception(exception);
			return null;
		}

	}

	public static String convert24to12time(String time) {
		try {
			DateFormat displayFormat = new SimpleDateFormat("hh:mm a");
			DateFormat parseFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = parseFormat.parse(time);
			return displayFormat.format(date);
		} catch (Exception exception) {
			LoggerService.exception(exception);
			return null;
		}

	}

	public static int getDayFromDate(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.parse(date, formatter);
			return localDate.getDayOfWeek().getValue();
		} catch (Exception exception) {
			LoggerService.exception(exception);
			return 0;
		}
	}

	public static Long getStartEndDayEpoch(String strDate, boolean startDayEpoch) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = sdf.parse(strDate);
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			if (startDayEpoch) {
				return calendar.getTimeInMillis() / 1000;
			} else {
				return ((calendar.getTimeInMillis() + (24 * 60 * 60 * 1000)) / 1000) - 1;
			}
		} catch (Exception exception) {
			LoggerService.exception(exception);
			return null;
		}
	}

	public static Long getStartDayEpoch(String strDate) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dateTime = LocalDate.parse(strDate, formatter);
			return dateTime.atStartOfDay(ZoneId
					.of(TimeZoneModel.getMAP().get(Integer.valueOf(SettingModel.getDefaultTimeZoneId())).getTimezone()))
					.toEpochSecond();
		} catch (Exception exception) {
			LoggerService.exception(exception);
			return null;
		}
	}

	public static String getTimeFromLocaldatetime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return localDateTime.format(formatter);
	}

	public static Time convertStringTimeToSqlTime(String time) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			return new Time(dateFormat.parse(time).getTime());
		} catch (Exception exception) {
			LoggerService.exception(exception);
			return null;
		}
	}

	public static Long getMinuteBetweenToTime(String startTime, String endTime) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			Date date1 = format.parse(startTime);
			Date date2 = format.parse(endTime);
			long difference = date2.getTime() - date1.getTime();
			return difference / 1000 / 60;
		} catch (Exception e) {
			LoggerService.exception(e);
			return 0l;
		}
	}

	public static long getEpochFromLocalDateTime(LocalDateTime localDateTime) {
		if (SettingModel.getDefaultTimeZoneId() != null
				|| (TimeZoneModel.getMAP() != null && !TimeZoneModel.getMAP().isEmpty())) {
			return localDateTime.atZone(ZoneId
					.of(TimeZoneModel.getMAP().get(Integer.valueOf(SettingModel.getDefaultTimeZoneId())).getTimezone()))
					.toEpochSecond();
		}
		return 0;
	}

	public static String getTimeFromEpoch(Long epoch) {
		return formateLocalDateTime(
				LocalDateTime
						.ofInstant(Instant.ofEpochSecond(epoch),
								ZoneId.of(TimeZoneModel.getMAP()
										.get(Integer.valueOf(SettingModel.getDefaultTimeZoneId())).getTimezone())),
				"hh:mm a").toString();
	}

	/**
	 * this method get Us time zone.
	 * 
	 * @return
	 */
	public static ZoneId getDefaultTimeZone() {
		return ZoneId
				.of(TimeZoneModel.getMAP().get(Integer.valueOf(SettingModel.getDefaultTimeZoneId())).getTimezone());
	}

	public static void main(String[] args) throws Exception {
	}
}