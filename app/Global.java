import play.*;
import models.ToDoWeek;
import models.dao.GenericDAO;
import play.GlobalSettings;

import play.db.jpa.JPA;

public class Global extends GlobalSettings {

	private static GenericDAO dao = new GenericDAO();
	private static ToDoWeek todoweek = new ToDoWeek();
	
	@Override
	public void onStart(Application app) {
		Logger.info("Aplicação inicializada...");

		JPA.withTransaction(new play.libs.F.Callback0() {
			@Override
			public void invoke() throws Throwable {
				//Semana 1
				dao.persist(todoweek.criarMeta("Comer", "MEDIA", "Preciso Comer", "Semana 1" ));
				dao.persist(todoweek.criarMeta("Métodos Estatísticos", "ALTA", "Fazer a lista de exercícios 2", "Semana 1"));
				dao.persist(todoweek.criarMeta("TISE 2014", "ALTA", "Preciso fazer a minha mala", "1"));
				dao.persist(todoweek.criarMeta("OAC", "MEDIA", "Estudar para fazer a reposição do teste de próxima terça-feira", "Semana 1"));
				
				//Semana 2
	            dao.persist(todoweek.criarMeta("Preparar o jantar de Natal", "ALTA", "Curtir o natal com minha família", "Semana 2"));
				
	            //Semana 3
				dao.persist(todoweek.criarMeta("Pesquisa", "ALTA", "Preciso ler um artigo para melhorar a seção de bibliografia", "Semana 3"));
				dao.persist(todoweek.criarMeta("Dormir", "ALTA", "Preciso dormir", "Semana 3"));
	            dao.persist(todoweek.criarMeta("Ler", "MEDIA", "Preciso ler o livro de Senhora.", "Semana 3"));
				
				//Semana 6
				dao.persist(todoweek.criarMeta("Viajar", "ALTA", "Preciso fazer uma viagem de férias", "Semana 6"));
				dao.persist(todoweek.criarMeta("Curtir as fetas de Santo Amaro 2015", "ALTA", "Preciso ir para a festa de Santo Amaro em janeiro de 2015.", "Semana 6"));		
			}});
	}
}
