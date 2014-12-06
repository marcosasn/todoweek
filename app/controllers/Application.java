package controllers;

import java.util.Collections;
import java.util.List;


import models.Objetivo;
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
        Collections.sort(objetivos);
        return ok(index.render(objetivos));
    }	
    
	@Transactional
	public static Result criarMeta() {
		DynamicForm form = Form.form().bindFromRequest();
		String nome;
		String prioridade;
		String descricao;
		String semana;
		
		try {
            nome = form.get("nome");
            prioridade = form.get("prioridade");
            descricao = form.get("descricao");;
            semana = form.get("semana");
        } catch (Exception e) {
            return badRequest();
        }

        if (nome == null || nome.equals("")|| descricao == null || descricao.equals("")|| prioridade == null ||
                semana == null || semana.equals("")) {
            return badRequest();
        }
		
		dao.persist(todoweek.criarMeta(nome, prioridade, descricao, semana));
        return redirect(routes.Application.index());
	}
	
	@Transactional
    public static Result marcarMetaFeita(long id) {
		Objetivo objetivo = dao.findByEntityId(Objetivo.class, id);
		dao.remove(objetivo);
        objetivo.setStatus(Objetivo.FEITO);
        dao.persist(objetivo);
        return redirect(routes.Application.index());
    }
	
	@Transactional
    public static Result deletarMeta(long id) {
        dao.removeById(Objetivo.class, id);
        return redirect(routes.Application.index());
    }
}
