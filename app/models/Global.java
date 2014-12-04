package models;

import play.*;
import controllers.Application;
import models.dao.GenericDAO;
import play.GlobalSettings;

import play.db.jpa.JPA;

public class Global extends GlobalSettings {

	private static GenericDAO dao = new GenericDAO();
	private static ToDoWeek todoweek = new ToDoWeek();
	
	public void onStart(Application app) {
		Logger.info("Aplicação inicializada...");

		JPA.withTransaction(new play.libs.F.Callback0() {
			@Override
			public void invoke() throws Throwable {
				for (int i = 1; i <= 4; i++) {
					dao.persist(todoweek.criarMeta("Objetivo " + i, "Alta", "Quero alcançar o objetivo "+i+".", "Semana 1"));
				}
				for (int i = 5; i <= 8; i++) {
					dao.persist(todoweek.criarMeta("Objetivo " + i, "Alta", "Quero alcançar o objetivo "+i+".", "Semana 2"));
				}
				for (int i = 8; i <= 10; i++) {
					dao.persist(todoweek.criarMeta("Objetivo " + i, "Alta", "Quero alcançar o objetivo "+i+".", "Semana 3"));
				}
				dao.flush();				
			}});
	}
}
