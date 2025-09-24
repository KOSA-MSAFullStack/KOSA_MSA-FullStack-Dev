package com.bharath.springai.text.prompttemplate;

import java.util.List;

public class CountryCuisines {

	private String country;
	private List<String> cuisines;

	public CountryCuisines(String country, List<String> cuisines) {
		this.country = country;
		this.cuisines = cuisines;
	}

	public String getCountry() {
		return country;
	}

	public List<String> getCuisines() {
		return cuisines;
	}

	@Override
	public String toString() {
		return "CountryCuisines{" + "country='" + country + '\'' + ", cuisines=" + cuisines + '}';
	}
}
