import static org.junit.Assert.*;

import java.util.List;
import models.Objetivo;
import models.ToDoWeek;
import models.dao.GenericDAO;

import org.junit.Test;

/**
 * Created by Marcos on 14/11/14.
 */

public class ToDoWeekTest extends AbstractTest {

	private GenericDAO dao = new GenericDAO();
	private ToDoWeek todoweek =  new ToDoWeek();
	private List<Objetivo> objetivos;
	
	@Test
    public void deveIniciarSemObjetivos() throws Exception {
        objetivos = dao.findAllByClassName(Objetivo.class.getName());
        assertTrue(objetivos.isEmpty());
    }

	@Test
    public void deveSalvarObjetivoNoBD() throws Exception {
        dao.persist(todoweek.criarMeta("Pagar SI1", "Alta", "Quero pagar a disciplina de SI1.", "Semana 6"));

        objetivos = dao.findAllByClassName(Objetivo.class.getName());
        assertEquals(objetivos.size(), 1);
        assertEquals(objetivos.get(0).getId(), 1);
        assertEquals(objetivos.get(0),todoweek.criarMeta("Pagar SI1", "Alta", "Quero pagar a disciplina de SI1.", "Semana 6"));
    }
    
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