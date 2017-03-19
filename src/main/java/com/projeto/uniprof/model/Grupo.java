package com.projeto.uniprof.model;

import java.io.Serializable;

public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int cdGrupo;
	private String nomeGrupo;
	private String dsGrupo;
	private String statusGrupo;
	
	private Usuario usuario;
	private Curso curso;
		
	public Grupo() {
		super();
	}
	
	public Grupo(int cdGrupo, String nomeGrupo, String dsGrupo) {
		super();
		this.cdGrupo = cdGrupo;
		this.nomeGrupo = nomeGrupo;
		this.dsGrupo = dsGrupo;
	}

	public int getCdGrupo() {
		return cdGrupo;
	}

	public void setCdGrupo(int cdGrupo) {
		this.cdGrupo = cdGrupo;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public String getDsGrupo() {
		return dsGrupo;
	}

	public void setDsGrupo(String dsGrupo) {
		this.dsGrupo = dsGrupo;
	}

	public String getStatusGrupo() {
		return statusGrupo;
	}

	public void setStatusGrupo(String statusGrupo) {
		this.statusGrupo = statusGrupo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}