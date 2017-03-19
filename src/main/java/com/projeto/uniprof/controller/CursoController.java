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

import com.projeto.uniprof.dao.CursoDAO;
import com.projeto.uniprof.dao.GrupoDAO;
import com.projeto.uniprof.dao.UsuarioDAO;
import com.projeto.uniprof.model.Curso;
import com.projeto.uniprof.model.Grupo;
import com.projeto.uniprof.model.Usuario;

@Controller
@Scope("session")
@SessionAttributes({"usuarioSessao"})
public class CursoController {
	@Autowired
	private CursoDAO cursoDAO;

	@Autowired
	private GrupoDAO grupoDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@RequestMapping(value = "/novoCurso", method = RequestMethod.GET)
	public ModelAndView novoCurso(ModelAndView model) {
		Curso novoCurso = new Curso();
		model.addObject("curso", novoCurso);
		model.setViewName("coordenador/cadastrarCurso");

		return model;
	}

	@RequestMapping(value = "/cadastrarCurso", method = RequestMethod.POST)
	public ModelAndView cadastrarCurso(@ModelAttribute Curso curso, HttpServletRequest request, HttpSession session) {
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");				
		cursoDAO.cadastrarCurso(curso, usuario.getCdUsuario());
		
		session.setAttribute("curso", curso);

		return new ModelAndView("redirect:/consultarMeusCursos");
	}

	@RequestMapping(value = "/consultarCurso")
	public ModelAndView listarCurso(ModelAndView model) throws IOException {
		List<Curso> listaCurso = cursoDAO.listarCurso();
		model.addObject("listaCurso", listaCurso);
		model.setViewName("coordenador/consultarCurso");

		return model;
	}

	@RequestMapping(value = "/editarCurso", method = RequestMethod.GET)
	public ModelAndView editarCurso(HttpServletRequest request, HttpSession session) {
		int cdCurso = Integer.parseInt(request.getParameter("cdCurso"));

		Curso curso = cursoDAO.getById(cdCurso);

		ModelAndView model = new ModelAndView("coordenador/editarCurso");
		model.addObject("curso", curso);
		
		session.setAttribute("curso", curso);
		
		return model;
	}

	@RequestMapping(value = "/salvarCurso", method = RequestMethod.POST)
	public ModelAndView salvarCurso(@ModelAttribute Curso curso, HttpServletRequest request) {
		cursoDAO.editarCurso(curso);

		return new ModelAndView("redirect:/consultarMeusCursos");
	}
	
	@RequestMapping(value = "/adicionarGrupoCurso", method = RequestMethod.GET)
	public ModelAndView adicionarGrupoCurso(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Curso cursoAtual;		
		
		if((Curso)session.getAttribute("curso") == null) {
			int cdCurso = Integer.parseInt(request.getParameter("cdCurso"));			
			cursoAtual = cursoDAO.getById(cdCurso);
			
			model.addObject("curso", cursoAtual);
		} else {
			cursoAtual = (Curso)session.getAttribute("curso");
			
			model.addObject("curso", cursoAtual);
		}
		
		session.setAttribute("curso", cursoAtual);
		
		List<Grupo> listarGrupos = grupoDAO.listarGruposForaCurso(cursoAtual.getCdCurso());		
		model.addObject("listarGrupos", listarGrupos);
		
		model.setViewName("coordenador/adicionarGrupoCurso");
		
		return model;
	}
	
	@RequestMapping(value = "/addGrupoCurso")
	public ModelAndView addGrupoCurso(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Curso curso = (Curso)session.getAttribute("curso");
		int cdGrupo = Integer.parseInt(request.getParameter("cdGrupo"));		
		int cdCurso = curso.getCdCurso();		
		
		Grupo grupo = grupoDAO.getById(cdGrupo);
		curso = cursoDAO.getById(cdCurso);
		
		session.setAttribute("curso", curso);
		
		cursoDAO.addGrupoCurso(curso, grupo);
		
		return new ModelAndView("redirect:/adicionarGrupoCurso");
	}
		
