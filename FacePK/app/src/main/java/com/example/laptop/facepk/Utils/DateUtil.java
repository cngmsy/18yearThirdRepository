package com.example.laptop.facepk.Utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by laptop on 2018/1/24.
 */

public class DateUtil {

	public static Date getDateFromLong(long time){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar.getTime();
	}
}
