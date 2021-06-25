package fr.afg.iteration1;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.afg.iteration1.entity.Company;
import fr.afg.iteration1.service.CompanyService;

;

@SpringBootTest
class Iteration1ApplicationTests {

	@Autowired
	CompanyService companyService;

	
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testAddCompanyToDb() {
		//add
		//companyService.saveCompany(new Company("companyName", new HashSet<>(), "siret", "email", "phone", "vatNumber", "nafApeCode", new HashSet<>()));
	
		//read
		List<Company> allCompany = companyService.getAllCompany();
		allCompany.forEach((c) -> System.out.println(c));
	}



}
