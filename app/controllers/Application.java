package controllers;

import models.ToDoWeek;
import play.data.Form;
import play.data.DynamicForm;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

	private static ToDoWeek toDoWeek = new ToDoWeek();


	public static Result index() {
		return ok(index.render(toDoWeek.getSemanas(), null));
	}

	public static Result criarMeta() {
		DynamicForm form = Form.form().bindFromRequest();
			
		String nome = form.get("nome");
		String prioridade = form.get("prioridade");
		String descricao = form.get("descricao");
		String semana = form.get("semana");
		
		toDoWeek.criarMeta(nome, prioridade, descricao, semana);
		return ok(index.render(toDoWeek.getSemanas(), null));
	}
	
	public static Result marcarMeta() {
		return ok(index.render(toDoWeek.getSemanas(), null));
	}
	
	public static Result deletarMeta(String id) {
		String nome = id;
		for (int i = 0; i < toDoWeek.getSemanas().size(); i++) {
			for (int j = 0; j < toDoWeek.getSemanas().get(i).numeroObjetivos(); j++) {
				if(toDoWeek.getSemanas().get(i).getObjetivos().get(j).getNome().equals(nome)){
					toDoWeek.getSemanas().get(i).getObjetivos().remove(toDoWeek.getSemanas().get(i).getObjetivos().get(j));
				}
			}
		}
		return ok(index.render(toDoWeek.getSemanas(), null));
	}
}
