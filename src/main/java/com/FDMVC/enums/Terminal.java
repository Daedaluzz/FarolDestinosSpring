package com.FDMVC.enums;

public enum Terminal {
	T1("Azul", "Terminal 1"),
	T2("Laranja", "Terminal 2"),
	T3("Amarelo", "Terminal 3"),
	T4("Verde", "Terminal 4"),
	T5("Magenta", "Terminal 5");
	
	private String cor;
	private String descricao;
	
	Terminal(String cor, String descricao) {
        this.cor = cor;
        this.descricao = descricao;
    }

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	
}
