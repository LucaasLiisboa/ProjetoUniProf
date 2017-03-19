package com.projeto.uniprof.dao;

import java.util.List;

import com.projeto.uniprof.model.Questao;

public interface QuestaoDAO {
	public void cadastrarQuestao(Questao questao, int cdUsuario);
	
	public List<Questao> listarQuestao();
	
	public Questao getById(int cdQuestao);
	
	public void editarQuestao(Questao questao);
	
	public List<Questao> listarQuestoesQuestionario(int cdQuestionario);
	
	public List<Questao> listarQuestoesForaQuestionario(int cdQuestionario);
	
	public void inativarQuestao(Questao questao);
	
	public void ativarQuestao(Questao questao);
	
	public List<Questao> listarMinhasQuestoes(int cdUsuario);
}