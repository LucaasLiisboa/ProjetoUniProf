package com.projeto.uniprof.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.uniprof.model.Usuario;

@Controller
public class HomeController {
	@RequestMapping(value="/")
	public ModelAndView callIndex(ModelAndView model) throws IOException {
		Usuario usuario = new Usuario();
		model.addObject("usuario", usuario);		
		model.setViewName("index");		
		
		return model;
	}
	
	@RequestMapping(value="/principal")
	public ModelAndView callPrincipal(ModelAndView model) throws IOException {
		model.setViewName("principal");
		
		return model;
	}
	
	@RequestMapping(value="/sair")
	public ModelAndView callLogOut(ModelAndView model, HttpSession session) throws IOException {
		session.removeAttribute("usuarioLogado");		
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/voltar")
	public ModelAndView callVoltar(ModelAndView model) throws IOException {
		return new ModelAndView("redirect:/");
	}
}