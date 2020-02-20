package com.gft.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.model.Carrinho;
import com.gft.model.Evento;
import com.gft.repository.CarrinhoCompraInter;
import com.gft.repository.EventosInter;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	private static final String CARRINHOVIEW = "Carrinho";

	private List<Carrinho> carrinhoCompra = new ArrayList<Carrinho>();

	@Autowired
	private EventosInter eventos;
	
	@RequestMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView(CARRINHOVIEW);
		mv.addObject("listaCarrinho", carrinhoCompra);
		return mv;
	}
	
	
	@RequestMapping("{codigo}")
	public ModelAndView addCarrinho(@PathVariable Long codigo) {

		ModelAndView mv = new ModelAndView(CARRINHOVIEW);

		Optional<Evento> evento = eventos.findById(codigo);
		Evento event = evento.get();
		Carrinho carrinho = new Carrinho();

		int controller = 0;

		for (Carrinho it : carrinhoCompra) {
			if (it.getEvento().getCodigo().equals(event.getCodigo())) {
				it.setQuantidade(it.getQuantidade() + 1);
				it.setIngressoDisp(it.getIngressoDisp() - 1);
				it.setIngressoDisp(event.getCapacidade() - it.getQuantidade());
				controller = 1;
				break;
			}
		}

		if (controller == 0) {
			carrinho.setEvento(event);
			carrinho.setValorUnitario(event.getValorIngresso());
			carrinho.setQuantidade(carrinho.getQuantidade() + 1);
			carrinho.setIngressoDisp(event.getCapacidade() - carrinho.getQuantidade());
			carrinhoCompra.add(carrinho);
		}


		mv.addObject("listaCarrinho", carrinhoCompra);
		return mv;
		//carrinho.setValorTotal(BigDecimal.valueOf(carrinho.getQuantidade()).multiply(carrinho.getValorUnitario()));
	}
	
	
	@RequestMapping("/altera/{codigo}/{acao}")
	public String altera(@PathVariable Long codigo, @PathVariable int acao, RedirectAttributes attributes) {
		
		Optional<Evento> evento = eventos.findById(codigo);
		Evento event = evento.get();
		
		
		for (Carrinho it : carrinhoCompra) {
			if (it.getEvento().getCodigo().equals(codigo)) {
				if(acao == 1) {
					it.setQuantidade(it.getQuantidade()+1);
					it.setIngressoDisp(event.getCapacidade() - it.getQuantidade());
				}	
				else if(acao == 0) {
					it.setQuantidade(it.getQuantidade()-1);
					it.setIngressoDisp(event.getCapacidade() + it.getQuantidade());
				}
			}
			break;
		}
		attributes.addAttribute("listaCarrinho", carrinhoCompra);
		return "redirect:/carrinho";
	}

	@RequestMapping(value="/excluir/{codigo}")
	public String excluir(@PathVariable Long codigo) {
		
		for (Carrinho it : carrinhoCompra) {
			if (it.getEvento().getCodigo().equals(codigo)) {
				carrinhoCompra.remove(it);
				break;
			}
		}
		
		return "redirect:/carrinho";
	}
}
