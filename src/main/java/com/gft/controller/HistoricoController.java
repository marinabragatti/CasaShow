package com.gft.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.model.Carrinho;
import com.gft.model.Compra;
import com.gft.model.Usuario;
import com.gft.repository.UsuarioInter;

@Controller
@RequestMapping("/historico")
public class HistoricoController {
	
	@Autowired
	private UsuarioInter usuarioInter;

	private List<Compra> compraFinal= new ArrayList<Compra>();
	
	private Compra compra = new Compra();
	
	private Usuario usuario;
	
	@RequestMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("Historico");
		mv.addObject("compra", compra);
		mv.addObject("listaCompra", compraFinal);
		return mv;
	}
	
}
