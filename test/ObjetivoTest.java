import static org.junit.Assert.*;

import java.util.List;

import models.Objetivo;
import models.Prioridade;
import models.ToDoWeek;
import models.dao.GenericDAO;

import org.junit.Test;


public class ObjetivoTest extends AbstractTest {

	GenericDAO dao = new GenericDAO();
	ToDoWeek todoweek =  new ToDoWeek();
    List<Objetivo> metas;

    @Test
    public void deveIniciarBDVazio() throws Exception {
        metas = dao.findAllByClassName(Objetivo.class.getName());
        assertEquals(metas.size(),0);
    }

    @Test
    public void deveAdicionarUmObjetivo() throws Exception{
        dao.persist(todoweek.criarMeta("Titulo", "ALTA" ,"Descrição", "Semana 1"));

        metas = dao.findAllByClassName((Objetivo.class.getName()));
        assertEquals(metas.size(), 1);
        assertEquals(metas.get(0).getNome(),"Titulo");
        assertEquals(metas.get(0).getDescricao(),"Descrição");
        assertEquals(metas.get(0).getSemana(),1);
        assertEquals(metas.get(0).getPrioridade(), Prioridade.ALTA);
      }

    @Test
    public void deveRemoverObjetivo() throws Exception{
        Objetivo obj1 = todoweek.criarMeta("Titulo","ALTA", "Descrição", "Semana 1");
        Objetivo obj2 = todoweek.criarMeta("Titulo","BAIXA", "Descrição", "Semana 2");
        dao.persist(obj1);
        dao.persist(obj2);

        metas = dao.findAllByClassName((Objetivo.class.getName()));
        assertTrue(metas.size() ==  new Integer(2));

        dao.remove(obj1);
        metas = dao.findAllByClassName((Objetivo.class.getName()));
        assertEquals(metas.size(),1);
        assertEquals(metas.get(0).getNome(), new String("Titulo"));
        assertEquals(metas.get(0).getDescricao(), new String("Descrição"));
        assertEquals(metas.get(0).getSemana(),2);
        assertEquals(metas.get(0).getPrioridade(), Prioridade.BAIXA);
    }

    @Test
    public void deveRemoverPeloId() throws Exception {
    	Objetivo obj1 = todoweek.criarMeta("Titulo","ALTA", "Descrição", "Semana 1");
        Objetivo obj2 = todoweek.criarMeta("Titulo","BAIXA", "Descrição", "Semana 2");
        dao.persist(obj1);
        dao.persist(obj2);

        metas = dao.findAllByClassName((Objetivo.class.getName()));
        assertTrue(metas.size() ==  new Integer(2));

        long id = metas.get(0).getId();

        dao.removeById(Objetivo.class, id);

        metas = dao.findAllByClassName(Objetivo.class.getName());
        assertEquals(metas.size(),1);
        assertEquals(metas.get(0).getNome(), new String("Titulo"));
        assertEquals(metas.get(0).getDescricao(), new String("Descrição"));
        assertEquals(metas.get(0).getSemana(),2);
        assertEquals(metas.get(0).getPrioridade(), Prioridade.BAIXA);
    }
}
