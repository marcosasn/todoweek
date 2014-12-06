import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.status;
import static play.test.Helpers.testServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.swing.text.View;

import models.Objetivo;
import models.Prioridade;
import models.ToDoWeek;
import models.dao.GenericDAO;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import play.GlobalSettings;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.libs.F.Callback;
import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;
import play.test.Helpers;
import play.test.TestBrowser;
import scala.Option;
import views.html.defaultpages.badRequest;
import views.html.defaultpages.todo;

/**
 * Created by Marcos on 14/11/14.
 */

public class ToDoWeekTest extends AbstractTest {

	private GenericDAO dao = new GenericDAO();
	private ToDoWeek todoweek =  new ToDoWeek();
	//private Result result;
	private List<Objetivo> objetivos;

    
    /*
     * Como usuário, visito a página principa do sistema e vlejo uma lista das metas que tenho, para que eu saiba quais as
     * próximas que tenho a perseguir. As metas estão ordenadas pela semana em que planejei cumpri-las, e, em seguida, por prioridade. 
     * Para cada semana, o sistema diz quantas metas há planejadas naquela semana.
     * 
     * Como usuário, marco uma meta de minha lista como alcançada, e o sistema a destaca de maneira diferente das não alcançadas, 
     * para que assim eu acompanhe meu progresso alcançando as metas em geral. A lista de metas deve agora mostrar para cada semana 
     * o total de metas, o total de metas alcançadas e o total das a alcançar.
     * 
     * Como usuário, apago uma meta, para que assim eu remova do sistema metas que desisti de alcançar.
     */
	
    /*VERIFICAR!!!!!!!!!!!!!
    * Seu sistema deve já iniciar contendo 10 metas cadastradas para as próximas 3 semanas. 
    * A forma fácil de fazer isso é usando o Global.java.
    */
    @Test
    public void deveIniciarSemObjetivos() throws Exception {
        objetivos = dao.findAllByClassName(Objetivo.class.getName());
        assertTrue(objetivos.isEmpty());
    }

    /*
     * Como usuário, cadastro uma nova meta, informando uma descrição, prioridade e escolhendo uma semana no futuro na qual
     * desejo que tenha alcançado essa meta, para que assim minha lista de metas possa ser populada.
     */
    @Test
    public void deveSalvarObjetivoNoBD() throws Exception {
        dao.persist(todoweek.criarMeta("Pagar SI1", "Alta", "Quero pagar a disciplina de SI1.", "Semana 6"));

        objetivos = dao.findAllByClassName(Objetivo.class.getName());
        assertEquals(objetivos.size(), 1);
        assertEquals(objetivos.get(0).getId(), 1);
        assertEquals(objetivos.get(0),todoweek.criarMeta("Pagar SI1", "Alta", "Quero pagar a disciplina de SI1.", "Semana 6"));
    }
    
    /*
     * Metas podem ser adicionadas para as próximas 6 semanas apenas.
     */
    @Test
    public void deveSalvarObjetivosApenasParaProximasSeisSemanas() throws Exception {
    	try {
    		dao.persist(todoweek.criarMeta("Pagar SI1", "Alta", "Quero pagar a disciplina de SI1.", "Semana 0"));
    	} catch (Exception e) {
    		assertEquals("invalid request code or no such operation", e);
    	}
    	
    	try {
    		dao.persist(todoweek.criarMeta("Pagar SI1", "Alta", "Quero pagar a disciplina de SI1.", "Semana 7"));
    	} catch (Exception e) {
    		assertEquals("invalid request code or no such operation", e);
    	}
    	
        dao.persist(todoweek.criarMeta("Pagar SI1", "Alta", "Quero pagar a disciplina de SI1.", "Semana 5"));
        dao.persist(todoweek.criarMeta("Pagar SI1", "Alta", "Quero pagar a disciplina de SI1.", "Semana 4"));
        objetivos = dao.findAllByClassName(Objetivo.class.getName());
        assertEquals(objetivos.size(), 4);
    }
}
