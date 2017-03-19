package com.projeto.uniprof.service;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {		
	public String getDsPermissao(int cdPermissao) {
		if(cdPermissao == 4) {
			return "Administrador";			
		} else if(cdPermissao == 3) {
			return "Coordenador";
		} else if(cdPermissao == 2) {
			return "Professor";			
		} else {
			return "Aluno";
		}
	}
	
	public String getStatusUsuario(int statusUsuario) {
		if(statusUsuario == 1) {
			return "Ativo";
		} else {
			return "Inativo";
		}
	}
}