package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Herb;
import com.example.demo.exceptions.HerbNotFound;
import com.example.demo.repository.HerbRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HerbService {
	
	@Autowired
	HerbRepo repo;
	

	public Herb add(Herb herb) {
		return repo.save(herb);
	}
	
	public List<Herb> addMany(List<Herb> herb){
		List<Herb> output = new ArrayList<Herb>();
		herb.forEach(t -> output.add(add(t)));
		return output;
	}
	
	public List<Herb> getAll() {
		return repo.findAll();
	}
	
	public Herb getById(Long index) {
		return repo.findById(index).orElseThrow(HerbNotFound::new);
	}
	
    public Optional<Herb> findById(Long Id) {
    	return repo.findById(Id);
    	
    	
    }
	
	public Herb update(Long Id, Herb herb) {
		Herb found = repo.findById(Id).orElseThrow(HerbNotFound::new);
		
		found.setName(herb.getName());
		found.setLatin(herb.getLatin());
		found.setUses(herb.getUses());
		
		return repo.save(herb);
	}
    	
	public boolean delete(Long index) {
    	repo.deleteById(index);
    	return (!repo.existsById(index));
	}


}
