//package br.com.rafaelmattos.MSCustomers.config;
//
//import java.text.ParseException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//import br.com.rafaelmattos.MSCustomers.service.TestService;
//
////Padroes Strategy e Template Method
//@Configuration
//@Profile("test")
//public class TestConfig {
//
//	@Autowired
//	private TestService dbService;
//	
//	@Bean
//	public boolean instantiateDatabase() throws ParseException {
//		dbService.instantiateTestDatabase();
//		return true;
//	}
//}