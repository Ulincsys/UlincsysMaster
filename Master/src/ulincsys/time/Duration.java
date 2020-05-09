package ulincsys.time;

import java.math.BigInteger;

public class Duration {
	private BigInteger length;
	private boolean isLocal;
	private long tickTime = 1000;
	
	/* Constructors ----------------------------------------------------*/
	
	public Duration() {
		this(0, false);
	}
	
	public Duration(long length) {
		this(length, false);
	}
	
	public Duration(BigInteger length) {
		this(length, false);
	}
	
	public Duration(boolean isLocal) {
		this(0, isLocal);
	}
	
	public Duration(long length, boolean isLocal) {
		this(BigInteger.valueOf(length), isLocal);
	}
	
	public Duration(BigInteger length, boolean isLocal) {
		this.length = length;
		this.isLocal = isLocal;
	}
	
	/* Methods ------------------------------------------------------------*/
	
	public java.time.Duration asJavaTime() {
		return java.time.Duration.ofMillis(length.longValue());
	}
	
	public void addMillis(long milliseconds) {
		length.add(BigInteger.valueOf(milliseconds));
	}
	
	public void addSecs(long seconds) {
		addMillis(seconds * 1000);
	}
	
	public void addMins(long minutes) {
		addSecs(minutes * 60);
	}
	
	public void addHours(long hours) {
		addMins(hours * 60);
	}
	
	public void addDays(int days) {
		addHours(days * 24);
	}
	
	public void addCalendarMonths(int months) {
		addDays(months * 30);
	}
	
	public void subMillis(long milliseconds) {
		addMillis(-1 * milliseconds);
	}
	
	public void subSecs(long seconds) {
		addSecs(-1 * seconds);
	}
	
	public void subMins(long minutes) {
		addMins(-1 * minutes);
	}
	
	public void subHours(long hours) {
		addHours(-1 * hours);
	}
	
	public void subDays(int days) {
		addDays(-1 * days);
	}
	
	public void subCalendarMonths(int months) {
		addCalendarMonths(-1 * months);
	}
	
	public void tick() {
		length.add(BigInteger.valueOf(tickTime));
	}
	
	public void add(Duration duration) {
		length.add(duration.getLength());
	}
	
	/* Getters and Setters ---------------------------------------------------------*/

	public boolean isLocal() {
		return isLocal;
	}

	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}
	
	public BigInteger getLength() {
		return length;
	}
	
	public BigInteger getAbsoluteLength() {
		return length.abs();
	}

	public void setLength(BigInteger length) {
		this.length = length;
	}

	public long getTickTime() {
		return tickTime;
	}

	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}
}














