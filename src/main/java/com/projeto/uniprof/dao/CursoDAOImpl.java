package com.projeto.uniprof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.projeto.uniprof.model.Curso;
import com.projeto.uniprof.model.Grupo;

public class CursoDAOImpl implements CursoDAO {
	private JdbcTemplate jdbcTemplate;
	
	public CursoDAOImpl(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void cadastrarCurso(Curso curso, int cdUsuario) {
		String sql = "INSERT INTO tCurso VALUES(?, ?, 'Ativo', (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = ?));";
		
		jdbcTemplate.update(sql, curso.getNomeCurso(), curso.getDsCurso(), cdUsuario);
		
	}

	@Override
	public List<Curso> listarCurso() {
		String sql = "SELECT * FROM tCurso WHERE statusCurso = 'Ativo';";
		
		List<Curso> listaCurso = jdbcTemplate.query(sql, new RowMapper<Curso>() {
			@Override
			public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
				Curso curso = new Curso();
				
				curso.setCdCurso(rs.getInt("cdCurso"));
				curso.setNomeCurso(rs.getString("nomeCurso"));
				curso.setDsCurso(rs.getString("dsCurso"));
				curso.setStatusCurso(rs.getString("statusCurso"));
				curso.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				
				return curso;
			}			
		});
		
		return listaCurso;
	}

