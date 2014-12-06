import org.junit.*;

import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class IntegrationTest extends AbstractTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
	@Test
    public void deveTerPaginaInicalSemObjetivos() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            
        	public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("To Do Week");
            }
        });
    }
}
