package com.projeto.uniprof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.projeto.uniprof.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	private JdbcTemplate jdbcTemplate;

	public UsuarioDAOImpl(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
		
	@Override
	public void cadastrarUsuario(Usuario usuario) {
		String sql = "INSERT INTO tUsuario VALUES(?, ?, ?, ?, NULL, NULL, ?, 'Ativo', 'Aluno');";
		
		jdbcTemplate.update(sql, 
						usuario.getNomeUsuario(), 
						usuario.getSobrenomeUsuario(), 
						usuario.getEmailUsuario(), 
						usuario.getSenhaUsuario(),
						usuario.gerarToken());
	}
	
	//Pesquisando os usuários e retornando todos eles (list) para serem exibidos	
	@Override
	public List<Usuario> listarUsuario() {
		String sql = "SELECT * FROM tUsuario;";
		
		List<Usuario> listaUsuario = jdbcTemplate.query(sql, new RowMapper<Usuario>() {
			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Usuario usuario = new Usuario();
				
				usuario.setCdUsuario(rs.getInt("cdUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setSobrenomeUsuario(rs.getString("sobrenomeUsuario"));
				usuario.setEmailUsuario(rs.getString("emailUsuario"));
				usuario.setSenhaUsuario(rs.getString("senhaUsuario"));
				usuario.setPerguntaSeguranca(rs.getString("perguntaSeguranca"));
				usuario.setRespostaSeguranca(rs.getString("respostaSeguranca"));
				usuario.setTokenUsuario(rs.getString("tokenUsuario"));
				usuario.setStatusUsuario(rs.getString("statusUsuario"));				
				usuario.setDsPermissao(rs.getString("dsPermissao"));
								
				return usuario;
			}			
		});
		
		return listaUsuario;
	}
	
	@Override
	public List<Usuario> listarAlunosAtivos() {
		String sql = "SELECT * FROM tUsuario WHERE statusUsuario = 'Ativo' AND dsPermissao = 'Aluno';";
		
		List<Usuario> listaUsuario = jdbcTemplate.query(sql, new RowMapper<Usuario>() {
			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Usuario usuario = new Usuario();
				
				usuario.setCdUsuario(rs.getInt("cdUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setSobrenomeUsuario(rs.getString("sobrenomeUsuario"));
				usuario.setEmailUsuario(rs.getString("emailUsuario"));
				usuario.setSenhaUsuario(rs.getString("senhaUsuario"));
				usuario.setPerguntaSeguranca(rs.getString("perguntaSeguranca"));
				usuario.setRespostaSeguranca(rs.getString("respostaSeguranca"));
				usuario.setTokenUsuario(rs.getString("tokenUsuario"));
				usuario.setStatusUsuario(rs.getString("statusUsuario"));				
				usuario.setDsPermissao(rs.getString("dsPermissao"));
								
				return usuario;
			}			
		});
		
		return listaUsuario;
	}

	//Pesquisando Usuário através de seu respectivo ID
	@Override
	public Usuario getById(int cdUsuario) {
		String sql = "SELECT * FROM tUsuario WHERE cdUsuario = " + cdUsuario + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Usuario>() {
			@Override
			public Usuario extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					Usuario usuario = new Usuario();
					
					usuario.setCdUsuario(rs.getInt("cdUsuario"));
					usuario.setNomeUsuario(rs.getString("nomeUsuario"));
					usuario.setSobrenomeUsuario(rs.getString("sobrenomeUsuario"));
					usuario.setEmailUsuario(rs.getString("emailUsuario"));
					usuario.setSenhaUsuario(rs.getString("senhaUsuario"));
					usuario.setPerguntaSeguranca(rs.getString("perguntaSeguranca"));
					usuario.setRespostaSeguranca(rs.getString("respostaSeguranca"));
					usuario.setTokenUsuario(rs.getString("tokenUsuario"));
					usuario.setStatusUsuario(rs.getString("statusUsuario"));				
					usuario.setDsPermissao(rs.getString("dsPermissao"));
					
					return usuario;
				}
				return null;
			}
		});
	}

	@Override
	public void editarUsuario(Usuario usuario) {
		String sql = "UPDATE tUsuario SET nomeUsuario = ?, sobrenomeUsuario = ?, perguntaSeguranca = ?, respostaSeguranca = ? WHERE cdUsuario = ?;";

		if(usuario.getCdUsuario() > 0) {
			jdbcTemplate.update(sql, usuario.getNomeUsuario(),
									 usuario.getSobrenomeUsuario(),
									 usuario.getPerguntaSeguranca(),
									 usuario.getRespostaSeguranca(),
									 usuario.getCdUsuario());
		}
	}

	@Override
	public void alterarPermissao(Usuario usuario) {
		String sql = "UPDATE tUsuario SET dsPermissao = ? WHERE cdUsuario = ?;";

		if(usuario.getCdUsuario() > 0) {
			jdbcTemplate.update(sql, usuario.getDsPermissao(),									 
									 usuario.getCdUsuario());
		}
	}

	@Override
	public void inativarUsuario(Usuario usuario) {
		String sql = "UPDATE tUsuario SET statusUsuario = 'Inativo' WHERE cdUsuario = ?;";

		if(usuario.getCdUsuario() > 0) {
			jdbcTemplate.update(sql, usuario.getCdUsuario());
		}
	}
	
	@Override
	public void ativarUsuario(Usuario usuario) {
		String sql = "UPDATE tUsuario SET statusUsuario = 'Ativo' WHERE cdUsuario = ?;";

		if(usuario.getCdUsuario() > 0) {
			jdbcTemplate.update(sql, usuario.getCdUsuario());
		}
	}

	@Override
	public Usuario logar(String email, String senha) {
		String sql = "SELECT * FROM tUsuario WHERE emailUsuario = '" + email + "' AND senhaUsuario = '" + senha + "'";		

		return jdbcTemplate.query(sql, new ResultSetExtractor<Usuario>() {
			@Override
			public Usuario extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Usuario usuario = getById(rs.getInt("cdUsuario"));					
					
					return usuario;
				}

				return null;
			}
		});
	}

	@Override
	public List<Usuario> listarUsuariosGrupo(int cdGrupo) {
		String sql = "SELECT u.* FROM tGrupo g, tGrupoUsuario gu, tUsuario u WHERE u.cdUsuario = gu.cdUsuario AND gu.cdGrupo = g.cdGrupo AND gu.cdGrupo = " + cdGrupo + ";";
		
		List<Usuario> listaUsuario = jdbcTemplate.query(sql, new RowMapper<Usuario>() {
			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Usuario usuario = new Usuario();
				
				usuario.setCdUsuario(rs.getInt("cdUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setSobrenomeUsuario(rs.getString("sobrenomeUsuario"));
				usuario.setEmailUsuario(rs.getString("emailUsuario"));
				usuario.setSenhaUsuario(rs.getString("senhaUsuario"));
				usuario.setPerguntaSeguranca(rs.getString("perguntaSeguranca"));
				usuario.setRespostaSeguranca(rs.getString("respostaSeguranca"));
				usuario.setTokenUsuario(rs.getString("tokenUsuario"));
				usuario.setStatusUsuario(rs.getString("statusUsuario"));				
				usuario.setDsPermissao(rs.getString("dsPermissao"));
								
				return usuario;
			}			
		});
		
		return listaUsuario;
	}

	@Override
	public List<Usuario> listarUsuariosForaGrupo(int cdGrupo) {
		String sql = "SELECT * FROM tUsuario WHERE cdUsuario NOT IN (SELECT cdUsuario FROM tGrupoUsuario WHERE cdGrupo = " + cdGrupo + ") AND dsPermissao = 'Aluno';";
		
		List<Usuario> listaUsuario = jdbcTemplate.query(sql, new RowMapper<Usuario>() {
			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Usuario usuario = new Usuario();
				
				usuario.setCdUsuario(rs.getInt("cdUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setSobrenomeUsuario(rs.getString("sobrenomeUsuario"));
				usuario.setEmailUsuario(rs.getString("emailUsuario"));
				usuario.setSenhaUsuario(rs.getString("senhaUsuario"));
				usuario.setPerguntaSeguranca(rs.getString("perguntaSeguranca"));
				usuario.setRespostaSeguranca(rs.getString("respostaSeguranca"));
				usuario.setTokenUsuario(rs.getString("tokenUsuario"));
				usuario.setStatusUsuario(rs.getString("statusUsuario"));				
				usuario.setDsPermissao(rs.getString("dsPermissao"));
								
				return usuario;
			}			
		});
		
		return listaUsuario;
	}
}