package com.projeto.uniprof.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int cdUsuario;
	private String nomeUsuario;
	private String sobrenomeUsuario;
	private String emailUsuario;
	private String senhaUsuario;
	private String perguntaSeguranca;
	private String respostaSeguranca;
	private String tokenUsuario;
	private String statusUsuario;
	private String dsPermissao;
	
	private Grupo grupo;
	
	public Usuario() {
		super();		
	}
	
	public Usuario(String nomeUsuario, String sobrenomeUsuario, String emailUsuario, String senhaUsuario) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.sobrenomeUsuario = sobrenomeUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
	}
	
	public int getCdUsuario() {
		return cdUsuario;
	}
	
	public void setCdUsuario(int cdUsuario) {
		this.cdUsuario = cdUsuario;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getSobrenomeUsuario() {
		return sobrenomeUsuario;
	}
	
	public void setSobrenomeUsuario(String sobrenomeUsuario) {
		this.sobrenomeUsuario = sobrenomeUsuario;
	}
	
	public String getEmailUsuario() {
		return emailUsuario;
	}
	
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	
	public String getPerguntaSeguranca() {
		return perguntaSeguranca;
	}
	
	public void setPerguntaSeguranca(String perguntaSeguranca) {
		this.perguntaSeguranca = perguntaSeguranca;
	}
	
	public String getRespostaSeguranca() {
		return respostaSeguranca;
	}
	
	public void setRespostaSeguranca(String respostaSeguranca) {
		this.respostaSeguranca = respostaSeguranca;
	}

	public String getTokenUsuario() {
		return tokenUsuario;
	}

	public void setTokenUsuario(String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
	}

	public String getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(String statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public String getDsPermissao() {
		return dsPermissao;
	}

	public void setDsPermissao(String dsPermissao) {
		this.dsPermissao = dsPermissao;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String gerarToken() {
		String token = null;
		
		SecureRandom random = new SecureRandom();
		
		token = new BigInteger(30, random).toString(32).toUpperCase();
		
		return token;
	}
}