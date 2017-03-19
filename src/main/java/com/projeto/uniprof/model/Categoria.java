package com.projeto.uniprof.model;

import java.io.Serializable;

public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int cdCategoria;
    private String dsCategoria;
    private String statusCategoria;
    
    public Categoria() {
		super();
	}

	public Categoria(String dsCategoria) {
		super();
		this.dsCategoria = dsCategoria;
	}

	public int getCdCategoria() {
		return cdCategoria;
	}

	public void setCdCategoria(int cdCategoria) {
		this.cdCategoria = cdCategoria;
	}

	public String getDsCategoria() {
		return dsCategoria;
	}

	public void setDsCategoria(String dsCategoria) {
		this.dsCategoria = dsCategoria;
	}

	public String getStatusCategoria() {
		return statusCategoria;
	}

	public void setStatusCategoria(String statusCategoria) {
		this.statusCategoria = statusCategoria;
	}
}