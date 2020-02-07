package com.gft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.model.Casa;

import com.gft.repository.CasasShowsInter;

@Controller
@RequestMapping("/casashow")
public class CasaShowController {
	
	private static final String CASAVIEW = "CadastroCasaShow";
	
	@Autowired
	private CasasShowsInter casas;
	
	@RequestMapping
	public ModelAndView view() {
		List<Casa> todasCasas = casas.findAll();
		ModelAndView mv = new ModelAndView(CASAVIEW);
		mv.addObject("casas", todasCasas);
		mv.addObject(new Casa());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Casa casa, Errors errors) {
		List<Casa> todasCasas = casas.findAll();
		ModelAndView mv = new ModelAndView(CASAVIEW);
		mv.addObject("casas", todasCasas);
		mv.addObject(new Casa());
		if(errors.hasErrors()) {
			mv.addObject("mensagem", "Todos os campos devem ser preenchidos!");
			return mv;
		}
		casas.save(casa);
		return view();
	}
	
	@RequestMapping(value= "{codigo}",method = {RequestMethod.PUT, RequestMethod.GET})
	public @ResponseBody ModelAndView edicao(@PathVariable Long codigo, RedirectAttributes attributes) {
		Casa casa = casas.findById(codigo).get(); 
		ModelAndView mv = new ModelAndView("redirect:/casashow");
		attributes.addFlashAttribute("casarecuperada", casa);
		return mv;
	}
	
}

