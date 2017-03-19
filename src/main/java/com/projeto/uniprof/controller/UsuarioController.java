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
public class UsuarioController {
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private GrupoDAO grupoDAO;
	
	@Autowired
	private CursoDAO cursoDAO;
		
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	private String logar(Usuario usuario, HttpSession session, HttpServletRequest request) {		
		Usuario usuarioLogado = usuarioDAO.logar(usuario.getEmailUsuario(), usuario.getSenhaUsuario());
				
		if(usuarioLogado == null) {
			return "redirect:/";
		} else {
			if(usuarioLogado.getStatusUsuario().equals("Inativo")) {
				return "redirect:/";
			} else {
				Curso curso = null;
				Grupo grupo = grupoDAO.getGruposUsuario(usuarioLogado.getCdUsuario());
				
				if(grupo != null) {
					curso = cursoDAO.getGruposCurso(grupo.getCdGrupo());
					
					int cdGrupoCurso = curso.getCdGrupoCurso();
					
					curso = cursoDAO.getById(curso.getCdCurso());
					
					curso.setCdGrupoCurso(cdGrupoCurso);					
					
					curso.setGrupo(grupo);
				}
				
				usuarioLogado.setGrupo(grupo);
				
				session.setAttribute("grupoUsuarioLogado", grupo);
				session.setAttribute("cursoUsuarioLogado", curso);
				session.setAttribute("usuarioLogado", usuarioLogado);				
			}			
		}
		
		return "principal";
	}
	
	@RequestMapping(value = "/novoUsuario", method = RequestMethod.GET)
	public ModelAndView novoUsuario(ModelAndView model) {
		Usuario novoUsuario = new Usuario();
		model.addObject("usuario", novoUsuario);
		model.setViewName("cadastrarUsuario");
		
		return model;
	}
	
	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
	public ModelAndView cadastrarUsuario(@ModelAttribute Usuario usuario, HttpServletRequest request) {
		usuarioDAO.cadastrarUsuario(usuario);
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/consultarUsuario")
	public ModelAndView listarUsuarios(ModelAndView model) throws IOException {
		List<Usuario> listaUsuarios = usuarioDAO.listarUsuario();
		model.addObject("listaUsuarios", listaUsuarios);
		model.setViewName("administrador/consultarUsuario");
		
		return model;
	}
	
	@RequestMapping(value = "/editarUsuario", method = RequestMethod.GET)
	public ModelAndView editarUsuario(HttpServletRequest request) {
		int cdUsuario = Integer.parseInt(request.getParameter("cdUsuario"));
		
		Usuario usuario = usuarioDAO.getById(cdUsuario);
		
		ModelAndView model = new ModelAndView("administrador/editarUsuario");
		model.addObject("usuario", usuario);

		return model;
	}
	
	@RequestMapping(value = "/alterarPermissao", method = RequestMethod.POST)
	public ModelAndView alterarPermissao(@ModelAttribute Usuario usuario, HttpServletRequest request, HttpSession session) {
		usuarioDAO.alterarPermissao(usuario);
				
		Usuario usuarioLogado = (Usuario)session.getAttribute("usuarioLogado");
		
		if(usuarioLogado.getCdUsuario() == usuario.getCdUsuario()) {
			usuarioLogado = usuarioDAO.getById(usuario.getCdUsuario());
			
			session.setAttribute("usuarioLogado", usuarioLogado);
			
			return new ModelAndView("redirect:/consultarUsuario");
		}
		
		return new ModelAndView("redirect:/consultarUsuario");
	}
		
	@RequestMapping(value = "/editarPerfil", method = RequestMethod.GET)
	public ModelAndView editarPerfil(HttpServletRequest request) {
		int cdUsuario = Integer.parseInt(request.getParameter("cdUsuario"));
		
		Usuario usuario = usuarioDAO.getById(cdUsuario);
		
		ModelAndView model = new ModelAndView("aluno/editarPerfil");
		
		model.addObject("usuario", usuario);

		return model;
	}
	
	@RequestMapping(value = "/salvarEdicao", method = RequestMethod.POST)
	public ModelAndView salvarEdicao(@ModelAttribute Usuario usuario, HttpServletRequest request) {
		usuarioDAO.editarUsuario(usuario);
		
		return new ModelAndView("redirect:/principal");
	}
	
	@RequestMapping(value = "/inativarUsuario")
	public ModelAndView inativarUsuario(HttpServletRequest request) {
		int cdUsuario = Integer.parseInt(request.getParameter("cdUsuario"));
		
		Usuario usuario = usuarioDAO.getById(cdUsuario);
		
		usuarioDAO.inativarUsuario(usuario);
		
		return new ModelAndView("redirect:/consultarUsuario");
	}
	
	@RequestMapping(value = "/ativarUsuario")
	public ModelAndView ativarUsuario(HttpServletRequest request) {
		int cdUsuario = Integer.parseInt(request.getParameter("cdUsuario"));
		
		Usuario usuario = usuarioDAO.getById(cdUsuario);
		
		usuarioDAO.ativarUsuario(usuario);
		
		return new ModelAndView("redirect:/consultarUsuario");
	}
	
	@RequestMapping(value = "/detalharUsuario", method = RequestMethod.GET)
	public ModelAndView detalharUsuario(HttpServletRequest request) {
		int cdUsuario = Integer.parseInt(request.getParameter("cdUsuario"));
		
		Usuario usuario = usuarioDAO.getById(cdUsuario);
		
		ModelAndView model = new ModelAndView("coordenador/detalharUsuario");
		model.addObject("usuario", usuario);

		return model;
	}
}