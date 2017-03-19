USE dbUniProf;
GO

--Dropar as FKs
ALTER TABLE tGrupoUsuario DROP CONSTRAINT fk_001;
ALTER TABLE tGrupoUsuario DROP CONSTRAINT fk_002;
ALTER TABLE tGrupoCurso DROP CONSTRAINT fk_003;
ALTER TABLE tGrupoCurso DROP CONSTRAINT fk_004;
ALTER TABLE tGrupoCursoQuestionario DROP CONSTRAINT fk_005;
ALTER TABLE tGrupoCursoQuestionario DROP CONSTRAINT fk_006;
ALTER TABLE tGrupoCursoQuestionario DROP CONSTRAINT fk_007;
ALTER TABLE tQuestionario DROP CONSTRAINT fk_008;
ALTER TABLE tQuestionario DROP CONSTRAINT fk_009;
ALTER TABLE tQuestao DROP CONSTRAINT fk_010;
ALTER TABLE tQuestao DROP CONSTRAINT fk_011;
ALTER TABLE tQuestionarioQuestao DROP CONSTRAINT fk_012;
ALTER TABLE tQuestionarioQuestao DROP CONSTRAINT fk_013;
ALTER TABLE tResposta DROP CONSTRAINT fk_014;
ALTER TABLE tResposta DROP CONSTRAINT fk_015;
ALTER TABLE tGrupo DROP CONSTRAINT fk_016;
ALTER TABLE tCurso DROP CONSTRAINT fk_017;

--Dropar as Tabelas
DROP TABLE tUsuario;
DROP TABLE tGrupoUsuario;
DROP TABLE tGrupo;
DROP TABLE tGrupoCurso;
DROP TABLE tCurso;
DROP TABLE tGrupoCursoQuestionario;
DROP TABLE tQuestionario;
DROP TABLE tQuestao;
DROP TABLE tCategoria;
DROP TABLE tQuestionarioQuestao;
DROP TABLE tResposta;

CREATE TABLE tUsuario (
	cdUsuario INTEGER IDENTITY(1,1) NOT NULL,
	nomeUsuario VARCHAR(30) NOT NULL,
	sobrenomeUsuario VARCHAR(30) NOT NULL,
	emailUsuario VARCHAR(50) NOT NULL,
	senhaUsuario VARCHAR(30) NOT NULL,
	perguntaSeguranca VARCHAR(100) NULL,
	respostaSeguranca VARCHAR(100) NULL,
	tokenUsuario VARCHAR(6) NULL,	
	statusUsuario VARCHAR(7) DEFAULT 'Ativo' NOT NULL,
	dsPermissao VARCHAR(15) NOT NULL,
	CONSTRAINT pk_usuario PRIMARY KEY (cdUsuario)
);
GO

CREATE TABLE tGrupoUsuario (
	cdGrupo INTEGER NOT NULL,
	cdUsuario INTEGER NOT NULL,
	CONSTRAINT pk_grupoUsuario PRIMARY KEY (cdGrupo, cdUsuario)
);
GO

CREATE TABLE tGrupo (
	cdGrupo INTEGER IDENTITY(1,1) NOT NULL,
	nomeGrupo VARCHAR(30) NOT NULL,
	dsGrupo VARCHAR(100) NULL,
	statusGrupo VARCHAR(7) DEFAULT 'Ativo' NOT NULL,
	cdUsuario INTEGER NOT NULL,
	CONSTRAINT pk_grupo PRIMARY KEY (cdGrupo)
);
GO

CREATE TABLE tGrupoCurso (
	cdGrupoCurso INTEGER IDENTITY(1,1) NOT NULL,
	cdGrupo INTEGER NOT NULL,
	cdCurso INTEGER NOT NULL,	
	CONSTRAINT pk_grupoCurso PRIMARY KEY (cdGrupoCurso)
);
GO

CREATE TABLE tCurso (
	cdCurso INTEGER IDENTITY(1,1) NOT NULL,
	nomeCurso VARCHAR(50) NOT NULL,
	dsCurso VARCHAR(100) NULL,
	statusCurso VARCHAR(7) DEFAULT 'Ativo' NOT NULL,
	cdUsuario INTEGER NOT NULL,
	CONSTRAINT pk_curso PRIMARY KEY (cdCurso)
);
GO

CREATE TABLE tGrupoCursoQuestionario (
	cdGrupoCurso INTEGER NOT NULL,
	cdQuestionario INTEGER NOT NULL,
	cdUsuario INTEGER NOT NULL,
	statusQuestionarioGrupoCurso VARCHAR(7) DEFAULT 'Ativo' NOT NULL,
	CONSTRAINT pk_grupoQuestionario PRIMARY KEY (cdGrupoCurso, cdQuestionario)
);
GO

CREATE TABLE tQuestionario (
	cdQuestionario INTEGER IDENTITY(1,1) NOT NULL,
	tituloQuestionario VARCHAR(100) NOT NULL,
	dsQuestionario VARCHAR(200) NULL,
	qtdQuestoes INTEGER NOT NULL,
	statusQuestionario VARCHAR(7) DEFAULT 'Ativo' NOT NULL,
	cdUsuario INTEGER NOT NULL,
	cdCategoria INTEGER NOT NULL,	
	CONSTRAINT pk_questionario PRIMARY KEY (cdQuestionario)
);
GO

