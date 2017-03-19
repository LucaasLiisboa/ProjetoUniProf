package com.projeto.uniprof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.projeto.uniprof.model.Questionario;
import com.projeto.uniprof.model.Resposta;
import com.projeto.uniprof.model.Usuario;

public class RespostaDAOImpl implements RespostaDAO {
	private JdbcTemplate jdbcTemplate;
	
	public RespostaDAOImpl(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}	

	@Override
	public void inscreverGrupoQuestionario(int cdGrupoCurso, Questionario questionario, Usuario usuario) {
		String sql = "INSERT INTO tGrupoCursoQuestionario VALUES("
						+ "(SELECT cdGrupoCurso FROM tGrupoCurso WHERE cdGrupoCurso = ?), "
						+ "(SELECT cdQuestionario FROM tQuestionario WHERE cdQuestionario = ?), "
						+ "(SELECT cdUsuario FROM tUsuario WHERE cdUsuario = ?), "
						+ "'Ativo');";
		
		jdbcTemplate.update(sql, cdGrupoCurso, questionario.getCdQuestionario(), usuario.getCdUsuario());
	}
	
	@Override
	public void removeGrupoQuestionario(int cdGrupoCurso, Questionario questionario, Usuario usuario) {
		String sql = "DELETE FROM tGrupoCursoQuestionario WHERE "
						+ "cdGrupoCurso = (SELECT cdGrupoCurso FROM tGrupoCurso WHERE cdGrupoCurso = ?) AND "
						+ "cdQuestionario = (SELECT cdQuestionario FROM tQuestionario WHERE cdQuestionario = ?) AND "
						+ "cdUsuario = (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = ?);";
		
		jdbcTemplate.update(sql, cdGrupoCurso, questionario.getCdQuestionario(), usuario.getCdUsuario());
	}

	@Override
	public Resposta getQuestionarioQuestao(int cdQuestionario, int cdQuestao) {
		String sql = "SELECT cdQuestionarioQuestao FROM tQuestionarioQuestao WHERE cdQuestionario = " + cdQuestionario + " AND cdQuestao = " + cdQuestao + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Resposta>() {
			@Override
			public Resposta extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Resposta resposta = new Resposta();
					
					resposta.setCdQuestionarioQuestao(rs.getInt("cdQuestionarioQuestao"));
														
					return resposta;
				}
				return null;
			}
		});
	}
	
	@Override
	public void responderQuestionario(int cdQuestionarioQuestao, int cdUsuario, String alternativaSelecionada) {
		String sql = "INSERT INTO tResposta VALUES((SELECT cdQuestionarioQuestao FROM tQuestionarioQuestao WHERE cdQuestionarioQuestao = ?), (SELECT cdUsuario FROM tUsuario WHERE cdUsuario = ?), ?);";		
		
		jdbcTemplate.update(sql, cdQuestionarioQuestao, cdUsuario, alternativaSelecionada);
	}

	@Override
	public List<Resposta> listarRespostas(Questionario questionario) {
		String sql = "SELECT * FROM tResposta;";
		
		
		List<Resposta> listaRespostas = jdbcTemplate.query(sql, new RowMapper<Resposta>() {
			@Override
			public Resposta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resposta resposta = new Resposta();
				
				resposta.setQuestionario(new QuestionarioDAOImpl(jdbcTemplate.getDataSource()).getById(questionario.getCdQuestionario()));
				resposta.setAlternativaSelecionada(rs.getString("alternativaSelecionada"));
								
				return resposta;
			}			
		});
		
		return listaRespostas;
	}
}