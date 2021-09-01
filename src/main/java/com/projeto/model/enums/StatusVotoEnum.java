package com.projeto.model.enums;


public enum StatusVotoEnum {
	ABLE_TO_VOTE("ABLE_TO_VOTE"),
	ENABLE_TO_VOTE("ENABLE_TO_VOTE");
	
	private String status;
	
	StatusVotoEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static StatusVotoEnum toEnum(Object value) {
		StatusVotoEnum ret = null;
		
		for( StatusVotoEnum enumObj: StatusVotoEnum.values()) {
			if(value.equals(enumObj.status)) {
				ret =  enumObj;
			}
		}
		
		return ret;
	}
	
}	