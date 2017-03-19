package com.projeto.uniprof.model;

import java.io.Serializable;

public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int cdCurso;
	private String nomeCurso;
	private String dsCurso;
	private String statusCurso;
	
	private int cdGrupoCurso;
	
	private Grupo grupo;
	private Usuario usuario;
	
	public Curso() {
		super();
	}
	
	public Curso(String nomeCurso, String dsCurso) {
		super();
		this.nomeCurso = nomeCurso;
		this.dsCurso = dsCurso;
	}

	public int getCdCurso() {
		return cdCurso;
	}

	public void setCdCurso(int cdCurso) {
		this.cdCurso = cdCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getDsCurso() {
		return dsCurso;
	}

	public void setDsCurso(String dsCurso) {
		this.dsCurso = dsCurso;
	}

	public String getStatusCurso() {
		return statusCurso;
	}

	public void setStatusCurso(String statusCurso) {
		this.statusCurso = statusCurso;
	}
	
	public int getCdGrupoCurso() {
		return cdGrupoCurso;
	}

	public void setCdGrupoCurso(int cdGrupoCurso) {
		this.cdGrupoCurso = cdGrupoCurso;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}