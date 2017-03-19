package com.projeto.uniprof.service;

import com.projeto.uniprof.model.Usuario;

import java.util.List;

import com.projeto.uniprof.model.Questionario;
import com.projeto.uniprof.model.Resposta;

public class RespostaService {
	public double corrigirQuestionario(Usuario usuario, Questionario questionario, List<Resposta> listaRespostas) {
		double nota = 0;
		
		for(int i = 0; i < questionario.getQuestoes().size(); i++) {
			for(int y = 0; y < listaRespostas.size(); y++) {
				if(questionario.getCdQuestionario() == listaRespostas.get(i).getQuestionario().getCdQuestionario()) {
					if(questionario.getQuestoes().get(i).getAlternativaCorreta().equals(listaRespostas.get(y).getAlternativaSelecionada())) {
						nota++;
					}
				}
			}
		}
		
		return nota;
	}
}