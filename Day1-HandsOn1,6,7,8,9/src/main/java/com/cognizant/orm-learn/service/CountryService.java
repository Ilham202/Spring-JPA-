package com.cognizant.springlearn.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.repository.CountryRepository;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;

	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
		
		Optional<Country> result = countryRepository.findById(countryCode);
		if(!result.isPresent()) {
			throw new CountryNotFoundException("Country Not Found");
		} else {
			Country country = result.get();
			return country;
		}
		
	}
	
	@Transactional
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}
	
	@Transactional
	public void addCountry(Country country) {
		countryRepository.save(country);
	}
	
	@Transactional
	public void updateCountry(String code, String name) {
		Optional<Country> result = countryRepository.findById(code);
		Country country = result.get();
		country.setName(name);
		countryRepository.save(country);
	}
	
	@Transactional
	public void deleteCountry(String code) {
		countryRepository.deleteById(code);
	}
	
	@Transactional
	public List<Country> allCountriesHavingSubstring(String subString) {
		List<Country> countries = countryRepository.findByNameContaining(subString);
		return countries;
	}
	
	@Transactional
	public List<Country> allCountriesHavingSubstringInAscOrder(String subString) {
		List<Country> countries = countryRepository.findByNameContainingOrderByNameAsc(subString);
		return countries;
	}
	
	@Transactional
	public List<Country> allCountriesStartingWith(char firstLetter) {
		List<Country> countries = countryRepository.findByNameStartingWith(firstLetter);
		return countries;
	}
	
}
