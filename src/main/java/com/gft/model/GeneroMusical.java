package com.gft.model;

public enum GeneroMusical {
	
	POP("Pop"),
	MPB("MPB"),
	AXE("Axé"),
	SERTANEJO("Sertanejo"),
	ROCK("Rock"),
	FUNK("Funk");
	
	private String genero;
	
	GeneroMusical(String genero){
		this.genero = genero;
	}
	
	public String getGenero() {
		return genero;
	}
}
