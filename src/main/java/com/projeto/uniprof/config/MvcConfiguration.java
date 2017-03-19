package com.projeto.uniprof.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.projeto.uniprof.dao.CategoriaDAO;
import com.projeto.uniprof.dao.CategoriaDAOImpl;
import com.projeto.uniprof.dao.CursoDAO;
import com.projeto.uniprof.dao.CursoDAOImpl;
import com.projeto.uniprof.dao.GrupoDAO;
import com.projeto.uniprof.dao.GrupoDAOImpl;
import com.projeto.uniprof.dao.QuestaoDAO;
import com.projeto.uniprof.dao.QuestaoDAOImpl;
import com.projeto.uniprof.dao.QuestionarioDAO;
import com.projeto.uniprof.dao.QuestionarioDAOImpl;
import com.projeto.uniprof.dao.RespostaDAO;
import com.projeto.uniprof.dao.RespostaDAOImpl;
import com.projeto.uniprof.dao.UsuarioDAO;
import com.projeto.uniprof.dao.UsuarioDAOImpl;



@Configuration
@ComponentScan(basePackages="com.projeto.uniprof")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=dbUniProf;");
		dataSource.setUsername("sa");
		dataSource.setPassword("123456");
		
		return dataSource;
	}
		
	@Bean
	public UsuarioDAO getUsuarioDao(){
		return new UsuarioDAOImpl(getDataSource());
	}
	
	@Bean
	public QuestaoDAO getQuestaoDao(){
		return new QuestaoDAOImpl(getDataSource());
	}
	
	@Bean
	public CategoriaDAO getCategoriaDao(){
		return new CategoriaDAOImpl(getDataSource());
	}
	
	@Bean
	public GrupoDAO getGrupoDao(){
		return new GrupoDAOImpl(getDataSource());
	}
	
	@Bean
	public CursoDAO getCursoDao(){
		return new CursoDAOImpl(getDataSource());
	}
	
	@Bean
	public QuestionarioDAO getQuestionarioDAO(){
		return new QuestionarioDAOImpl(getDataSource());
	}
	
	@Bean RespostaDAO getRespostaDAO() {
		return new RespostaDAOImpl(getDataSource());
	}
}