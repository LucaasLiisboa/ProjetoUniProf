package com.projeto.uniprof.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.uniprof.dao.CategoriaDAO;
import com.projeto.uniprof.model.Categoria;

@Controller
@Scope("session")
@SessionAttributes({"usuarioSessao"})
public class CategoriaController {
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	@RequestMapping(value = "/novaCategoria", method = RequestMethod.GET)
	public ModelAndView novaCategoria(ModelAndView model) {
		Categoria novaCategoria = new Categoria();
		model.addObject("categoria", novaCategoria);
		model.setViewName("administrador/cadastrarCategoria");
		
		return model;
	}
	
	@RequestMapping(value = "/cadastrarCategoria", method = RequestMethod.POST)
	public ModelAndView cadastrarCategoria(@ModelAttribute Categoria categoria, HttpServletRequest request) {
		categoriaDAO.cadastrarCategoria(categoria);
		
		return new ModelAndView("redirect:/consultarCategoria");
	}
	
	@RequestMapping(value="/consultarCategoria")
	public ModelAndView listarCategoria(ModelAndView model) throws IOException {
		List<Categoria> listaCategoria = categoriaDAO.listarCategoria();
		model.addObject("listaCategoria", listaCategoria);
		model.setViewName("administrador/consultarCategoria");
		
		return model;
	}
	
	@RequestMapping(value = "/editarCategoria", method = RequestMethod.GET)
	public ModelAndView editarCategoria(HttpServletRequest request) {
		int cdCategoria = Integer.parseInt(request.getParameter("cdCategoria"));
		
		Categoria categoria = categoriaDAO.getById(cdCategoria);
		
		ModelAndView model = new ModelAndView("administrador/editarCategoria");
		model.addObject("categoria", categoria);

		return model;
	}
	
	@RequestMapping(value = "/salvarCategoria", method = RequestMethod.POST)
	public ModelAndView salvarCategoria(@ModelAttribute Categoria categoria, HttpServletRequest request) {
		categoriaDAO.editarCategoria(categoria);
		
		return new ModelAndView("redirect:/consultarCategoria");
	}
		
	@RequestMapping(value = "/inativarCategoria", method = RequestMethod.GET)
	public ModelAndView inativarCategoria(HttpServletRequest request) {		
		int cdCategoria = Integer.parseInt(request.getParameter("cdCategoria"));
		
		Categoria categoria = categoriaDAO.getById(cdCategoria);
		
		categoriaDAO.inativarCategoria(categoria);
		
		ModelAndView model = new ModelAndView("redirect:/consultarCategoria");
		
		return model;
	}
	
	@RequestMapping(value = "/ativarCategoria", method = RequestMethod.GET)
	public ModelAndView ativarCategoria(HttpServletRequest request) {		
		int cdCategoria = Integer.parseInt(request.getParameter("cdCategoria"));
		
		Categoria categoria = categoriaDAO.getById(cdCategoria);
		
		categoriaDAO.ativarCategoria(categoria);
		
		ModelAndView model = new ModelAndView("redirect:/consultarCategoria");
		
		return model;
	}
}