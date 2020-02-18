package com.gft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.gft.model.Evento;
import com.gft.repository.EventosInter;

@Controller
@RequestMapping("/carrinho")
public class SessaoCarrinhoController {

	@Autowired
	private EventosInter eventos;
	
	@RequestMapping(value = "carrinho/index")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView();
		List<Evento> listaEvento = eventos.findAll();
		mv.addObject("resultadoLista", listaEvento);
		return mv;
	}
}
