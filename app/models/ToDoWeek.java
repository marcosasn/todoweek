package models;

import java.util.ArrayList;
import java.util.List;

public class ToDoWeek {
	
	private static List<Week> semanas;
	private final String NOME = "Semana ";
	
	public ToDoWeek() {
		semanas = new ArrayList<Week>();
		for (int i = 0; i < 7; i++) {
			semanas.add(new Week());
			semanas.get(i).setName(NOME + i);
		}
	}

	public static List<Week> getSemanas() {
		return semanas;
	}
	
	public void setSemana(Week semana) {
		semanas.add(semana);
	}
	
}
