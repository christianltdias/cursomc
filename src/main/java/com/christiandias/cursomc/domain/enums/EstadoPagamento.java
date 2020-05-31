package com.christiandias.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1 , "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int value;
	private String descricao;
	
	private EstadoPagamento(int value, String descricao) {
		this.value = value;
		this.descricao = descricao;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getValue())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
