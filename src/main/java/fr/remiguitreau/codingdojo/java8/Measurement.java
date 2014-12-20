package fr.remiguitreau.codingdojo.java8;

import java.util.Date;

public class Measurement {
	private final Date timestamp;
	
	private final float value;
	
	private final Quality qualityOrNull;

	public Measurement(Date date, float f) {
		this(date, f, null);
	}
	public Measurement(Date timestamp, float value, Quality qualityOrNull) {
		super();
		this.timestamp = timestamp;
		this.value = value;
		this.qualityOrNull = qualityOrNull;
	}


	public Date getTimestamp() {
		return timestamp;
	}

	public float getValue() {
		return value;
	}

	public Quality getQualityOrNull() {
		return qualityOrNull;
	}
	
	
}
