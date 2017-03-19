package com.projeto.uniprof.dao;

import java.util.List;

import com.projeto.uniprof.model.Curso;
import com.projeto.uniprof.model.Grupo;

public interface CursoDAO {
	public void cadastrarCurso(Curso curso, int cdUsuario);
	
	public List<Curso> listarCurso();
	
	public Curso getById(int cdCurso);
	
	public void editarCurso(Curso curso);
	
	public List<Curso> listarMeusCursos(int cdUsuario);
	
	public void addGrupoCurso(Curso curso, Grupo grupo);
	
	public void removerGrupoCurso(Curso curso, Grupo grupo);
	
	public void atualizarGrupoCurso(Curso curso, Grupo grupo);
	
	public void inativarCurso(Curso curso);
	
	public void ativarCurso(Curso curso);
	
	public Curso getGrupoCurso(int cdGrupo, int cdCurso);
	
	public List<Curso> getNomeGrupoNomeCurso();
	
	public Curso getGrupoCursoById(int cdGrupoCurso);

	public Curso getGruposCurso(int cdGrupo);
}