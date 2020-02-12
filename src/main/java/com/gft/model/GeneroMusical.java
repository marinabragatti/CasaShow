package com.gft.model;

public enum GeneroMusical {
	
	POP("Pop"),
	MPB("MPB"),
	AXE("Axé"),
	SERTANEJO("Sertanejo"),
	ROCK("Rock"),
	FUNK("Funk");
	
	private String generos;
	
	GeneroMusical(String generos){
		this.generos = generos;
	}
	
	public String getGeneros() {
		return generos;
	}
}
