package com.cognizant.springlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.springlearn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String>{

	public List<Country> findByNameContaining(String subString);
	
	public List<Country> findByNameContainingOrderByNameAsc(String subString);
	
	public List<Country> findByNameStartingWith(char firstLetter);
	
}
