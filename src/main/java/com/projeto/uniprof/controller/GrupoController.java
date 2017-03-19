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
public class GrupoController {
	@Autowired
	private GrupoDAO grupoDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private CursoDAO cursoDAO;
		
	@RequestMapping(value = "/novoGrupo", method = RequestMethod.GET)
	public ModelAndView novoGrupo(ModelAndView model) {
		Grupo novoGrupo = new Grupo();
		model.addObject("grupo", novoGrupo);
		
		List<Curso> listaCursos = cursoDAO.listarCurso();
		model.addObject("listaCursos", listaCursos);
		
		model.setViewName("coordenador/cadastrarGrupo");
		
		return model;
	}
	
	@RequestMapping(value = "/cadastrarGrupo", method = RequestMethod.POST)
	public ModelAndView cadastrarGrupo(@ModelAttribute Grupo grupo, HttpServletRequest request, HttpSession session) {
		int cdGrupo = 0;
		
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
		int cdCurso = grupo.getCurso().getCdCurso();
				
		grupoDAO.cadastrarGrupo(grupo, usuario.getCdUsuario());
		
		List<Grupo> listaGrupos = grupoDAO.listarGrupo();
				
		cdGrupo = listaGrupos.get(listaGrupos.size() - 1).getCdGrupo();		
		
		Grupo novoGrupo = grupoDAO.getById(cdGrupo);		
		Curso curso = cursoDAO.getById(cdCurso);
		
		cursoDAO.addGrupoCurso(curso, novoGrupo);
		
		curso.setGrupo(novoGrupo);
		grupo.setCurso(curso);
		
		session.setAttribute("grupo", novoGrupo);
		session.setAttribute("curso", curso);
			
		return new ModelAndView("redirect:/consultarMeusGrupos");
	}
	
	@RequestMapping(value="/consultarGrupo")
	public ModelAndView listarGrupo(ModelAndView model) throws IOException {
		List<Grupo> listaGrupo = grupoDAO.listarGrupo();
		model.addObject("listaGrupo", listaGrupo);
		
		model.setViewName("coordenador/consultarGrupo");
		
		return model;
	}
	
	@RequestMapping(value = "/editarGrupo", method = RequestMethod.GET)
	public ModelAndView editarGrupo(ModelAndView model, HttpServletRequest request, HttpSession session) {
		int cdGrupo = Integer.parseInt(request.getParameter("cdGrupo"));
		
		Grupo grupo = grupoDAO.getById(cdGrupo);
		
		List<Curso> listaCursos = cursoDAO.listarCurso();
		model.addObject("listaCursos", listaCursos);
				
		model.addObject("grupo", grupo);	
		
		session.setAttribute("grupo", grupo);
		
		model.setViewName("coordenador/editarGrupo");		

		return model;
	}
	
	@RequestMapping(value = "/salvarGrupo", method = RequestMethod.POST)
	public ModelAndView salvarGrupo(Grupo grupo, HttpServletRequest request, HttpSession session) {		
		grupoDAO.editarGrupo(grupo);
		
		Curso curso = cursoDAO.getById(grupo.getCurso().getCdCurso());
		
		cursoDAO.atualizarGrupoCurso(curso, grupo);
		
		return new ModelAndView("redirect:/consultarMeusGrupos");
	}
	
	@RequestMapping(value = "/adicionarGrupoUsuario", method = RequestMethod.GET)
	public ModelAndView adicionarGrupoUsuario(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Grupo grupoAtual;
		
		if((Grupo)session.getAttribute("grupo") == null) {
			int cdGrupo = Integer.parseInt(request.getParameter("grupo"));
			grupoAtual = grupoDAO.getById(cdGrupo);
			
			model.addObject("grupo", grupoAtual);
		} else {
			grupoAtual = (Grupo)session.getAttribute("grupo");
			
			model.addObject("grupo", grupoAtual);
		}
		
		session.setAttribute("grupo", grupoAtual);
		
		List<Usuario> listaUsuarios = usuarioDAO.listarUsuariosForaGrupo(grupoAtual.getCdGrupo());		
		model.addObject("listaUsuarios", listaUsuarios);
				
		model.setViewName("coordenador/adicionarGrupoUsuario");
		
		return model;
	}
		
