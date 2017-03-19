package com.projeto.uniprof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.projeto.uniprof.model.Questao;
import com.projeto.uniprof.model.Questionario;

public class QuestionarioDAOImpl implements QuestionarioDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	QuestaoDAO questaoDAO;
	
	public QuestionarioDAOImpl(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	//public void cadastrarQuestionario(HttpServletRequest request, Questionario questionario) {
	public void cadastrarQuestionario(Questionario questionario, int cdUsuario) {
		String sql = "INSERT INTO tQuestionario VALUES (?, ?, 10, 'Ativo', (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = ?), (SELECT cdCategoria FROM tCategoria WHERE cdCategoria = ?));";
				
		jdbcTemplate.update(sql, questionario.getTituloQuestionario(), questionario.getDsQuestionario(), cdUsuario, questionario.getCategoria().getCdCategoria());
	}
	
	// TODO Implementar as questões no listarQuestionario do Questionário
	@Override
	public List<Questionario> listarQuestionario() {
		String sql = "SELECT * FROM tQuestionario WHERE statusQuestionario = 'Ativo';";
		
		List<Questionario> listaQuestionario = jdbcTemplate.query(sql, new RowMapper<Questionario>() {
			@Override
			public Questionario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Questionario questionario = new Questionario();
				
				questionario.setCdQuestionario(rs.getInt("cdQuestionario"));
				questionario.setTituloQuestionario(rs.getString("tituloQuestionario"));
				questionario.setDsQuestionario(rs.getString("dsQuestionario"));
				questionario.setQtdQuestoes(rs.getInt("qtdQuestoes"));
				questionario.setStatusQuestionario(rs.getString("statusQuestionario"));
				questionario.setQuestoes(new QuestaoDAOImpl(jdbcTemplate.getDataSource()).listarQuestoesQuestionario(rs.getInt("cdQuestionario")));
				questionario.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				questionario.setCategoria(new CategoriaDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdCategoria")));
								
				return questionario;
			}			
		});
		
		return listaQuestionario;
	}
	
	// TODO Implementar as questões no getById do Questionário
	@Override
	public Questionario getById(int cdQuestionario) {
		String sql = "SELECT * FROM tQuestionario WHERE cdQuestionario = " + cdQuestionario;
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Questionario>() {
			@Override
			public Questionario extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Questionario questionario = new Questionario();
					
					questionario.setCdQuestionario(rs.getInt("cdQuestionario"));
					questionario.setTituloQuestionario(rs.getString("tituloQuestionario"));
					questionario.setDsQuestionario(rs.getString("dsQuestionario"));
					questionario.setQtdQuestoes(rs.getInt("qtdQuestoes"));
					questionario.setStatusQuestionario(rs.getString("statusQuestionario"));
					questionario.setQuestoes(new QuestaoDAOImpl(jdbcTemplate.getDataSource()).listarQuestoesQuestionario(rs.getInt("cdQuestionario")));
					questionario.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
					questionario.setCategoria(new CategoriaDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdCategoria")));
					
					return questionario;
				}
				return null;
			}			
		});
	}

	@Override
	public void editarQuestionario(Questionario questionario) {
		String sql = "UPDATE tQuestionario SET tituloQuestionario = ?, dsQuestionario = ?, qtdQuestoes = ?, cdCategoria = (SELECT cdCategoria FROM tCategoria WHERE cdCategoria = ?) WHERE cdQuestionario = ?;";
		if(questionario.getCdQuestionario() > 0) {
			jdbcTemplate.update(sql, questionario.getTituloQuestionario(),
									 questionario.getDsQuestionario(),
									 questionario.getQtdQuestoes(),
									 questionario.getCategoria().getCdCategoria(),
									 questionario.getCdQuestionario());
		}
	}

	@Override
	public void addQuestao(Questionario questionario, Questao questao) {
		String sql = "INSERT INTO tQuestionarioQuestao VALUES((SELECT cdQuestionario FROM tQuestionario WHERE cdQuestionario = ?), (SELECT cdQuestao FROM tQuestao WHERE cdQuestao = ?));";
		
		jdbcTemplate.update(sql, questionario.getCdQuestionario(), questao.getCdQuestao());
	}

	@Override
	public void removerQuestao(Questionario questionario, Questao questao) {
		String sql = "DELETE FROM tQuestionarioQuestao WHERE cdQuestionario = (SELECT cdQuestionario FROM tQuestionario WHERE cdQuestionario = ?) AND cdQuestao = (SELECT cdQuestao FROM tQuestao WHERE cdQuestao = ?);";
		
		jdbcTemplate.update(sql, questionario.getCdQuestionario(), questao.getCdQuestao());
	}

	@Override
	public void inativarQuestionario(Questionario questionario) {
		String sql = "UPDATE tQuestionario SET statusQuestionario = 'Inativo' WHERE cdQuestionario = ?;";
		
		jdbcTemplate.update(sql, questionario.getCdQuestionario());		
	}

	@Override
	public void ativarQuestionario(Questionario questionario) {
		String sql = "UPDATE tQuestionario SET statusQuestionario = 'Ativo' WHERE cdQuestionario = ?;";
		
		jdbcTemplate.update(sql, questionario.getCdQuestionario());		
	}

	@Override
	public List<Questionario> listarMeusQuestionarios(int cdUsuario) {
		String sql = "SELECT * FROM tQuestionario WHERE cdUsuario = (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = " + cdUsuario + ")";
		
		List<Questionario> listaQuestionario = jdbcTemplate.query(sql, new RowMapper<Questionario>() {
			@Override
			public Questionario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Questionario questionario = new Questionario();
				
				questionario.setCdQuestionario(rs.getInt("cdQuestionario"));
				questionario.setTituloQuestionario(rs.getString("tituloQuestionario"));
				questionario.setDsQuestionario(rs.getString("dsQuestionario"));
				questionario.setQtdQuestoes(rs.getInt("qtdQuestoes"));
				questionario.setStatusQuestionario(rs.getString("statusQuestionario"));
				questionario.setQuestoes(new QuestaoDAOImpl(jdbcTemplate.getDataSource()).listarQuestoesQuestionario(rs.getInt("cdQuestionario")));
				questionario.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				questionario.setCategoria(new CategoriaDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdCategoria")));
								
				return questionario;
			}			
		});
		
		return listaQuestionario;
	}
	
	@Override
	public List<Questionario> listarQuestionariosForaGrupoCurso(int cdGrupoCurso) {
		String sql = "SELECT * FROM tQuestionario WHERE cdQuestionario NOT IN (SELECT cdQuestionario FROM tGrupoCursoQuestionario WHERE cdGrupoCurso = " + cdGrupoCurso + ");";
		
		List<Questionario> listaQuestionario = jdbcTemplate.query(sql, new RowMapper<Questionario>() {
			@Override
			public Questionario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Questionario questionario = new Questionario();
				
				questionario.setCdQuestionario(rs.getInt("cdQuestionario"));
				questionario.setTituloQuestionario(rs.getString("tituloQuestionario"));
				questionario.setDsQuestionario(rs.getString("dsQuestionario"));
				questionario.setQtdQuestoes(rs.getInt("qtdQuestoes"));
				questionario.setStatusQuestionario(rs.getString("statusQuestionario"));
				questionario.setQuestoes(new QuestaoDAOImpl(jdbcTemplate.getDataSource()).listarQuestoesQuestionario(rs.getInt("cdQuestionario")));
				questionario.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				questionario.setCategoria(new CategoriaDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdCategoria")));
								
				return questionario;
			}			
		});
		
		return listaQuestionario;
	}
	
	@Override
	public List<Questionario> listarQuestionariosDentroGrupoCurso(int cdGrupoCurso) {
		String sql = "SELECT * FROM tQuestionario WHERE cdQuestionario IN (SELECT cdQuestionario FROM tGrupoCursoQuestionario WHERE cdGrupoCurso = " + cdGrupoCurso + ");";
		
		List<Questionario> listaQuestionario = jdbcTemplate.query(sql, new RowMapper<Questionario>() {
			@Override
			public Questionario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Questionario questionario = new Questionario();
				
				questionario.setCdQuestionario(rs.getInt("cdQuestionario"));
				questionario.setTituloQuestionario(rs.getString("tituloQuestionario"));
				questionario.setDsQuestionario(rs.getString("dsQuestionario"));
				questionario.setQtdQuestoes(rs.getInt("qtdQuestoes"));
				questionario.setStatusQuestionario(rs.getString("statusQuestionario"));
				questionario.setQuestoes(new QuestaoDAOImpl(jdbcTemplate.getDataSource()).listarQuestoesQuestionario(rs.getInt("cdQuestionario")));
				questionario.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				questionario.setCategoria(new CategoriaDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdCategoria")));
								
				return questionario;
			}			
		});
		
		return listaQuestionario;
	}
	
	@Override
	public List<Questionario> listarQuestionariosDentroGrupoCursoNaoRespondido(int cdGrupoCurso) {
		// TODO Incluir a cláusula que verifica se o usuário já respondeu o questionário, caso sim, o questionário não deverá ser exibido.
		String sql = "SELECT * FROM tQuestionario WHERE cdQuestionario IN (SELECT cdQuestionario FROM tGrupoCursoQuestionario WHERE cdGrupoCurso = " + cdGrupoCurso + ") AND "
				+ "cdQuestionario NOT IN (SELECT qq.cdQuestionario FROM tQuestionarioQuestao qq, tResposta r WHERE qq.cdQuestionarioQuestao = r.cdQuestionarioQuestao);";
		
		List<Questionario> listaQuestionario = jdbcTemplate.query(sql, new RowMapper<Questionario>() {
			@Override
			public Questionario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Questionario questionario = new Questionario();
				
				questionario.setCdQuestionario(rs.getInt("cdQuestionario"));
				questionario.setTituloQuestionario(rs.getString("tituloQuestionario"));
				questionario.setDsQuestionario(rs.getString("dsQuestionario"));
				questionario.setQtdQuestoes(rs.getInt("qtdQuestoes"));
				questionario.setStatusQuestionario(rs.getString("statusQuestionario"));
				questionario.setQuestoes(new QuestaoDAOImpl(jdbcTemplate.getDataSource()).listarQuestoesQuestionario(rs.getInt("cdQuestionario")));
				questionario.setUsuario(new UsuarioDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdUsuario")));
				questionario.setCategoria(new CategoriaDAOImpl(jdbcTemplate.getDataSource()).getById(rs.getInt("cdCategoria")));
								
				return questionario;
			}			
		});
		
		return listaQuestionario;
	}
}
