package com.christiandias.cursomc.domain.enums;

public enum Perfil {
    
	ADMIN(1 , "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int value;
	private String descricao;
	
	private Perfil(int value, String descricao) {
		this.value = value;
		this.descricao = descricao;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (Perfil x : Perfil.values()) {
			if(cod.equals(x.getValue())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}