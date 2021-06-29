package com.cognizant.springlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	//@Autowired
	private static CountryService countryService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);
		testGetAllCountries();
		getAllCountriesTest();
		testAddCountry();
		testUpdateCountry();
		testDeleteCountry();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
		testAllCountriesHavingSubstring();
		testAllCountriesHavingSubstringInAscOrder();
		testAllCountriesStartingWith();
		
		LOGGER.info("Inside main");
	}

	private static void testGetAllCountries() {

		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("End");

	}

	private static void getAllCountriesTest() {

		Country country;
		try {
			LOGGER.info("Start");
			country = countryService.findCountryByCode("IN");
			LOGGER.debug("Country:{}", country);
			LOGGER.info("End");
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void testAddCountry() {
		
		Country country = new Country();
		country.setCode("ST");
		country.setName("SampleCountry");
		countryService.addCountry(country);
		
		try {
			LOGGER.info("Start");
			country = countryService.findCountryByCode("ST");
			LOGGER.debug("Country:{}", country);
			LOGGER.info("End");
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void testUpdateCountry() {
		
		String code = "IN";
		String name = "Republic of India";
		
		try {
			Country country = countryService.findCountryByCode(code);
			LOGGER.debug("Country:{}", country);
			
			countryService.updateCountry(code, name);
			
			country = countryService.findCountryByCode(code);
			LOGGER.debug("Country:{}", country);
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void testDeleteCountry() {
		
		String code = "ST";
		countryService.deleteCountry(code);
		try {
			countryService.findCountryByCode(code);
		} catch(CountryNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void testAllCountriesHavingSubstring() {
		LOGGER.info("Start");
		List<Country> countries = countryService.allCountriesHavingSubstring("ou");
		LOGGER.debug("Countries:{}", countries);
		LOGGER.info("End");
	}
	
	private static void testAllCountriesHavingSubstringInAscOrder() {
		LOGGER.info("Start");
		List<Country> countries = countryService.allCountriesHavingSubstringInAscOrder("ou");
		LOGGER.debug("Countries:{}", countries);
		LOGGER.info("End");
	}
	
	private static void testAllCountriesStartingWith() {
		LOGGER.info("Start");
		List<Country> countries = countryService.allCountriesStartingWith('Z');
		LOGGER.debug("Countries:{}", countries);
		LOGGER.info("End");
	}
	
}