	@RequestMapping(value = "/inativarCurso", method = RequestMethod.GET)
	public ModelAndView inativarCurso(HttpServletRequest request) {		
		int cdCurso = Integer.parseInt(request.getParameter("cdCurso"));
		
		Curso curso = cursoDAO.getById(cdCurso);
		
		cursoDAO.inativarCurso(curso);
		
		ModelAndView model = new ModelAndView("redirect:/consultarMeusCursos");
		
		return model;
	}
	
	@RequestMapping(value = "/ativarCurso", method = RequestMethod.GET)
	public ModelAndView ativarCurso(HttpServletRequest request) {		
		int cdCurso = Integer.parseInt(request.getParameter("cdCurso"));
		
		Curso curso = cursoDAO.getById(cdCurso);
		
		cursoDAO.ativarCurso(curso);
		
		ModelAndView model = new ModelAndView("redirect:/consultarMeusCursos");
		
		return model;
	}
	
	@RequestMapping(value = "/detalharCurso", method = RequestMethod.GET)
	public ModelAndView detalharCurso(HttpServletRequest request, HttpSession session) {
		int cdCurso = Integer.parseInt(request.getParameter("cdCurso"));
		Curso curso = cursoDAO.getById(cdCurso);
						
		ModelAndView model = new ModelAndView("coordenador/detalharCurso");
		
		session.setAttribute("curso", curso);
		
		model.addObject("curso", curso);
		
		return model;
	}
	
	@RequestMapping(value = "/detalharMeusCursos", method = RequestMethod.GET)
	public ModelAndView detalharMeusCursos(HttpServletRequest request, HttpSession session) {
		int cdCurso = Integer.parseInt(request.getParameter("cdCurso"));
		Curso curso = cursoDAO.getById(cdCurso);
						
		ModelAndView model = new ModelAndView("coordenador/detalharMeusCursos");
		
		session.setAttribute("curso", curso);
		
		model.addObject("curso", curso);
		
		return model;
	}
	
	@RequestMapping(value="/consultarMeusCursos")
	public ModelAndView consultarMeusCursos(ModelAndView model, HttpServletRequest request, HttpSession session) throws IOException {		
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
		usuario = usuarioDAO.getById(usuario.getCdUsuario());
		
		List<Curso> listaCursos = cursoDAO.listarMeusCursos(usuario.getCdUsuario());
		model.addObject("listaCursos", listaCursos);
				
		model.setViewName("coordenador/consultarMeusCursos");
		
		return model;
	}
	
	@RequestMapping(value="/deletaGrupoCurso")
	public ModelAndView deletaGrupoCurso(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Curso curso = (Curso)session.getAttribute("curso");
		
		List<Grupo> listaGrupos = grupoDAO.listarGruposCurso();
		
		model.addObject("listaGrupos", listaGrupos);
		
		model.addObject("curso", curso);
				
		model.setViewName("coordenador/removerGrupoCurso");
		
		return model;
	}
	
	@RequestMapping(value = "/removerGrupoCurso")
	public ModelAndView removerGrupoCurso(HttpServletRequest request, HttpSession session) {
		Curso curso = (Curso)session.getAttribute("curso");
		int cdGrupo = Integer.parseInt(request.getParameter("cdGrupo"));
		int cdCurso = curso.getCdCurso();
		
		Grupo grupo = grupoDAO.getById(cdGrupo);
		curso = cursoDAO.getById(cdCurso);
				
		cursoDAO.removerGrupoCurso(curso, grupo);
		
		return new ModelAndView("redirect:/deletaGrupoCurso");
	}
	
	
	
	@RequestMapping(value="/consultarGrupoCurso")
	public ModelAndView consultarGrupoCurso(ModelAndView model, HttpServletRequest request, HttpSession session) throws IOException {
		List<Curso> listaGrupoCurso = cursoDAO.getNomeGrupoNomeCurso();
		model.addObject("listaGrupoCurso", listaGrupoCurso);
				
		model.setViewName("professor/consultarGrupoCurso");
		
		return model;
	}
}