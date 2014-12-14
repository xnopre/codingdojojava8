package fr.remiguitreau.codingdojo.java8;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Measurement {
	private final Date timestamp;
	
	private final float value;
	
	private Quality qualityOrNull;
}
