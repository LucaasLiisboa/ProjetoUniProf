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
import com.projeto.uniprof.dao.QuestionarioDAO;
import com.projeto.uniprof.dao.UsuarioDAO;
import com.projeto.uniprof.model.Categoria;
import com.projeto.uniprof.model.Questao;
import com.projeto.uniprof.model.Questionario;
import com.projeto.uniprof.model.Usuario;

@Controller
@Scope("session")
@SessionAttributes({"usuarioSessao"})
public class QuestionarioController {
	@Autowired
	private QuestionarioDAO questionarioDAO;
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	@Autowired
	private QuestaoDAO questaoDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
		
	@RequestMapping(value = "/novoQuestionario", method = RequestMethod.GET)
	public ModelAndView novoQuestionario(ModelAndView model) {
		Questionario novoQuestionario = new Questionario();
		model.addObject("questionario", novoQuestionario);		
		model.setViewName("professor/cadastrarQuestionario");
		
		List<Categoria> listaCategoria = categoriaDAO.listarCategoriaAtiva();
		model.addObject("listaCategoria", listaCategoria);

		return model;
	}
	
	@RequestMapping(value = "/cadastrarQuestionario", method = RequestMethod.POST)
	public ModelAndView cadastrarQuestionario(@ModelAttribute Questionario questionario, HttpServletRequest request, HttpSession session) {		
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");		
		questionarioDAO.cadastrarQuestionario(questionario, usuario.getCdUsuario());
				
		List<Questionario> listaQuestionarios = questionarioDAO.listarQuestionario();		
		
		questionario = listaQuestionarios.get(listaQuestionarios.size() - 1);
		
		session.setAttribute("questionario", questionario);
		
		return new ModelAndView("redirect:/adicionarQuestao");
	}
	
	@RequestMapping(value="/consultarQuestionario")
	public ModelAndView listarQuestionarios(ModelAndView model) throws IOException {
		List<Questionario> listaQuestionarios = questionarioDAO.listarQuestionario();		
		model.addObject("listaQuestionarios", listaQuestionarios);
				
		model.setViewName("professor/consultarQuestionario");
		
		return model;
	}
	
	@RequestMapping(value = "/detalharQuestionario", method = RequestMethod.GET)
	public ModelAndView detalharQuestionario(HttpServletRequest request, HttpSession session) {
		int cdQuestionario = Integer.parseInt(request.getParameter("cdQuestionario"));
		
		Questionario questionario = questionarioDAO.getById(cdQuestionario);
		
		session.setAttribute("questionario", questionario);
				
		List<Questao> listaQuestoes = questaoDAO.listarQuestoesQuestionario(questionario.getCdQuestionario());
		
		ModelAndView model = new ModelAndView("professor/detalharQuestionario");
		
		List<Categoria> listaCategoria = categoriaDAO.listarCategoria();
		model.addObject("listaCategoria", listaCategoria);
		
		model.addObject("questionario", questionario);
		
		model.addObject("listaQuestoes", listaQuestoes);
		
		return model;
	}
	
	@RequestMapping(value = "/editarQuestionario", method = RequestMethod.GET)
	public ModelAndView editarQuestionario(HttpServletRequest request, HttpSession session) {
		int cdQuestionario = Integer.parseInt(request.getParameter("cdQuestionario"));
		Questionario questionario = questionarioDAO.getById(cdQuestionario);
						
		ModelAndView model = new ModelAndView("professor/editarQuestionario");
		
		List<Categoria> listaCategoria = categoriaDAO.listarCategoriaAtiva();
		model.addObject("listaCategoria", listaCategoria);
				
		session.setAttribute("questionario", questionario);
		
		model.addObject("questionario", questionario);
				
		return model;
	}
	
	@RequestMapping(value = "/salvarQuestionario", method = RequestMethod.POST)
	public ModelAndView salvarQuestionario(@ModelAttribute Questionario questionario, HttpServletRequest request) {
		questionarioDAO.editarQuestionario(questionario);
		
		return new ModelAndView("redirect:/adicionarQuestao");
	}	
	
	@RequestMapping(value = "/detalharQuestao", method = RequestMethod.GET)
	public ModelAndView detalharQuestao(HttpServletRequest request) {
		int codigoQuestao = Integer.parseInt(request.getParameter("cdQuestao"));
		Questao questao = questaoDAO.getById(codigoQuestao);
						
		ModelAndView model = new ModelAndView("professor/detalharQuestao");
		
		List<Categoria> listaCategoria = categoriaDAO.listarCategoria();
		model.addObject("listaCategoria", listaCategoria);
		
		model.addObject("questao", questao);
		
		return model;
	}
	
