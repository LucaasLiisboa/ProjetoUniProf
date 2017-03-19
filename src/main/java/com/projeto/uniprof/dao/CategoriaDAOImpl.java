package com.projeto.uniprof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.projeto.uniprof.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {
	private JdbcTemplate jdbcTemplate;
	
	public CategoriaDAOImpl(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void cadastrarCategoria(Categoria categoria) {
		String sql = "INSERT INTO tCategoria VALUES(?, 'Ativo');";
		
		jdbcTemplate.update(sql, categoria.getDsCategoria());
	}

	@Override
	public List<Categoria> listarCategoria() {
		String sql = "SELECT * FROM tCategoria;";
		
		List<Categoria> listaCategoria = jdbcTemplate.query(sql, new RowMapper<Categoria>() {
			@Override
			public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
				Categoria categoria = new Categoria();
				
				categoria.setCdCategoria(rs.getInt("cdCategoria"));
				categoria.setDsCategoria(rs.getString("dsCategoria"));
				categoria.setStatusCategoria(rs.getString("statusCategoria"));
				
				return categoria;
			}			
		});
		
		return listaCategoria;
	}
	
	@Override
	public List<Categoria> listarCategoriaAtiva() {
		String sql = "SELECT * FROM tCategoria WHERE statusCategoria = 'Ativo';";
		
		List<Categoria> listaCategoria = jdbcTemplate.query(sql, new RowMapper<Categoria>() {
			@Override
			public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
				Categoria categoria = new Categoria();
				
				categoria.setCdCategoria(rs.getInt("cdCategoria"));
				categoria.setDsCategoria(rs.getString("dsCategoria"));
				categoria.setStatusCategoria(rs.getString("statusCategoria"));
				
				return categoria;
			}			
		});
		
		return listaCategoria;
	}

	@Override
	public Categoria getById(int cdCategoria) {
		String sql = "SELECT * FROM tCategoria WHERE cdCategoria = " + cdCategoria + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Categoria>() {
			@Override
			public Categoria extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Categoria categoria = new Categoria();
					
					categoria.setCdCategoria(rs.getInt("cdCategoria"));
					categoria.setDsCategoria(rs.getString("dsCategoria"));
					categoria.setStatusCategoria(rs.getString("statusCategoria"));
					
					return categoria;
				}
				return null;
			}			
		});
	}

	@Override
	public void editarCategoria(Categoria categoria) {
		String sql = "UPDATE tCategoria SET dsCategoria = ? WHERE cdCategoria = ?;";

		if(categoria.getCdCategoria() > 0) {
			jdbcTemplate.update(sql, categoria.getDsCategoria(),
									 categoria.getCdCategoria());
		}		
	}

	@Override
	public void inativarCategoria(Categoria categoria) {
		String sql = "UPDATE tCategoria SET statusCategoria = 'Inativo' WHERE cdCategoria = ?;";
		
		jdbcTemplate.update(sql, categoria.getCdCategoria());
		
	}
	
	@Override
	public void ativarCategoria(Categoria categoria) {
		String sql = "UPDATE tCategoria SET statusCategoria = 'Ativo' WHERE cdCategoria = ?;";
		
		jdbcTemplate.update(sql, categoria.getCdCategoria());
		
	}
}