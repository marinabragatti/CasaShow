package com.gft.model;

public enum FormaPagto {
	
	CARTAOMASTER("Crédito Master"),
	CARTAOVISA("Crédito Visa"),
	CARTAODEBITO("Débito"),
	PAYPAL("PayPal");
	
	private String formaPagto;

	FormaPagto(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getFormaPagto() {
		return formaPagto;
	}

	public void setFormaPagto(String formaPagto) {
		this.formaPagto = formaPagto;
	}

}
