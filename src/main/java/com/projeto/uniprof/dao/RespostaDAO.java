package com.projeto.uniprof.dao;

import java.util.List;

import com.projeto.uniprof.model.Questionario;
import com.projeto.uniprof.model.Resposta;
import com.projeto.uniprof.model.Usuario;

public interface RespostaDAO {	
	public void inscreverGrupoQuestionario(int cdGrupoCurso, Questionario questionario, Usuario usuario);
	
	public void removeGrupoQuestionario(int cdGrupoCurso, Questionario questionario, Usuario usuario);
	
	public Resposta getQuestionarioQuestao(int cdQuestionario, int cdQuestao);
	
	public void responderQuestionario(int cdQuestionarioQuestao, int cdUsuario, String alternativaSelecionada);
	
	public List<Resposta> listarRespostas(Questionario questionario);
}