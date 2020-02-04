package com.gft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class CasaShowController {
	
	@RequestMapping("/home")
	public String novo() {
		return "CadastroEvento";
	}
	

}
