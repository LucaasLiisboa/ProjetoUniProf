package com.projeto.uniprof.model;

import java.io.Serializable;

public class Questao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int cdQuestao;
	private String enunciadoQuestao;
	private String alternativaA;
	private String alternativaB;
	private String alternativaC;
	private String alternativaD;
	private String alternativaE;	
	private String alternativaCorreta;
	private String statusQuestao;
	private Usuario usuario;
	private Categoria categoria;
	private Resposta resposta;
	
	public Questao() {
		super();
	}
	
	public Questao(int cdQuestao, String enunciadoQuestao, String alternativaA, String alternativaB,
			String alternativaC, String alternativaD, String alternativaE, String alternativaCorreta, Usuario usuario, Categoria categoria) {
		super();
		this.cdQuestao = cdQuestao;
		this.enunciadoQuestao = enunciadoQuestao;
		this.alternativaA = alternativaA;
		this.alternativaB = alternativaB;
		this.alternativaC = alternativaC;
		this.alternativaD = alternativaD;
		this.alternativaE = alternativaE;
		this.alternativaCorreta = alternativaCorreta;
		this.usuario = usuario;
		this.categoria = categoria;
	}

	public int getCdQuestao() {
		return cdQuestao;
	}

	public void setCdQuestao(int cdQuestao) {
		this.cdQuestao = cdQuestao;
	}

	public String getEnunciadoQuestao() {
		return enunciadoQuestao;
	}

	public void setEnunciadoQuestao(String enunciadoQuestao) {
		this.enunciadoQuestao = enunciadoQuestao;
	}

	public String getAlternativaA() {
		return alternativaA;
	}

	public void setAlternativaA(String alternativaA) {
		this.alternativaA = alternativaA;
	}

	public String getAlternativaB() {
		return alternativaB;
	}

	public void setAlternativaB(String alternativaB) {
		this.alternativaB = alternativaB;
	}

	public String getAlternativaC() {
		return alternativaC;
	}

	public void setAlternativaC(String alternativaC) {
		this.alternativaC = alternativaC;
	}

	public String getAlternativaD() {
		return alternativaD;
	}

	public void setAlternativaD(String alternativaD) {
		this.alternativaD = alternativaD;
	}

	public String getAlternativaE() {
		return alternativaE;
	}

	public void setAlternativaE(String alternativaE) {
		this.alternativaE = alternativaE;
	}

	public String getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(String alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}
	
	public String getStatusQuestao() {
		return statusQuestao;
	}

	public void setStatusQuestao(String statusQuestao) {
		this.statusQuestao = statusQuestao;
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

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
}