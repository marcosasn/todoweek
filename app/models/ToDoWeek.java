package models;

public class ToDoWeek {
		
	private final String BAIXA = "Baixa";
	private final String MEDIA = "Média";
	private final String ALTA = "Alta";
	
	private final String SEMANA_UM = "Semana 1";
	private final String SEMANA_DOIS = "Semana 2";
	private final String SEMANA_TRES = "Semana 3";
	private final String SEMANA_QUATRO = "Semana 4";
	private final String SEMANA_CINCO = "Semana 5";
	private final String SEMANA_SEIS = "Semana 6";
	
	public ToDoWeek() {}
	
	public Objetivo criarMeta(String nome,String prioridade, String descricao, String semana) {
		Prioridade prioridadeCorrente = Prioridade.BAIXA;
		int semanaCorrente = 1;
				
		if (prioridade.equals(BAIXA)) {
			prioridadeCorrente = Prioridade.BAIXA;
		}
		else if (prioridade.equals(MEDIA)) {
			prioridadeCorrente = Prioridade.MEDIA;
		}
		else if (prioridade.equals(ALTA)) {
			prioridadeCorrente = Prioridade.ALTA;
		}
		
		switch (semana) {
		case SEMANA_UM:
			semanaCorrente = 1;
			break;
		case SEMANA_DOIS:
			semanaCorrente = 2;
			break;
		case SEMANA_TRES:
			semanaCorrente = 3;
			break;
		case SEMANA_QUATRO:
			semanaCorrente = 4;
			break;
		case SEMANA_CINCO:
			semanaCorrente = 5;
			break;
		case SEMANA_SEIS:
			semanaCorrente = 6;
			break;
		default:
			break;
		}
		return new Objetivo(nome, prioridadeCorrente, descricao, semanaCorrente);
	}
	
}
