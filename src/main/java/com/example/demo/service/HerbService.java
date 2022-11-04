package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.example.demo.entity.Herb;
import com.example.demo.exceptions.HerbNotFound;
import com.example.demo.repository.HerbRepo;


import java.util.List;


@Service
public class HerbService {
	
	@Autowired
	HerbRepo repo;
	

	public Herb add(Herb herb) {
		return repo.save(herb);
	}
	
	public List<Herb> addMultiple(List<Herb> herbs){
		return this.repo.saveAll(herbs);
	}
	
	public List<Herb> findAll() {
		return repo.findAll();
	}
	
	public Herb getById(Long index) {
		return repo.findById(index).orElseThrow(HerbNotFound::new);
	}
	
    
	public Herb update(Long id, Herb herb) {
		Herb found = repo.findById(id).orElseThrow(HerbNotFound::new);
		
		found.setName(herb.getName());
		found.setLatin(herb.getLatin());
		found.setPartsUsed(herb.getPartsUsed());
		
		return repo.save(found);
	}
    	
//	public boolean delete(Long index) {
//    	repo.deleteById(index);
//    	return (!repo.existsById(index));
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		boolean deleted = !this.repo.existsById(id);
		return deleted;
	}


}
