package controllers;

import java.util.List;


import models.Objetivo;
import models.Prioridade;
import models.ToDoWeek;
import models.dao.GenericDAO;
import play.data.Form;
import play.data.DynamicForm;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

	private static List<Objetivo> objetivos;
	private static ToDoWeek todoweek = new ToDoWeek();
	private static final GenericDAO dao = new GenericDAO();

    @Transactional
    public static Result index() {
        objetivos = dao.findAllByClassName(Objetivo.class.getName());
        return ok(index.render(objetivos));
    }	
    
	@Transactional
	public static Result criarMeta() {
		DynamicForm form = Form.form().bindFromRequest();
			
		String nome = form.get("nome");
		String prioridade = form.get("prioridade");
		String descricao = form.get("descricao");
		String semana = form.get("semana");
		
		dao.persist(todoweek.criarMeta(nome, prioridade, descricao, semana));
        List<Objetivo> objetivos = dao.findAllByClassName(Objetivo.class.getName());
        return ok(index.render(objetivos));
	}
	
	/*//persiste?
	public static Result marcarMeta() {
		return ok(index.render(toDoWeek.getSemanas(), null));
	}*/
	
	@Transactional
	public static Result deletarMeta(String id) {
		for (int i = 0; i < objetivos.size(); i++) {
			if (objetivos.get(i).getId() == Long.parseLong(id)) {
				dao.remove(objetivos.remove(i));
			}
		}
		return ok(index.render(objetivos));
	}
}