CREATE TABLE tQuestao (
	cdQuestao INTEGER IDENTITY(1,1) NOT NULL,	
	enunciadoQuestao VARCHAR(250) NOT NULL,
	alternativaA VARCHAR(250) NOT NULL,
	alternativaB VARCHAR(250) NOT NULL,
	alternativaC VARCHAR(250) NOT NULL,
	alternativaD VARCHAR(250) NOT NULL,
	alternativaE VARCHAR(250) NOT NULL,
	alternativaCorreta CHAR(1) NOT NULL,
	statusQuestao VARCHAR(7) DEFAULT 'Ativo' NOT NULL,
	cdUsuario INTEGER NOT NULL,
	cdCategoria INTEGER NOT NULL,
	CONSTRAINT pk_questao PRIMARY KEY (cdQuestao)
);
GO

CREATE TABLE tCategoria (
	cdCategoria INTEGER IDENTITY(1,1) NOT NULL,
	dsCategoria VARCHAR(40) NOT NULL,
	statusCategoria VARCHAR(7) DEFAULT 'Ativo' NOT NULL,
	CONSTRAINT pk_categoria PRIMARY KEY (cdCategoria)
);
GO

CREATE TABLE tQuestionarioQuestao (
	cdQuestionarioQuestao INTEGER IDENTITY(1,1) NOT NULL,
	cdQuestionario INTEGER NOT NULL,
	cdQuestao INTEGER NOT NULL,
	CONSTRAINT pk_questionarioQuestao PRIMARY KEY (cdQuestionarioQuestao)
);
GO

CREATE TABLE tResposta (
	cdQuestionarioQuestao INTEGER NOT NULL,
	cdUsuario INTEGER NOT NULL,
	alternativaSelecionada CHAR(1) NULL,	
	CONSTRAINT pk_resposta PRIMARY KEY (cdQuestionarioQuestao, cdUsuario)
);
GO

ALTER TABLE tGrupoUsuario ADD CONSTRAINT fk_001 FOREIGN KEY(cdUsuario) REFERENCES tUsuario(cdUsuario);

ALTER TABLE tGrupoUsuario ADD CONSTRAINT fk_002 FOREIGN KEY(cdGrupo) REFERENCES tGrupo(cdGrupo);

ALTER TABLE tGrupoCurso ADD CONSTRAINT fk_003 FOREIGN KEY(cdGrupo) REFERENCES tGrupo(cdGrupo);

ALTER TABLE tGrupoCurso ADD CONSTRAINT fk_004 FOREIGN KEY(cdCurso) REFERENCES tCurso(cdCurso);

ALTER TABLE tGrupoCursoQuestionario ADD CONSTRAINT fk_005 FOREIGN KEY(cdGrupoCurso) REFERENCES tGrupoCurso(cdGrupoCurso);

ALTER TABLE tGrupoCursoQuestionario ADD CONSTRAINT fk_006 FOREIGN KEY(cdQuestionario) REFERENCES tQuestionario(cdQuestionario);

ALTER TABLE tGrupoCursoQuestionario ADD CONSTRAINT fk_007 FOREIGN KEY(cdUsuario) REFERENCES tUsuario(cdUsuario);

ALTER TABLE tQuestionario ADD CONSTRAINT fk_008 FOREIGN KEY(cdUsuario) REFERENCES tUsuario(cdUsuario);

ALTER TABLE tQuestionario ADD CONSTRAINT fk_009 FOREIGN KEY(cdCategoria) REFERENCES tCategoria(cdCategoria);

ALTER TABLE tQuestao ADD CONSTRAINT fk_010 FOREIGN KEY(cdUsuario) REFERENCES tUsuario(cdUsuario);

ALTER TABLE tQuestao ADD CONSTRAINT fk_011 FOREIGN KEY(cdCategoria) REFERENCES tCategoria(cdCategoria);

ALTER TABLE tQuestionarioQuestao ADD CONSTRAINT fk_012 FOREIGN KEY(cdQuestionario) REFERENCES tQuestionario(cdQuestionario);

ALTER TABLE tQuestionarioQuestao ADD CONSTRAINT fk_013 FOREIGN KEY(cdQuestao) REFERENCES tQuestao(cdQuestao);

ALTER TABLE tResposta ADD CONSTRAINT fk_014 FOREIGN KEY(cdQuestionarioQuestao) REFERENCES tQuestionarioQuestao(cdQuestionarioQuestao);

ALTER TABLE tResposta ADD CONSTRAINT fk_015 FOREIGN KEY(cdUsuario) REFERENCES tUsuario(cdUsuario);

ALTER TABLE tGrupo ADD CONSTRAINT fk_016 FOREIGN KEY(cdUsuario) REFERENCES tUsuario(cdUsuario);

ALTER TABLE tCurso ADD CONSTRAINT fk_017 FOREIGN KEY(cdUsuario) REFERENCES tUsuario(cdUsuario);