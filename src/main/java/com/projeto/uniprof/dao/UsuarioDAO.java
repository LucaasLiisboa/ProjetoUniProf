package com.projeto.uniprof.dao;

import java.util.List;

import com.projeto.uniprof.model.Usuario;

public interface UsuarioDAO {
	public Usuario logar(String email, String senha);
	
	public void cadastrarUsuario(Usuario usuario);
	
	public List<Usuario> listarUsuario();
	
	public List<Usuario> listarAlunosAtivos();
	
	public Usuario getById(int cdUsuario);
	
	public void editarUsuario(Usuario usuario);
	
	public void alterarPermissao(Usuario usuario);
	
	public void inativarUsuario(Usuario usuario);
	
	public void ativarUsuario(Usuario usuario);
	
	public List<Usuario> listarUsuariosGrupo(int cdGrupo);
	
	public List<Usuario> listarUsuariosForaGrupo(int cdGrupo);
}