	@RequestMapping(value="/adicionarQuestao")
	public ModelAndView listarQuestoes(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Questionario questionarioAtual = (Questionario)session.getAttribute("questionario");
		
		List<Questao> listaQuestoes = questaoDAO.listarQuestoesForaQuestionario(questionarioAtual.getCdQuestionario());
		
		model.addObject("listaQuestoes", listaQuestoes);		
		model.addObject("questionarioAtual", questionarioAtual);
				
		model.setViewName("professor/adicionarQuestao");
		
		return model;
	}
		
	@RequestMapping(value = "/addQuestao")
	public ModelAndView addQuestao(HttpServletRequest request, HttpSession session) {
		Questionario questionarioAtual = (Questionario)session.getAttribute("questionario");
		int cdQuestaoView = Integer.parseInt(request.getParameter("cdQuestao"));		
		int cdQuetionario = questionarioAtual.getCdQuestionario();
				
		Questao questao = questaoDAO.getById(cdQuestaoView);		
		questionarioAtual = questionarioDAO.getById(cdQuetionario);
		
		questionarioDAO.addQuestao(questionarioAtual, questao);
		
		return new ModelAndView("redirect:/adicionarQuestao");
	}
	
	@RequestMapping(value="/deletaQuestao")
	public ModelAndView listarQuestoesRemover(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Questionario questionario = (Questionario)session.getAttribute("questionario");
		
		List<Questao> listaQuestoes = questaoDAO.listarQuestoesQuestionario(questionario.getCdQuestionario());
				
		model.addObject("listaQuestoes", listaQuestoes);
		
		model.addObject("questionario", questionario);
				
		model.setViewName("professor/removerQuestao");
		
		return model;
	}
	
	@RequestMapping(value = "/removeQuestao")
	public ModelAndView deleteQuestao(HttpServletRequest request, HttpSession session) {
		Questionario questionario = (Questionario)session.getAttribute("questionario");
		int cdQuestaoView = Integer.parseInt(request.getParameter("cdQuestao"));
		int cdQuetionario = questionario.getCdQuestionario();
				
		Questao questao = questaoDAO.getById(cdQuestaoView);		
		questionario = questionarioDAO.getById(cdQuetionario);
		
		questionarioDAO.removerQuestao(questionario, questao);
		
		return new ModelAndView("redirect:/deletaQuestao");
	}
	
	@RequestMapping(value = "/inativarQuestionario", method = RequestMethod.GET)
	public ModelAndView inativarQuestionario(HttpServletRequest request) {		
		int cdQuestionario = Integer.parseInt(request.getParameter("cdQuestionario"));
		
		Questionario questionario = questionarioDAO.getById(cdQuestionario);
		
		questionarioDAO.inativarQuestionario(questionario);
		
		ModelAndView model = new ModelAndView("redirect:/consultarMeusQuestionarios");
		
		return model;
	}	
	
	@RequestMapping(value = "/ativarQuestionario", method = RequestMethod.GET)
	public ModelAndView ativarQuestionario(HttpServletRequest request) {		
		int cdQuestionario = Integer.parseInt(request.getParameter("cdQuestionario"));
		
		Questionario questionario = questionarioDAO.getById(cdQuestionario);
		
		questionarioDAO.ativarQuestionario(questionario);
		
		ModelAndView model = new ModelAndView("redirect:/consultarMeusQuestionarios");
		
		return model;
	}
	
	@RequestMapping(value="/consultarMeusQuestionarios")
	public ModelAndView consultarMeusQuestionarios(ModelAndView model, HttpServletRequest request, HttpSession session) throws IOException {
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");		
		usuario = usuarioDAO.getById(usuario.getCdUsuario());
		
		List<Questionario> listaQuestionarios = questionarioDAO.listarMeusQuestionarios(usuario.getCdUsuario());
		model.addObject("listaQuestionarios", listaQuestionarios);
				
		model.setViewName("professor/consultarMeusQuestionarios");
		
		return model;
	}
	
	@RequestMapping(value = "/detalharMeuQuestionario", method = RequestMethod.GET)
	public ModelAndView detalharMeuQuestionario(HttpServletRequest request, HttpSession session) {
		int cdQuestionario = Integer.parseInt(request.getParameter("cdQuestionario"));
		
		Questionario questionario = questionarioDAO.getById(cdQuestionario);
		
		session.setAttribute("questionario", questionario);
				
		List<Questao> listaQuestoes = questaoDAO.listarQuestoesQuestionario(questionario.getCdQuestionario());
		
		ModelAndView model = new ModelAndView("professor/detalharMeuQuestionario");
		
		List<Categoria> listaCategoria = categoriaDAO.listarCategoria();
		model.addObject("listaCategoria", listaCategoria);
		
		model.addObject("questionario", questionario);
		
		model.addObject("listaQuestoes", listaQuestoes);
		
		return model;
	}
}
