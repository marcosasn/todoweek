import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Objetivo;
import models.Prioridade;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import models.dao.GenericDAO;
import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;

public class ApplicationTest extends AbstractTest {
    
	Result result;

    @Test
    public void deveConectarComIndex() throws Exception {
        result = callAction(controllers.routes.ref.Application.index(), fakeRequest());

        assertThat(status(result)).isEqualTo(Http.Status.OK);
        assertThat(redirectLocation(result)).isEqualTo(null);
    }

    @Test
    public void deveMostrarInterfaceInicial() throws Exception {
        result = callAction(controllers.routes.ref.Application.index(), fakeRequest());
        assertThat(status(result)).isEqualTo(Http.Status.OK);
        assertThat(redirectLocation(result)).isEqualTo(null);
        assertThat(charset(result)).isEqualTo("utf-8");

       
        String[] words = {"To Do Week", "Metas semana 1", "Metas semana 2", "Metas semana 3","Metas semana 4","Metas semana 5",
        "Metas semana 6", "Criar meta","Semana 1","Semana 2","Semana 3","Semana 4","Semana 5","Semana 6"};
		String html = contentAsString(result);
		
		for (String word : words) {
			assertThat(html).contains(word);	
		}
	}

    @Test
    public void deveAdicionarNovoObjetivo() throws Exception {
        Map<String, String> formData = new HashMap<>();
        formData.put("nome", "Teste1");
        formData.put("descricao", "Teste1 Descrição");
        formData.put("prioridade", "MEDIA");
        formData.put("semana", "Semana 1");

        result = callAction(controllers.routes.ref.Application.index());
        assertThat(status(result)).isEqualTo(Http.Status.OK);
        assertThat(redirectLocation(result)).isEqualTo(null);

        result = callAction(controllers.routes.ref.Application.criarMeta(),fakeRequest().withFormUrlEncodedBody(formData));
        assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/");

        
        GenericDAO dao = new GenericDAO();
        List<Objetivo> objetivos = dao.findAllByClassName(Objetivo.class.getName());
        assertThat(objetivos.size()).isEqualTo(1);
        assertThat(objetivos.get(0).getNome()).isEqualTo("Teste1");
        assertThat(objetivos.get(0).getDescricao()).isEqualTo("Teste1 Descrição");
        assertThat(objetivos.get(0).getPrioridade()).isEqualTo(Prioridade.MEDIA);
        assertThat(objetivos.get(0).getSemana()).isEqualTo(1);

        result = callAction(controllers.routes.ref.Application.index(), fakeRequest());
        assertThat(status(result)).isEqualTo(Http.Status.OK);
        assertThat(contentAsString(result)).contains("Teste1");
        assertThat(contentAsString(result)).contains("Teste1 Descrição");
        assertThat(contentAsString(result)).contains("MEDIA");
    }

    @Test
    public void naoDeveAdicionarNovoObjetivoFormVazio() throws Exception {
        Map<String, String> formData = new HashMap<>();

        result = callAction(controllers.routes.ref.Application.criarMeta(),fakeRequest().withFormUrlEncodedBody(formData));
        assertThat(status(result)).isEqualTo(Http.Status.BAD_REQUEST);

        GenericDAO dao = new GenericDAO();
        List<Objetivo> objetivos = dao.findAllByClassName(Objetivo.class.getName());
        assertThat(objetivos).isEmpty();
    }

    @Test
    public void deveOrdemPorSemana() throws Exception {
    	Map<String, String> formData = new HashMap<>();
        formData.put("nome", "Teste1");
        formData.put("descricao", "Teste1 Descrição");
        formData.put("prioridade", "MEDIA");
        formData.put("semana", "Semana 1");
        
        result = callAction(controllers.routes.ref.Application.criarMeta(),fakeRequest().withFormUrlEncodedBody(formData));
        assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/");
        
        formData.clear();
        formData.put("nome", "Teste2");
        formData.put("descricao", "Teste2 inferior a 1");
        formData.put("prioridade", "MEDIA");
        formData.put("semana", "Semana 2");

        result = callAction(controllers.routes.ref.Application.criarMeta(),fakeRequest().withFormUrlEncodedBody(formData));
        assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/");
   
        result = callAction(controllers.routes.ref.Application.index(), fakeRequest());
        assertThat(status(result)).isEqualTo(Http.Status.OK);
        assertThat(redirectLocation(result)).isEqualTo(null);
   
        String html = contentAsString(result);
        assertThat(html.indexOf("Teste1")).isLessThan(html.indexOf("Teste2"));
    }
    
    public void deveOrdenarPorPrioridade() {
    	Map<String, String> formData = new HashMap<>();
        formData.put("nome", "Teste1");
        formData.put("descricao", "Teste1 Descrição");
        formData.put("prioridade", "MEDIA");
        formData.put("semana", "Semana 1");
 
        result = callAction(controllers.routes.ref.Application.criarMeta(),fakeRequest().withFormUrlEncodedBody(formData));
        assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/");
       
        formData.clear();
        formData.put("title", "Teste3");
        formData.put("description", "Teste3 mais importante");
        formData.put("priority", "Alta");
        formData.put("week", "1");
        
        result = callAction(controllers.routes.ref.Application.criarMeta(),fakeRequest().withFormUrlEncodedBody(formData));
        assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/");
 
        result = callAction(controllers.routes.ref.Application.index(), fakeRequest());
        assertThat(status(result)).isEqualTo(Http.Status.OK);

        String html = contentAsString(result);
        assertThat(html.indexOf("Teste3")).isLessThan(html.indexOf("Teste1"));
    }
   
    @Test
    public void deveMudarObjetivoParafeito() throws Exception {
    	Map<String, String> formData = new HashMap<>();
        formData.put("nome", "Teste1");
        formData.put("descricao", "Teste1 Descrição");
        formData.put("prioridade", "MEDIA");
        formData.put("semana", "Semana 1");

        result = callAction(controllers.routes.ref.Application.criarMeta(),fakeRequest().withFormUrlEncodedBody(formData));
        assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/");

        result = callAction(controllers.routes.ref.Application.marcarMetaFeita(1L), fakeRequest());
        assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/");

        result = callAction(controllers.routes.ref.Application.index(), fakeRequest());
        assertThat(contentAsString(result)).contains("FEITO");
        
        GenericDAO dao = new GenericDAO();
        List<Objetivo> objetivos = dao.findAllByClassName(Objetivo.class.getName());
        assertThat(objetivos.get(0).getStatus()).isEqualTo("FEITO");
    }

    @Test
    public void deveDeletarObjetivo() throws Exception {
    	Map<String, String> formData = new HashMap<>();
        formData.put("nome", "Teste1");
        formData.put("descricao", "Teste1 Descrição");
        formData.put("prioridade", "MEDIA");
        formData.put("semana", "Semana 1");

        result = callAction(controllers.routes.ref.Application.criarMeta(),fakeRequest().withFormUrlEncodedBody(formData));
        assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/");

        result = callAction(controllers.routes.ref.Application.deletarMeta(1L), fakeRequest());
        assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/");

        assertThat(contentAsString(result)).doesNotContain("Teste1");
        assertThat(contentAsString(result)).doesNotContain("Teste1 Descrição");
        
        GenericDAO dao = new GenericDAO();
        List<Objetivo> objetivos = dao.findAllByClassName(Objetivo.class.getName());
        assertThat(objetivos).isEmpty();
    }
}