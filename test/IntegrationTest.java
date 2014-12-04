import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    /*@Test
    public void deveTerPaginaInicalSemObjetivos() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            
        	public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                //assertThat(browser.pageSource()).contains("Your new application is ready.");
            }
        });
    }*/

    /*@Test
    public void deveCadastrarUmaMetaNaPagina() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/");

                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("nome", "Pagar SI1");
                parameters.put("prioridade", "Alta");
                parameters.put("descricao", "Quero pagar a disciplina de SI1.");
                parameters.put("semana", "Semana 6");

                FakeRequest fakeRequest = new FakeRequest().withFormUrlEncodedBody(parameters);
                Result result = Helpers.callAction(controllers.routes.ref.Application.criarMeta(), fakeRequest);
                
                int responseCode = status(result);
                assertThat(responseCode).isEqualTo(OK);
            }
        });
    }*/
}
