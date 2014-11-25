import static org.junit.Assert.*;

import models.Objective;
import models.Week;

import org.junit.Before;
import org.junit.Test;


public class ObjectiveTest {
	
	private Objective objective;

	@Before
	public void setUp() throws Exception {
		objective = new Objective();
	}

	@Test
	public void testeGetsSets() {
		objective.setNome("Aprender Play e Java.");
		assertTrue(objective.getNome().equals("Aprender Play e Java."));
		
		objective.setPrioridade(1);
		assertTrue(objective.getPrioridade() == new Integer(1));
		
		objective.setDescricao("Eu gostaria de aprender Play e Java neste lab2 de si1.");
		assertTrue(objective.getDescricao().equals("Eu gostaria de aprender Play e Java neste lab2 de si1."));
		
		objective.ehAlcancada(true);
		assertTrue(objective.getStatus());
	}
	
	@Test
	public void deveSerAlcancada() {
		assertFalse(objective.getStatus());
		objective.ehAlcancada(true);
		assertTrue(objective.getStatus());
		
		objective.ehAlcancada(false);
		assertFalse(objective.getStatus());
		
		//semana????????????
	}
}