	@RequestMapping(value = "/addUsuarioGrupo")
	public ModelAndView addUsuarioGrupo(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Grupo grupoAtual;
		
		if((Grupo)session.getAttribute("grupo") == null) {
			int cdGrupo = Integer.parseInt(request.getParameter("grupo"));
			grupoAtual = grupoDAO.getById(cdGrupo);
			
			model.addObject("grupo", grupoAtual);
		} else {
			grupoAtual = (Grupo)session.getAttribute("grupo");
			
			model.addObject("grupo", grupoAtual);
		}
		
		session.setAttribute("grupo", grupoAtual);
		
		int cdUsuario = Integer.parseInt(request.getParameter("cdUsuario"));		
		int cdGrupo = grupoAtual.getCdGrupo();
		
		Usuario usuario = usuarioDAO.getById(cdUsuario);
		grupoAtual = grupoDAO.getById(cdGrupo);
		
		grupoDAO.addUsuario(grupoAtual, usuario);
		
		return new ModelAndView("redirect:/adicionarGrupoUsuario");
	}
	
	
	@RequestMapping(value = "/inativarGrupo", method = RequestMethod.GET)
	public ModelAndView inativarGrupo(HttpServletRequest request) {		
		int cdGrupo = Integer.parseInt(request.getParameter("cdGrupo"));
		
		Grupo grupo = grupoDAO.getById(cdGrupo);
		
		grupoDAO.inativarGrupo(grupo);
		
		ModelAndView model = new ModelAndView("redirect:/consultarMeusGrupos");
		
		return model;
	}
	
	@RequestMapping(value = "/ativarGrupo", method = RequestMethod.GET)
	public ModelAndView ativarGrupo(HttpServletRequest request) {		
		int cdGrupo = Integer.parseInt(request.getParameter("cdGrupo"));
		
		Grupo grupo = grupoDAO.getById(cdGrupo);
		
		grupoDAO.ativarGrupo(grupo);
		
		ModelAndView model = new ModelAndView("redirect:/consultarMeusGrupos");
		
		return model;
	}
	
	@RequestMapping(value = "/detalharGrupo", method = RequestMethod.GET)
	public ModelAndView detalharGrupo(HttpServletRequest request, HttpSession session) {
		int cdGrupo = Integer.parseInt(request.getParameter("cdGrupo"));
		Grupo grupo = grupoDAO.getById(cdGrupo);
						
		ModelAndView model = new ModelAndView("coordenador/detalharGrupo");
		
		session.setAttribute("grupo", grupo);
		
		model.addObject("grupo", grupo);
		
		return model;
	}
	
	@RequestMapping(value = "/detalharMeusGrupos", method = RequestMethod.GET)
	public ModelAndView detalharMeusGrupos(HttpServletRequest request, HttpSession session) {
		int cdGrupo = Integer.parseInt(request.getParameter("cdGrupo"));
		Grupo grupo = grupoDAO.getById(cdGrupo);
						
		ModelAndView model = new ModelAndView("coordenador/detalharMeusGrupos");
				
		model.addObject("grupo", grupo);
		
		session.setAttribute("grupo", grupo);
		
		return model;
	}
	
	@RequestMapping(value="/consultarMeusGrupos")
	public ModelAndView consultarMeusGrupos(ModelAndView model, HttpServletRequest request, HttpSession session) throws IOException {		
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");		
		usuario = usuarioDAO.getById(usuario.getCdUsuario());
		
		List<Grupo> listaGrupos = grupoDAO.listarMeusGrupos(usuario.getCdUsuario());
		model.addObject("listaGrupos", listaGrupos);
				
		model.setViewName("coordenador/consultarMeusGrupos");
		
		return model;
	}
	
	@RequestMapping(value="/deletaUsuarioGrupo")
	public ModelAndView deletaUsuarioGrupo(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Grupo grupo = (Grupo)session.getAttribute("grupo");
		
		List<Usuario> listaUsuarios = usuarioDAO.listarUsuariosGrupo(grupo.getCdGrupo());
		
		model.addObject("listaUsuarios", listaUsuarios);
		
		model.addObject("grupo", grupo);
				
		model.setViewName("coordenador/removerUsuarioGrupo");
		
		return model;
	}
	
	@RequestMapping(value = "/removerUsuarioGrupo")
	public ModelAndView removerUsuarioGrupo(HttpServletRequest request, HttpSession session) {
		Grupo grupo = (Grupo)session.getAttribute("grupo");
		int cdUsuario = Integer.parseInt(request.getParameter("cdUsuario"));
		int cdGrupo = grupo.getCdGrupo();
		
		Usuario usuario = usuarioDAO.getById(cdUsuario);
		grupo = grupoDAO.getById(cdGrupo);
		
		grupoDAO.removerUsuario(grupo, usuario);
		
		return new ModelAndView("redirect:/deletaUsuarioGrupo");
	}
}