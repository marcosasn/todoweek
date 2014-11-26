package models;

import java.util.ArrayList;
import java.util.List;

public class ToDoWeek {
	
	private static List<Week> semanas;
	private final String NOME = "Semana ";
	private final String SEMANA_UM = "Semana 1";
	private final String SEMANA_DOIS = "Semana 2";
	private final String SEMANA_TRES = "Semana 3";
	private final String SEMANA_QUATRO = "Semana 4";
	private final String SEMANA_CINCO = "Semana 5";
	private final String SEMANA_SEIS = "Semana 6";
	
	private final Integer INDEX_SEMANA_UM = 0;
	private final Integer INDEX_SEMANA_DOIS = 1;
	private final Integer INDEX_SEMANA_TRES = 2;
	private final Integer INDEX_SEMANA_QUATRO = 3;
	private final Integer INDEX_SEMANA_CINCO = 4;
	private final Integer INDEX_SEMANA_SEIS = 5;
	
	public ToDoWeek() {
		semanas = new ArrayList<Week>();
		for (int i = 0; i < 6; i++) {
			semanas.add(new Week());
			semanas.get(i).setName(NOME + i);
		}
	}

	public List<Week> getSemanas() {
		return semanas;
	}
	
	public void setSemana(Week semana) {
		semanas.add(semana);
	}
	
	public void criarMeta(String nome,String prioridade, String descricao, String semana) {
		Objective meta;
		
		switch (semana) {
		case SEMANA_UM:
			meta = new Objective(nome, prioridade, descricao, semana);
			semanas.get(INDEX_SEMANA_UM).addObjetivo(meta);
			break;
		case SEMANA_DOIS:
			meta = new Objective(nome, prioridade, descricao, semana);
			semanas.get(INDEX_SEMANA_DOIS).addObjetivo(meta);
			break;
		case SEMANA_TRES:
			meta = new Objective(nome, prioridade, descricao, semana);
			semanas.get(INDEX_SEMANA_TRES).addObjetivo(meta);
			break;
		case SEMANA_QUATRO:
			meta = new Objective(nome, prioridade, descricao, semana);
			semanas.get(INDEX_SEMANA_QUATRO).addObjetivo(meta);
			break;
		case SEMANA_CINCO:
			meta = new Objective(nome, prioridade, descricao, semana);
			semanas.get(INDEX_SEMANA_CINCO).addObjetivo(meta);
			break;
		case SEMANA_SEIS:
			meta = new Objective(nome, prioridade, descricao, semana);
			semanas.get(INDEX_SEMANA_SEIS).addObjetivo(meta);
			break;
		default:
			break;
		}
	}
}
