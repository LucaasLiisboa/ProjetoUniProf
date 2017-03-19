package com.projeto.uniprof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.projeto.uniprof.model.Grupo;
import com.projeto.uniprof.model.Usuario;

public class GrupoDAOImpl implements GrupoDAO {
	private JdbcTemplate jdbcTemplate;
	
	public GrupoDAOImpl(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void cadastrarGrupo(Grupo grupo, int cdUsuario) {
		String sql = "INSERT INTO tGrupo VALUES(?, ?, 'Ativo', (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = ?));";
		
		jdbcTemplate.update(sql, grupo.getNomeGrupo(), grupo.getDsGrupo(), cdUsuario);		
	}

	@Override
	public List<Grupo> listarGrupo() {
		String sql = "SELECT * FROM tGrupo WHERE statusGrupo = 'Ativo';";
		
		List<Grupo> listaGrupo = jdbcTemplate.query(sql, new RowMapper<Grupo>() {
			@Override
			public Grupo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Grupo grupo = new Grupo();
				
				grupo.setCdGrupo(rs.getInt("cdGrupo"));
				grupo.setNomeGrupo(rs.getString("nomeGrupo"));
				grupo.setDsGrupo(rs.getString("dsGrupo"));
				grupo.setStatusGrupo(rs.getString("statusGrupo"));
				grupo.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				
				return grupo;
			}			
		});
		
		return listaGrupo;
	}

	@Override
	public Grupo getById(int cdGrupo) {
		String sql = "SELECT * FROM tGrupo WHERE cdGrupo = " + cdGrupo + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Grupo>() {
			@Override
			public Grupo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Grupo grupo = new Grupo();
					
					grupo.setCdGrupo(rs.getInt("cdGrupo"));
					grupo.setNomeGrupo(rs.getString("nomeGrupo"));
					grupo.setDsGrupo(rs.getString("dsGrupo"));
					grupo.setStatusGrupo(rs.getString("statusGrupo"));
					grupo.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
					
					return grupo;
				}
				return null;
			}			
		});
	}

	@Override
	public void editarGrupo(Grupo grupo) {
		String sql = "UPDATE tGrupo SET nomeGrupo = ?, dsGrupo = ? WHERE cdGrupo = ?;";

		if(grupo.getCdGrupo() > 0) {
			jdbcTemplate.update(sql, grupo.getNomeGrupo(), grupo.getDsGrupo(), grupo.getCdGrupo());
		}
	}
	
	@Override
	public List<Grupo> listarMeusGrupos(int cdUsuario) {
		String sql = "SELECT * FROM tGrupo WHERE cdUsuario = (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = " + cdUsuario + ");";
		
		List<Grupo> listaGrupo = jdbcTemplate.query(sql, new RowMapper<Grupo>() {
			@Override
			public Grupo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Grupo grupo = new Grupo();
				
				grupo.setCdGrupo(rs.getInt("cdGrupo"));
				grupo.setNomeGrupo(rs.getString("nomeGrupo"));
				grupo.setDsGrupo(rs.getString("dsGrupo"));
				grupo.setStatusGrupo(rs.getString("statusGrupo"));
				grupo.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				
				return grupo;
			}			
		});
		
		return listaGrupo;
	}
	
	@Override
	public void addUsuario(Grupo grupo, Usuario usuario) {
		String sql = "INSERT INTO tGrupoUsuario VALUES((SELECT cdGrupo FROM tGrupo WHERE cdGrupo = ?), (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = ?))";
				
		jdbcTemplate.update(sql, grupo.getCdGrupo(), usuario.getCdUsuario());
	}
	
	@Override
	public void removerUsuario(Grupo grupo, Usuario usuario) {
		String sql = "DELETE FROM tGrupoUsuario WHERE cdGrupo = (SELECT cdGrupo FROM tGrupo WHERE cdGrupo = ?) AND cdUsuario = (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = ?);";
		
		jdbcTemplate.update(sql, grupo.getCdGrupo(), usuario.getCdUsuario());
	}
	
	@Override
	public void inativarGrupo(Grupo grupo) {
		String sql = "UPDATE tGrupo SET statusGrupo = 'Inativo' WHERE cdGrupo = ?;";
		
		jdbcTemplate.update(sql, grupo.getCdGrupo());		
	}
	
	@Override
	public void ativarGrupo(Grupo grupo) {
		String sql = "UPDATE tGrupo SET statusGrupo = 'Ativo' WHERE cdGrupo = ?;";
		
		jdbcTemplate.update(sql, grupo.getCdGrupo());		
	}

	@Override
	public Grupo getGrupoUsuario(int cdGrupo, int cdUsuario) {
		String sql = "SELECT * FROM tGrupoUsuario WHERE cdGrupo = (SELECT cdGrupo FROM tGrupo WHERE cdGrupo = " + cdGrupo + ") AND cdUsuario = (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = " + cdUsuario + ");";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Grupo>() {
			@Override
			public Grupo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Grupo grupo = new Grupo();
					
					grupo.setCdGrupo(rs.getInt("cdGrupo"));
					grupo.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
					
					return grupo;
				}
				return null;
			}
		});
	}
	
	@Override
	public List<Grupo> listarGruposCurso() {
		String sql = "SELECT g.* FROM tCurso c, tGrupoCurso gc, tGrupo g WHERE c.cdCurso = gc.cdCurso AND gc.cdGrupo = g.cdGrupo;";
		
		List<Grupo> listaGrupo = jdbcTemplate.query(sql, new RowMapper<Grupo>() {
			@Override
			public Grupo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Grupo grupo = new Grupo();
				
				grupo.setCdGrupo(rs.getInt("cdGrupo"));
				grupo.setNomeGrupo(rs.getString("nomeGrupo"));
				grupo.setDsGrupo(rs.getString("dsGrupo"));
				grupo.setStatusGrupo(rs.getString("statusGrupo"));
				grupo.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				
				return grupo;
			}			
		});
		
		return listaGrupo;
	}

	@Override
	public List<Grupo> listarGruposForaCurso(int cdCurso) {
		String sql = "SELECT * FROM tGrupo WHERE cdGrupo NOT IN (SELECT cdGrupo FROM tGrupoCurso WHERE cdCurso = " + cdCurso + ");";
		
		List<Grupo> listaGrupo = jdbcTemplate.query(sql, new RowMapper<Grupo>() {
			@Override
			public Grupo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Grupo grupo = new Grupo();
				
				grupo.setCdGrupo(rs.getInt("cdGrupo"));
				grupo.setNomeGrupo(rs.getString("nomeGrupo"));
				grupo.setDsGrupo(rs.getString("dsGrupo"));
				grupo.setStatusGrupo(rs.getString("statusGrupo"));
				grupo.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				
				return grupo;
			}			
		});
		
		return listaGrupo;
	}

	@Override
	public Grupo getGruposUsuario(int cdUsuario) {
		String sql = "SELECT g.* FROM tUsuario u, tGrupoUsuario gu, tGrupo g WHERE u.cdUsuario = gu.cdUsuario AND gu.cdGrupo = g.cdGrupo AND u.cdUsuario = " + cdUsuario + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Grupo>() {
			@Override
			public Grupo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Grupo grupo = new Grupo();
					
					grupo.setCdGrupo(rs.getInt("cdGrupo"));
					grupo.setNomeGrupo(rs.getString("nomeGrupo"));
					grupo.setDsGrupo(rs.getString("dsGrupo"));
					grupo.setStatusGrupo(rs.getString("statusGrupo"));
					grupo.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
					
					return grupo;
				}
				return null;
			}			
		});
	}
}