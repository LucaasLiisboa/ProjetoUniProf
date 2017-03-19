package com.projeto.uniprof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.projeto.uniprof.model.Questao;

public class QuestaoDAOImpl implements QuestaoDAO {
	private JdbcTemplate jdbcTemplate;

	public QuestaoDAOImpl(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void cadastrarQuestao(Questao questao, int cdUsuario) {		
		String sql = "INSERT INTO tQuestao VALUES (?,?,?,?,?,?,?,'Ativo',(SELECT cdUsuario FROM tUsuario WHERE cdUsuario = ?),(SELECT cdCategoria FROM tCategoria WHERE cdCategoria = ?));";
		
		jdbcTemplate.update(sql, questao.getEnunciadoQuestao(), questao.getAlternativaA(), questao.getAlternativaB(), questao.getAlternativaC(), questao.getAlternativaD(), questao.getAlternativaE(), questao.getAlternativaCorreta(), cdUsuario, questao.getCategoria().getCdCategoria());		
	}
	
	@Override
	public List<Questao> listarQuestao() {		
		//String sql = "SELECT q.* FROM tQuestao q, tCategoria c, tUsuario u WHERE q.cdUsuario = u.cdUsuario AND q.cdCategoria = c.cdCategoria;";
		String sql = "SELECT * FROM tQuestao WHERE statusQuestao != 'Inativo';";
		
		List<Questao> listaQuestao = jdbcTemplate.query(sql, new RowMapper<Questao>() {
			@Override
			public Questao mapRow(ResultSet rs, int rowNum) throws SQLException {
				Questao questao = new Questao();
				
				questao.setCdQuestao(rs.getInt("cdQuestao"));
				questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
				questao.setAlternativaA(rs.getString("alternativaA"));
				questao.setAlternativaB(rs.getString("alternativaB"));
				questao.setAlternativaC(rs.getString("alternativaC"));
				questao.setAlternativaD(rs.getString("alternativaD"));
				questao.setAlternativaE(rs.getString("alternativaE"));
				questao.setAlternativaCorreta(rs.getString("alternativaCorreta"));
				questao.setStatusQuestao(rs.getString("statusQuestao"));
				questao.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				questao.setCategoria(new CategoriaDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdCategoria")));
				
				return questao;
			}			
		});
		
		return listaQuestao;
	}

	@Override
	public Questao getById(int cdQuestao) {
		String sql = "SELECT * FROM tQuestao WHERE cdQuestao = " + cdQuestao + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Questao>() {
			@Override
			public Questao extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Questao questao = new Questao();
					
					questao.setCdQuestao(rs.getInt("cdQuestao"));
					questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
					questao.setAlternativaA(rs.getString("alternativaA"));
					questao.setAlternativaB(rs.getString("alternativaB"));
					questao.setAlternativaC(rs.getString("alternativaC"));
					questao.setAlternativaD(rs.getString("alternativaD"));
					questao.setAlternativaE(rs.getString("alternativaE"));
					questao.setAlternativaCorreta(rs.getString("alternativaCorreta"));
					questao.setStatusQuestao(rs.getString("statusQuestao"));
					questao.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
					questao.setCategoria(new CategoriaDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdCategoria")));
					
					return questao;
				}
				return null;
			}			
		});
	}

	@Override
	public void editarQuestao(Questao questao) {
		String sql = "UPDATE tQuestao SET enunciadoQuestao = ?, alternativaA = ?, alternativaB = ?, alternativaC = ?, alternativaD = ?, alternativaE = ?, alternativaCorreta = ?, cdCategoria = (SELECT cdCategoria FROM tCategoria WHERE cdCategoria = ?) WHERE cdQuestao = ?;";
		
		if(questao.getCdQuestao() > 0) {
			jdbcTemplate.update(sql, questao.getEnunciadoQuestao(),
									 questao.getAlternativaA(),
									 questao.getAlternativaB(),
									 questao.getAlternativaC(),
									 questao.getAlternativaD(),
									 questao.getAlternativaE(),
									 questao.getAlternativaCorreta(),
									 questao.getCategoria().getCdCategoria(),
									 questao.getCdQuestao());
		}		
	}
	
