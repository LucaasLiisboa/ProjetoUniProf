package com.projeto.uniprof.model;

import java.io.Serializable;
import java.util.List;

public class Resposta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String alternativaSelecionada;	
	private List<Resposta> listaRespostas;
	private int cdQuestionarioQuestao;
	
	private Questionario questionario;
	private Questao questao;
	private Usuario usuario;
		
	public Resposta() {
		super();		
	}
	
	public Resposta(String alternativaSelecionada) {
		super();
		this.alternativaSelecionada = alternativaSelecionada;
	}

	public String getAlternativaSelecionada() {
		return alternativaSelecionada;
	}

	public void setAlternativaSelecionada(String alternativaSelecionada) {
		this.alternativaSelecionada = alternativaSelecionada;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getCdQuestionarioQuestao() {
		return cdQuestionarioQuestao;
	}

	public void setCdQuestionarioQuestao(int cdQuestionarioQuestao) {
		this.cdQuestionarioQuestao = cdQuestionarioQuestao;
	}

	public List<Resposta> getListaRespostas() {
		return listaRespostas;
	}

	public void setListaRespostas(List<Resposta> listaRespostas) {
		this.listaRespostas = listaRespostas;
	}	
}