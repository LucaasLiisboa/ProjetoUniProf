package com.projeto.uniprof.dao;

import java.util.List;

import com.projeto.uniprof.model.Questao;
import com.projeto.uniprof.model.Questionario;


public interface QuestionarioDAO {
	public void cadastrarQuestionario(Questionario questionario, int cdUsuario);
	
	public List<Questionario> listarQuestionario();
	
	public Questionario getById(int cdQuestionario);
	
	public void editarQuestionario(Questionario questionario);
	
	public void addQuestao(Questionario questionario, Questao questao);
	
	public void removerQuestao(Questionario questionario, Questao questao);
	
	public void inativarQuestionario(Questionario questionario);
	
	public void ativarQuestionario(Questionario questionario);
	
	public List<Questionario> listarMeusQuestionarios(int cdUsuario);
	
	public List<Questionario> listarQuestionariosForaGrupoCurso(int cdQuestionario);
	
	public List<Questionario> listarQuestionariosDentroGrupoCurso(int cdGrupoCurso);
	
	public List<Questionario> listarQuestionariosDentroGrupoCursoNaoRespondido(int cdGrupoCurso);
}
