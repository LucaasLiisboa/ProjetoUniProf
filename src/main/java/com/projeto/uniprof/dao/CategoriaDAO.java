package com.projeto.uniprof.dao;

import java.util.List;

import com.projeto.uniprof.model.Categoria;

public interface CategoriaDAO {
	public void cadastrarCategoria(Categoria categoria);
	
	public List<Categoria> listarCategoria();
	
	public List<Categoria> listarCategoriaAtiva();
	
	public Categoria getById(int cdCategoria);
	
	public void editarCategoria(Categoria categoria);
	
	public void inativarCategoria(Categoria categoria);
	
	public void ativarCategoria(Categoria categoria);
}
