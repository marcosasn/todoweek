package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Week {
	
	private List<Objective> objetivos;
	private String name;
	
	public Week() {	
		objetivos = new ArrayList<Objective>();
	}
	
	public boolean isEmpty() {
		if (objetivos.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public void addObjetivo(Objective objetivo) {
		objetivos.add(objetivo);
		Collections.sort(this.getObjetivos());
	}
	
	public List<Objective> getObjetivos() {
		return objetivos;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int numeroObjetivos() {
		return objetivos.size();
	}
}
