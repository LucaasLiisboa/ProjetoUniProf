package com.projeto.uniprof.dao;

import java.util.List;

import com.projeto.uniprof.model.Grupo;
import com.projeto.uniprof.model.Usuario;

public interface GrupoDAO {
	public void cadastrarGrupo(Grupo grupo, int cdUsuario);
	
	public List<Grupo> listarGrupo();
	
	public Grupo getById(int cdGrupo);
	
	public void editarGrupo(Grupo grupo);
	
	public List<Grupo> listarMeusGrupos(int cdUsuario);
	
	public void addUsuario(Grupo grupo, Usuario usuario);
	
	public void removerUsuario(Grupo grupo, Usuario usuario);
	
	public void inativarGrupo(Grupo grupo);
	
	public void ativarGrupo(Grupo grupo);
	
	public Grupo getGrupoUsuario(int cdGrupo, int cdUsuario);
	
	public List<Grupo> listarGruposCurso();
	
	public List<Grupo> listarGruposForaCurso(int cdCurso);
	
	public Grupo getGruposUsuario(int cdUsuario);
}