
package fr.afg.iteration1;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import fr.afg.iteration1.delegate.TvaDelegateImpl;
import fr.afg.iteration1.dtoResponse.CalculeTvaResponse;
import fr.afg.iteration1.dtoResponse.TvaResponse;



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

	
}
