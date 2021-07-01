
package fr.afg.iteration1;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import fr.afg.iteration1.delegate.TvaDelegateImpl;
import fr.afg.iteration1.ui.response.CalculeTvaResponse;
import fr.afg.iteration1.ui.response.TvaResponse;


/**
 * The type Iteration 1 application tests.
 */
@SpringBootTest
class Iteration1ApplicationTests {

	@Autowired
	private TvaDelegateImpl tvaDeleg;

    /**
     * Context loads.
     */
    @Test
	void contextLoads() {
	}

    /**
     * Test delegate get list tva.
     */
    @Test
	public void testDelegateGetListTva() {

		List<TvaResponse> tvaList = tvaDeleg.getAllTva();
		
		for (TvaResponse tva : tvaList) {
			System.out.println(tva.getCategory());
		}
		Assertions.assertNotNull(tvaList);
		Assertions.assertTrue(tvaList.size() > 0);
	}

    /**
     * Test delegate get calcule tva.
     */
    @Test
	public void testDelegateGetCalculeTva() {

		CalculeTvaResponse response = tvaDeleg.getCalculeTva(100.0, 2L);
		
		
		Assertions.assertEquals(110.0, response.getFinalPrice());
	}

    /**
     * Test delegate new tva.
     */
    @Test
	public void testDelegateNewTva() {
        
		TvaResponse newTva = new TvaResponse("vat Code", 7.0, "string label", "String category");

		TvaResponse response = tvaDeleg.newTva(newTva);
		
		System.out.println(response.toString());
		Assertions.assertEquals("string label", response.getLabel());
		Assertions.assertEquals(7.0, response.getValueVat());

	}

    
    @Test
	public void testDelegateUpdateTva() {

		TvaResponse newTva = new TvaResponse(1L, "vat Code", 8.0, "string label", "String category");

		tvaDeleg.updateTva(newTva);

		Assertions.assertEquals(newTva.getValueVat(), 8.0);
	}
}
