package ulincsys.time;

import java.text.SimpleDateFormat;
import java.util.Date;

import static ulincsys.pythonics.Pythonics.*;

public class Time {
	private Duration duration;
	
	public Time() {
		duration = new Duration();
	}
	
	public static String current(String format) {
		return ((new SimpleDateFormat(format)).format(new Date(System.currentTimeMillis())));
	}
	
	public static String current() {
		return current("yyyy-MM-dd 'at' HH:mm:ss z");
	}
	
	public static String currentTime() {
		return current("HH:mm:ss a");
	}
	
	public static String currentDate() {
		return current("yyyy-MM-dd");
	}
	
	public static int currentDay() {
		return Int(current("dd"));
	}
	
	public static int currentMonth() {
		return Int(current("MM"));
	}
	
	public static int currentYear() {
		return Int(current("yyyy"));
	}
}
