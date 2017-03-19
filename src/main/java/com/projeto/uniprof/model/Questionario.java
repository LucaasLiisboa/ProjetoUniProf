package com.projeto.uniprof.model;

import java.io.Serializable;
import java.util.List;

public class Questionario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int cdQuestionario;
	private String tituloQuestionario;
	private String dsQuestionario;
	private int qtdQuestoes;
	private String statusQuestionario;
	private Usuario usuario;
	private Questao questao;
	private List<Questao> questoes;
	private Categoria categoria;
	
	public Questionario() {
		super();
	}
	
	public Questionario(String tituloQuestionario, String dsQuestionario, int qtdQuestoes, Usuario usuario) {
		super();
		this.tituloQuestionario = tituloQuestionario;
		this.dsQuestionario = dsQuestionario;
		this.qtdQuestoes = qtdQuestoes;
		this.usuario = usuario;
	}

	public int getCdQuestionario() {
		return cdQuestionario;
	}

	public void setCdQuestionario(int cdQuestionario) {
		this.cdQuestionario = cdQuestionario;
	}

	public String getTituloQuestionario() {
		return tituloQuestionario;
	}

	public void setTituloQuestionario(String tituloQuestionario) {
		this.tituloQuestionario = tituloQuestionario;
	}

	public String getDsQuestionario() {
		return dsQuestionario;
	}

	public void setDsQuestionario(String dsQuestionario) {
		this.dsQuestionario = dsQuestionario;
	}

	public int getQtdQuestoes() {
		return qtdQuestoes;
	}

	public void setQtdQuestoes(int qtdQuestoes) {
		this.qtdQuestoes = qtdQuestoes;
	}

	public String getStatusQuestionario() {
		return statusQuestionario;
	}

	public void setStatusQuestionario(String statusQuestionario) {
		this.statusQuestionario = statusQuestionario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
}