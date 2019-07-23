package com.comtrade.compare;

import java.util.Comparator;

import com.comtrade.domain.property.Property;

public class CompareRating implements Comparator<Property>{

	@Override
	public int compare(Property o1, Property o2) {
		return o1.getRating()-o2.getRating();
	}
	
}
