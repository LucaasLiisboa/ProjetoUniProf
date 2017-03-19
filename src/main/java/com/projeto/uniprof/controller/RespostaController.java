package com.projeto.uniprof.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.uniprof.dao.CursoDAO;
import com.projeto.uniprof.dao.GrupoDAO;
import com.projeto.uniprof.dao.QuestaoDAO;
import com.projeto.uniprof.dao.QuestionarioDAO;
import com.projeto.uniprof.dao.RespostaDAO;
import com.projeto.uniprof.model.Curso;
import com.projeto.uniprof.model.Grupo;
import com.projeto.uniprof.model.Questao;
import com.projeto.uniprof.model.Questionario;
import com.projeto.uniprof.model.Resposta;
import com.projeto.uniprof.model.Usuario;

@Controller
@Scope("session")
@SessionAttributes({"usuarioSessao"})
public class RespostaController {
	@Autowired
	private RespostaDAO respostaDAO;
	
	@Autowired
	private CursoDAO cursoDAO;
	
	@Autowired
	private GrupoDAO grupoDAO;
	
	@Autowired
	private QuestionarioDAO questionarioDAO;
	
	@Autowired
	private QuestaoDAO questaoDAO;
				
	@RequestMapping(value = "/novaInscricaoGrupoCursoQuestionario", method = RequestMethod.GET)
	public ModelAndView inscreverGrupoCursoQuestionario(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Curso cursoAtual;
		Grupo grupoAtual;
		
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
		session.setAttribute("usuarioLogado", usuario);
		
		if((Curso)session.getAttribute("curso") == null) {
			int cdGrupoCurso = Integer.parseInt(request.getParameter("cdGrupoCurso"));
			
			cursoAtual = cursoDAO.getGrupoCursoById(cdGrupoCurso);
			
			grupoAtual = grupoDAO.getById(cursoAtual.getGrupo().getCdGrupo());
			
			cursoAtual = cursoDAO.getById(cursoAtual.getCdCurso());
			
			cursoAtual.setGrupo(grupoAtual);
			
			cursoAtual.setCdGrupoCurso(cdGrupoCurso);
			
			cursoAtual.setGrupo(grupoAtual);
			
			model.addObject("curso", cursoAtual);
			model.addObject("grupo", grupoAtual);
		} else {
			cursoAtual = (Curso)session.getAttribute("curso");
			grupoAtual = (Grupo)session.getAttribute("grupo");
			
			cursoAtual.setGrupo(grupoAtual);
			
			model.addObject("curso", cursoAtual);			
			model.addObject("grupo", grupoAtual);
		}
		
		session.setAttribute("curso", cursoAtual);
		session.setAttribute("grupo", grupoAtual);
		
		List<Questionario> listaQuestionarios = questionarioDAO.listarQuestionariosForaGrupoCurso(cursoAtual.getCdGrupoCurso());
		model.addObject("listaQuestionarios", listaQuestionarios);
				
		model.setViewName("professor/novaInscricaoGrupoCursoQuestionario");
		
		return model;
	}
	
	@RequestMapping(value = "/inscreverGrupoQuestionario")
	public ModelAndView inscreverGrupoQuestionario(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Curso curso = (Curso)session.getAttribute("curso");
		
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
		
		int cdQuestionario = Integer.parseInt(request.getParameter("cdQuestionario"));
		
		Questionario questionario = questionarioDAO.getById(cdQuestionario);
		
		session.setAttribute("curso", curso);
		
		respostaDAO.inscreverGrupoQuestionario(curso.getCdGrupoCurso(), questionario, usuario);
		
		return new ModelAndView("redirect:/novaInscricaoGrupoCursoQuestionario");
	}
	
	@RequestMapping(value = "/removerInscricaoGrupoCursoQuestionario", method = RequestMethod.GET)
	public ModelAndView removerInscricaoGrupoCursoQuestionario(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Curso cursoAtual;
		Grupo grupoAtual;
		
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
		session.setAttribute("usuarioLogado", usuario);
		
		if((Curso)session.getAttribute("curso") == null) {
			int cdGrupoCurso = Integer.parseInt(request.getParameter("cdGrupoCurso"));
			cursoAtual = cursoDAO.getGrupoCursoById(cdGrupoCurso);
			
			grupoAtual = grupoDAO.getById(cursoAtual.getGrupo().getCdGrupo());
			
			cursoAtual = cursoDAO.getById(cursoAtual.getCdCurso());
			
			cursoAtual.setGrupo(grupoAtual);
			
			cursoAtual.setCdGrupoCurso(cdGrupoCurso);
			
			model.addObject("curso", cursoAtual);
		} else {
			cursoAtual = (Curso)session.getAttribute("curso");
			
			model.addObject("curso", cursoAtual);
		}
		
		session.setAttribute("curso", cursoAtual);		
		
		List<Questionario> listaQuestionarios = questionarioDAO.listarQuestionariosDentroGrupoCurso(cursoAtual.getCdGrupoCurso());
		model.addObject("listaQuestionarios", listaQuestionarios);
				
		model.setViewName("professor/removerInscricaoGrupoCursoQuestionario");
		
		return model;
	}
	