	@Override
	public Curso getById(int cdCurso) {
		String sql = "SELECT * FROM tCurso WHERE cdCurso = " + cdCurso + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Curso>() {
			@Override
			public Curso extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Curso curso = new Curso();
					
					curso.setCdCurso(rs.getInt("cdCurso"));
					curso.setNomeCurso(rs.getString("nomeCurso"));
					curso.setDsCurso(rs.getString("dsCurso"));
					curso.setStatusCurso(rs.getString("statusCurso"));
					curso.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
										
					return curso;
				}
				return null;
			}			
		});
	}

	@Override
	public void editarCurso(Curso curso) {
		String sql = "UPDATE tCurso SET nomeCurso = ?, dsCurso = ? WHERE cdCurso = ?;";
		
		if(curso.getCdCurso() > 0) {
			jdbcTemplate.update(sql, curso.getNomeCurso(), curso.getDsCurso(), curso.getCdCurso());
		}
	}
	
	@Override
	public List<Curso> listarMeusCursos(int cdUsuario) {
		String sql = "SELECT * FROM tCurso WHERE cdUsuario = (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = " + cdUsuario + ");";
		
		List<Curso> listaCurso = jdbcTemplate.query(sql, new RowMapper<Curso>() {
			@Override
			public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
				Curso curso = new Curso();
				
				curso.setCdCurso(rs.getInt("cdCurso"));
				curso.setNomeCurso(rs.getString("nomeCurso"));
				curso.setDsCurso(rs.getString("dsCurso"));
				curso.setStatusCurso(rs.getString("statusCurso"));
				curso.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				
				return curso;
			}			
		});
		
		return listaCurso;
	}
	
	@Override
	public void addGrupoCurso(Curso curso, Grupo grupo) {
		String sql = "INSERT INTO tGrupoCurso VALUES((SELECT cdGrupo FROM tGrupo WHERE cdGrupo = ?), (SELECT cdCurso FROM tCurso WHERE cdCurso = ?));";
		
		jdbcTemplate.update(sql, grupo.getCdGrupo(), curso.getCdCurso());
	}

	@Override
	public void removerGrupoCurso(Curso curso, Grupo grupo) {
		String sql = "DELETE FROM tGrupoCurso WHERE cdGrupo = (SELECT cdGrupo FROM tGrupo WHERE cdGrupo = ?) AND cdCurso = (SELECT cdCurso FROM tCurso WHERE cdCurso = ?);";
		
		jdbcTemplate.update(sql, grupo.getCdGrupo(), curso.getCdCurso());
	}
	
	@Override
	public void atualizarGrupoCurso(Curso curso, Grupo grupo) {
		String sql = "UPDATE tGrupoCurso SET cdGrupo = (SELECT cdGrupo FROM tGrupo WHERE cdGrupo = ?), cdCurso = (SELECT cdCurso FROM tCurso WHERE cdCurso = ?) WHERE cdGrupoCurso = ?;";
		
		jdbcTemplate.update(sql, grupo.getCdGrupo(), curso.getCdCurso(), curso.getCdGrupoCurso());
	}
	
	@Override
	public void inativarCurso(Curso curso) {
		String sql = "UPDATE tCurso SET statusCurso = 'Inativo' WHERE cdCurso = ?;";
		
		jdbcTemplate.update(sql, curso.getCdCurso());		
	}
	
	@Override
	public void ativarCurso(Curso curso) {
		String sql = "UPDATE tCurso SET statusCurso = 'Ativo' WHERE cdCurso = ?;";
		
		jdbcTemplate.update(sql, curso.getCdCurso());		
	}
	
	@Override
	public Curso getGrupoCurso(int cdGrupo, int cdCurso) {
		String sql = "SELECT * FROM tGrupoCurso WHERE cdGrupo = (SELECT cdGrupo FROM tGrupo WHERE cdGrupo = " + cdGrupo + ") AND cdCurso = (SELECT cdCurso FROM tCurso WHERE cdCurso = " + cdCurso + ");";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Curso>() {
			@Override
			public Curso extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Curso curso = new Curso();
					
					curso.setCdCurso(rs.getInt("cdCurso"));
					curso.setGrupo(new GrupoDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdGrupo")));
					
					return curso;
				}
				return null;
			}
		});
	}
	
	@Override
	public List<Curso> getNomeGrupoNomeCurso() {
		String sql = "SELECT gc.cdGrupoCurso, g.cdGrupo, c.* FROM tGrupo g, tGrupoCurso gc, tCurso c WHERE g.cdGrupo = gc.cdGrupo AND gc.cdCurso = c.cdCurso;";
		
		List<Curso> listaCurso = jdbcTemplate.query(sql, new RowMapper<Curso>() {
			@Override
			public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
				Curso curso = new Curso();
				
				curso.setCdGrupoCurso(rs.getInt("cdGrupoCurso"));
				curso.setCdCurso(rs.getInt("cdCurso"));
				curso.setNomeCurso(rs.getString("nomeCurso"));
				curso.setDsCurso(rs.getString("dsCurso"));
				curso.setStatusCurso(rs.getString("statusCurso"));				
				curso.setGrupo(new GrupoDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdGrupo")));
				
				return curso;
			}			
		});
		
		return listaCurso;
	}	
	
	@Override
	public Curso getGrupoCursoById(int cdGrupoCurso) {
		String sql = "SELECT * FROM tGrupoCurso WHERE cdGrupoCurso = " + cdGrupoCurso + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Curso>() {
			@Override
			public Curso extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Curso curso = new Curso();
					
					curso.setCdGrupoCurso(rs.getInt("cdGrupoCurso"));
					curso.setCdCurso(rs.getInt("cdCurso"));
					curso.setGrupo(new GrupoDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdGrupo")));
					
					return curso;
				}
				return null;
			}
		});
	}

	@Override
	public Curso getGruposCurso(int cdGrupo) {
		String sql = "SELECT c.*, gc.cdGrupoCurso FROM tCurso c, tGrupoCurso gc, tGrupo g WHERE c.cdCurso = gc.cdCurso AND gc.cdGrupo = g.cdGrupo AND g.cdGrupo = " + cdGrupo + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Curso>() {
			@Override
			public Curso extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Curso curso = new Curso();
					
					curso.setCdGrupoCurso(rs.getInt("cdGrupoCurso"));
					curso.setCdCurso(rs.getInt("cdCurso"));					
					
					return curso;
				}
				return null;
			}
		});
	}
}