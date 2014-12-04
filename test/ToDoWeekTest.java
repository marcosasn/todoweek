import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

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
import play.test.FakeApplication;
import play.test.Helpers;
import scala.Option;
import views.html.defaultpages.todo;

/**
 * Created by Marcos on 14/11/14.
 */

public class ToDoWeekTest {

	public EntityManager em;
	private GenericDAO dao;
	private ToDoWeek todoweek;

    @Before
    public void setUp() {
    	dao = new GenericDAO();
    	todoweek = new ToDoWeek();
    	
        FakeApplication app = Helpers.fakeApplication(new GlobalSettings());
        Helpers.start(app);
        Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }
    
    /*
     * Como usuário, visito a página principa do sistema e vlejo uma lista das metas que tenho, para que eu saiba quais as
     * próximas que tenho a perseguir. As metas estão ordenadas pela semana em que planejei cumpri-las, e, em seguida, por prioridade. 
     * Para cada semana, o sistema diz quantas metas há planejadas naquela semana.
     * 
     * Como usuário, cadastro uma nova meta, informando uma descrição, prioridade e escolhendo uma semana no futuro na qual
     * desejo que tenha alcançado essa meta, para que assim minha lista de metas possa ser populada. Metas podem ser adicionadas
     * para as próximas 6 semanas apenas.
     * 
     * Como usuário, marco uma meta de minha lista como alcançada, e o sistema a destaca de maneira diferente das não alcançadas, 
     * para que assim eu acompanhe meu progresso alcançando as metas em geral. A lista de metas deve agora mostrar para cada semana 
     * o total de metas, o total de metas alcançadas e o total das a alcançar.
     * 
     * Como usuário, apago uma meta, para que assim eu remova do sistema metas que desisti de alcançar.
     * 
     * Seu sistema deve já iniciar contendo 10 metas cadastradas para as próximas 3 semanas. 
     * A forma fácil de fazer isso é usando o Global.java.
     */
	
    @Test
    public void deveIniciarSemObjetivos() throws Exception {
        List<Objetivo> objetivos = dao.findAllByClassName(Objetivo.class.getName());
        assertTrue(objetivos.isEmpty());
    }

    @Test
    public void deveSalvarObjetivoNoBD() throws Exception {
        dao.persist(todoweek.criarMeta("Pagar SI1", "Alta", "Quero pagar a disciplina de SI1.", "Semana 6"));

        List<Objetivo> objetivos = dao.findAllByClassName(Objetivo.class.getName());
        assertTrue(objetivos.size() == 1);
        assertTrue(objetivos.get(0).getId() == 1);
    }

}
