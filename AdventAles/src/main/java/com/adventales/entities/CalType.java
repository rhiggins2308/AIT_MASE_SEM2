package com.adventales.entities;

public enum CalType {

	LAGER("lager"),
	RED_ALE("redale"),
	PALE_ALE("paleale"),
	WEISSBIER("weissbier"),
	STOUT("stout"),
	POT_LUCK("potluck");
	
	private String type;
	
	private CalType(String type) {
		this.type = type;
	}
	
	public String getCalType() {
		return this.type;
	}
}
