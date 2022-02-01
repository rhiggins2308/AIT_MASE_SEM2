package com.library.app.category.resource.error;

public enum ErrorMessages {
	INVALID_FIELD("Invalid field"),
	CATEGORY_EXISTS("Category already exists"),
	CATEGORY_NOT_FOUND("Category does not exist");
	
	private String errorMessage;
	
	ErrorMessages(String errMsg){
		this.errorMessage=errMsg;
	}
	
	public String getMsg(){
		return errorMessage;
	}
}
