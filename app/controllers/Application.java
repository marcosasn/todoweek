package controllers;

import java.util.LinkedList;
import java.util.List;

import models.Objective;
import models.ToDoWeek;
import models.Week;
import play.data.Form;
import play.data.DynamicForm;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

	private static ToDoWeek toDoWeek = new ToDoWeek();
	private static List<Week> semanas = toDoWeek.getSemanas();

	public static Result index() {
		return ok(index.render(semanas, null));
	}

	public static Result criarMeta() {
		DynamicForm form = Form.form().bindFromRequest();
		Objective meta;
		
		String nome = form.get("nome");
		String prioridade = form.get("prioridade");
		String descricao = form.get("descricao");
		String semana = form.get("semana");

		switch (semana) {
		case "Semana 1":
			meta = new Objective(nome, prioridade, descricao, semana);
			semanas.get(0).addObjetivo(meta);
			break;
		case "Semana 2":
			meta = new Objective(nome, prioridade, descricao, semana);
			semanas.get(1).addObjetivo(meta);
			break;
		default:
			break;
		}
		
		return ok(index.render(semanas, null));
	}
}
