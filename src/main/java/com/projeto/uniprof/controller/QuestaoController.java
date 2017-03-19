package com.projeto.uniprof.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.uniprof.dao.CategoriaDAO;
import com.projeto.uniprof.dao.QuestaoDAO;
import com.projeto.uniprof.dao.UsuarioDAO;
import com.projeto.uniprof.model.Categoria;
import com.projeto.uniprof.model.Questao;
import com.projeto.uniprof.model.Usuario;

@Controller
@Scope("session")
@SessionAttributes({"usuarioSessao"})
public class QuestaoController {
	@Autowired
	private QuestaoDAO questaoDAO;
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
		
	@RequestMapping(value = "/novaQuestao", method = RequestMethod.GET)
	public ModelAndView novaQuestao(ModelAndView model) {
		Questao novaQuestao = new Questao();
		model.addObject("questao", novaQuestao);
		model.setViewName("professor/cadastrarQuestao");
			
		List<Categoria> listaCategoria = categoriaDAO.listarCategoriaAtiva();
		model.addObject("listaCategoria", listaCategoria);
		
		return model;
	}
	
	@RequestMapping(value = "/cadastrarQuestao", method = RequestMethod.POST)
	public ModelAndView cadastrarQuestao(Questao questao, HttpServletRequest request, HttpSession session) {		
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");		
		
		questaoDAO.cadastrarQuestao(questao, usuario.getCdUsuario());						
				
		return new ModelAndView("redirect:/consultarMinhasQuestoes");
	}
	
	@RequestMapping(value="/consultarQuestao")
	public ModelAndView listarQuestoes(ModelAndView model) throws IOException {
		List<Questao> listaQuestoes = questaoDAO.listarQuestao();		
		model.addObject("listaQuestoes", listaQuestoes);
				
		model.setViewName("professor/consultarQuestao");
		
		return model;
	}
	
	@RequestMapping(value = "/editarQuestao", method = RequestMethod.GET)
	public ModelAndView editarQuestao(HttpServletRequest request) {
		int codigoQuestao = Integer.parseInt(request.getParameter("cdQuestao"));
		Questao questao = questaoDAO.getById(codigoQuestao);
						
		ModelAndView model = new ModelAndView("professor/editarQuestao");
		
		List<Categoria> listaCategoria = categoriaDAO.listarCategoriaAtiva();
		model.addObject("listaCategoria", listaCategoria);
		
		model.addObject("questao", questao);
		
		return model;
	}
	
	@RequestMapping(value = "/salvarQuestao", method = RequestMethod.POST)
	public ModelAndView salvarQuestao(@ModelAttribute Questao questao, HttpServletRequest request) {
		questaoDAO.editarQuestao(questao);
		
		return new ModelAndView("redirect:/consultarMinhasQuestoes");
	}
	
	@RequestMapping(value = "/detalharQuestoes", method = RequestMethod.GET)
	public ModelAndView detalharQuestoes(HttpServletRequest request) {
		int codigoQuestao = Integer.parseInt(request.getParameter("cdQuestao"));
		Questao questao = questaoDAO.getById(codigoQuestao);
						
		ModelAndView model = new ModelAndView("professor/detalharQuestao");
		
		List<Categoria> listaCategoria = categoriaDAO.listarCategoria();
		model.addObject("listaCategoria", listaCategoria);
		
		model.addObject("questao", questao);
		
		return model;
	}
	
	@RequestMapping(value = "/inativarQuestao", method = RequestMethod.GET)
	public ModelAndView inativarQuestao(HttpServletRequest request) {		
		int cdQuestao = Integer.parseInt(request.getParameter("cdQuestao"));
		
		Questao questao = questaoDAO.getById(cdQuestao);
		
		questaoDAO.inativarQuestao(questao);
		
		ModelAndView model = new ModelAndView("redirect:/consultarMinhasQuestoes");
		
		return model;
	}
	
	
	@RequestMapping(value = "/ativarQuestao", method = RequestMethod.GET)
	public ModelAndView ativarQuestao(HttpServletRequest request) {		
		int cdQuestao = Integer.parseInt(request.getParameter("cdQuestao"));
		
		Questao questao = questaoDAO.getById(cdQuestao);
		
		questaoDAO.ativarQuestao(questao);
		
		ModelAndView model = new ModelAndView("redirect:/consultarMinhasQuestoes");
		
		return model;
	}
	
	@RequestMapping(value="/consultarMinhasQuestoes")
	public ModelAndView consultarMinhasQuestoes(ModelAndView model, HttpServletRequest request, HttpSession session) throws IOException {
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");		
		usuario = usuarioDAO.getById(usuario.getCdUsuario());
		
		List<Questao> listaQuestoes = questaoDAO.listarMinhasQuestoes(usuario.getCdUsuario());
		model.addObject("listaQuestoes", listaQuestoes);
				
		model.setViewName("professor/consultarMinhasQuestoes");
		
		return model;
	}
}