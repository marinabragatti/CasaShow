package com.gft.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.model.Carrinho;
import com.gft.model.Evento;
import com.gft.repository.EventosInter;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	private List<Carrinho> carrinhoCompra = new ArrayList<Carrinho>();
	
	@Autowired
	private EventosInter eventos;
	
	@RequestMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("Carrinho");
		mv.addObject("listaCarrinho", carrinhoCompra);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView addCarrinho(@PathVariable Long codigo) {
		
		ModelAndView mv = new ModelAndView("Carrinho");
		
		Optional<Evento> evento = eventos.findById(codigo);
		Evento event = evento.get();
		Carrinho carrinho = new Carrinho();
		
		carrinho.setEvento(event);
		carrinho.setQuantidade(carrinho.getQuantidade() + 1);
		carrinho.setValorTotal(carrinho.getValorUnitario()*carrinho.getQuantidade());
		carrinho.setIngressoDisp(event.getCapacidade()-carrinho.getQuantidade());
		carrinhoCompra.add(carrinho);
		
		mv.addObject("listaCarrinho", carrinhoCompra);
		return mv;
	}
}
