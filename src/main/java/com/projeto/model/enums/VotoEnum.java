package com.projeto.model.enums;

public enum VotoEnum {
	BRANCO("BRANCO"),
	SIM("SIM"),
	NAO("NAO");
	
	private String descricao;
	
	VotoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static VotoEnum toEnum(Object value) {
		VotoEnum ret = null;
		for( VotoEnum enumObj: VotoEnum.values()) {
			if(value.equals(enumObj.descricao)) {
				ret =  enumObj;
			}
		}
		
		return ret;
	}


}