	@RequestMapping(value = "/removeGrupoQuestionario")
	public ModelAndView removeGrupoQuestionario(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Curso curso = (Curso)session.getAttribute("curso");
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
		
		int cdQuestionario = Integer.parseInt(request.getParameter("cdQuestionario"));
		
		Questionario questionario = questionarioDAO.getById(cdQuestionario);
		
		session.setAttribute("curso", curso);
		
		respostaDAO.removeGrupoQuestionario(curso.getCdGrupoCurso(), questionario, usuario);
		
		return new ModelAndView("redirect:/removerInscricaoGrupoCursoQuestionario");
	}
	
	@RequestMapping(value="/selecionarQuestionario")
	public ModelAndView selecionarQuestionario(ModelAndView model, HttpSession session) throws IOException {
		Curso curso = (Curso)session.getAttribute("cursoUsuarioLogado");		
		
		List<Questionario> listaQuestionarios = questionarioDAO.listarQuestionariosDentroGrupoCursoNaoRespondido(curso.getCdGrupoCurso());
			
		model.addObject("listaQuestionarios", listaQuestionarios);
			
		model.setViewName("aluno/selecionarQuestionario");			
				
		return model;
	}
	
	@RequestMapping(value="/sobreQuestionario")
	public ModelAndView sobreQuestionario(ModelAndView model, HttpServletRequest request, HttpSession session) {
		int cdQuestionario = Integer.parseInt(request.getParameter("cdQuestionario"));
		
		Questionario questionario = questionarioDAO.getById(cdQuestionario);
				
		model.addObject("questionario", questionario);
		
		session.setAttribute("questionario", questionario);
		
		model.setViewName("aluno/sobreQuestionario");
		
		return model;
	}
	
	@RequestMapping(value="/responderQuestionario", method = RequestMethod.GET)
	public ModelAndView responderQuestionario(ModelAndView model, HttpServletRequest request, HttpSession session) {
		int cdQuestionario = Integer.parseInt(request.getParameter("cdQuestionario"));
		
		Questionario questionario = questionarioDAO.getById(cdQuestionario);
		
		session.setAttribute("questionario", questionario);
				
		List<Questao> listaQuestoes = questaoDAO.listarQuestoesQuestionario(questionario.getCdQuestionario());
		
		model.addObject("questionario", questionario);
		
		model.addObject("listaQuestoes", listaQuestoes);
		
		session.setAttribute("questionario", questionario);
		
		model.setViewName("aluno/responderQuestionario");
		
		return model;
	}
	
	@RequestMapping(value="/incluirRespostaQuestionario", method = RequestMethod.POST)
	public ModelAndView incluirRespostaQuestionario(ModelAndView model, HttpServletRequest request, HttpSession session, Questionario questionario, Usuario usuario) {
		List<Resposta> listaRespostas = questionario.getQuestao().getResposta().getListaRespostas();
		
		questionario = (Questionario)session.getAttribute("questionario");
		usuario = (Usuario)session.getAttribute("usuarioLogado");
		
		for(int i = 0; i < listaRespostas.size(); i++) {
			Resposta resposta = respostaDAO.getQuestionarioQuestao(questionario.getCdQuestionario(), questionario.getQuestoes().get(i).getCdQuestao());
			
			respostaDAO.responderQuestionario(resposta.getCdQuestionarioQuestao(), usuario.getCdUsuario(), listaRespostas.get(i).getAlternativaSelecionada());
		}
		
		List<Resposta> listaRespostasBase = respostaDAO.listarRespostas(questionario);
		
		session.setAttribute("listaRespostas", listaRespostasBase);		
				
		model.setViewName("redirect:/corrigirQuestionario");
						
		return model;
	}
	
	@RequestMapping(value="/corrigirQuestionario")
	public ModelAndView corrigirQuestionario(ModelAndView model, HttpServletRequest request, HttpSession session, Questionario questionario, Usuario usuario) {
		questionario = (Questionario)session.getAttribute("questionario");
		usuario = (Usuario)session.getAttribute("usuarioLogado");
		
		double nota = 0;
		
		@SuppressWarnings("unchecked")
		List<Resposta> listaRespostas = (List<Resposta>)session.getAttribute("listaRespostas");
		
		for(int i = 0; i < questionario.getQuestoes().size(); i++) {			
			if(questionario.getCdQuestionario() == listaRespostas.get(i).getQuestionario().getCdQuestionario()) {
				if(questionario.getQuestoes().get(i).getCdQuestao() == listaRespostas.get(i).getQuestionario().getQuestoes().get(i).getCdQuestao()){
					if(questionario.getQuestoes().get(i).getAlternativaCorreta().equals(listaRespostas.get(i).getAlternativaSelecionada())) {
						nota++;
					}
				}
			}
		}
		
		System.out.println("Nota:" + nota);
		
		model.setViewName("redirect:/selecionarQuestionario");
		
		return model;
	}
}