	@Override
	public List<Questao> listarQuestoesQuestionario(int cdQuestionario) {		
		String sql = "SELECT qt.* FROM tQuestao qt, tQuestionarioQuestao qq, tQuestionario q WHERE qt.cdQuestao = qq.cdQuestao AND qq.cdQuestionario = q.cdQuestionario AND qq.cdQuestionario = " + cdQuestionario + ";";
		
		List<Questao> listaQuestao = jdbcTemplate.query(sql, new RowMapper<Questao>() {
			@Override
			public Questao mapRow(ResultSet rs, int rowNum) throws SQLException {
				Questao questao = new Questao();
				
				questao.setCdQuestao(rs.getInt("cdQuestao"));
				questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
				questao.setAlternativaA(rs.getString("alternativaA"));
				questao.setAlternativaB(rs.getString("alternativaB"));
				questao.setAlternativaC(rs.getString("alternativaC"));
				questao.setAlternativaD(rs.getString("alternativaD"));
				questao.setAlternativaE(rs.getString("alternativaE"));
				questao.setAlternativaCorreta(rs.getString("alternativaCorreta"));
				questao.setStatusQuestao(rs.getString("statusQuestao"));
				questao.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				questao.setCategoria(new CategoriaDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdCategoria")));
				
				return questao;
			}			
		});
		
		return listaQuestao;
	}

	@Override
	public List<Questao> listarQuestoesForaQuestionario(int cdQuestionario) {
		String sql = "SELECT * FROM tQuestao WHERE cdQuestao NOT IN (SELECT cdQuestao FROM tQuestionarioQuestao WHERE cdQuestionario = " + cdQuestionario + ") AND statusQuestao = 'Ativo';";
		
		List<Questao> listaQuestao = jdbcTemplate.query(sql, new RowMapper<Questao>() {
			@Override
			public Questao mapRow(ResultSet rs, int rowNum) throws SQLException {
				Questao questao = new Questao();
				
				questao.setCdQuestao(rs.getInt("cdQuestao"));
				questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
				questao.setAlternativaA(rs.getString("alternativaA"));
				questao.setAlternativaB(rs.getString("alternativaB"));
				questao.setAlternativaC(rs.getString("alternativaC"));
				questao.setAlternativaD(rs.getString("alternativaD"));
				questao.setAlternativaE(rs.getString("alternativaE"));
				questao.setAlternativaCorreta(rs.getString("alternativaCorreta"));
				questao.setStatusQuestao(rs.getString("statusQuestao"));
				questao.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				questao.setCategoria(new CategoriaDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdCategoria")));
				
				return questao;
			}			
		});
		
		return listaQuestao;
	}
	
	@Override
	public void inativarQuestao(Questao questao) {
		String sql = "UPDATE tQuestao SET statusQuestao = 'Inativo' WHERE cdQuestao = ?;";
		
		jdbcTemplate.update(sql, questao.getCdQuestao());
	}
	
	@Override
	public void ativarQuestao(Questao questao) {
		String sql = "UPDATE tQuestao SET statusQuestao = 'Ativo' WHERE cdQuestao = ?;";
		
		jdbcTemplate.update(sql, questao.getCdQuestao());		
	}

	@Override
	public List<Questao> listarMinhasQuestoes(int cdUsuario) {
		String sql = "SELECT * FROM tQuestao WHERE cdUsuario = (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = " + cdUsuario + ")";
		
		List<Questao> listaQuestao = jdbcTemplate.query(sql, new RowMapper<Questao>() {
			@Override
			public Questao mapRow(ResultSet rs, int rowNum) throws SQLException {
				Questao questao = new Questao();
				
				questao.setCdQuestao(rs.getInt("cdQuestao"));
				questao.setEnunciadoQuestao(rs.getString("enunciadoQuestao"));
				questao.setAlternativaA(rs.getString("alternativaA"));
				questao.setAlternativaB(rs.getString("alternativaB"));
				questao.setAlternativaC(rs.getString("alternativaC"));
				questao.setAlternativaD(rs.getString("alternativaD"));
				questao.setAlternativaE(rs.getString("alternativaE"));
				questao.setAlternativaCorreta(rs.getString("alternativaCorreta"));
				questao.setStatusQuestao(rs.getString("statusQuestao"));
				questao.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				questao.setCategoria(new CategoriaDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdCategoria")));
				
				return questao;
			}			
		});
		
		return listaQuestao;
	}
}
