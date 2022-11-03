package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Herb;
import com.example.demo.service.HerbService;

import java.util.*;

@RestController()
@RequestMapping("/herbs")
public class HerbController {

	@Autowired
	HerbService service;
	
	@GetMapping("/test")
	public String HelloWorld() {
		return "Hello World! I am HerbAPI ";
	}
	
	@PostMapping("/add")

	public ResponseEntity<Herb> add(@RequestBody Herb newHerb) {
		return new ResponseEntity<Herb>(this.service.add(newHerb), HttpStatus.CREATED);
	}
	
	@PostMapping("/addMultiple")
	public ResponseEntity<List<Herb>> addMultiple(@RequestBody List<Herb> herbs) {
		return new ResponseEntity<List<Herb>>(this.service.addMultiple(herbs), HttpStatus.CREATED);

	}
	@GetMapping("/getAll")
	public List<Herb> findAll(){
		return service.findAll();
	}

	@GetMapping("/getById/{id}")
	public Herb getById(@PathVariable("id") Long index) {
		return service.getById(index);
	}
	
//	@GetMapping("/getByParam")
//	public Herb getByParam(@PathParam("index") Long index) throws HerbNotFound{
//		if (index != null) {
//				return service.getById(index);
//		}
//		return null;
//	}

	@PutMapping("/update/{id}")
	
	public ResponseEntity<Herb> update(@RequestBody Herb herb, @PathVariable("id") Long id) {
		return new ResponseEntity<Herb>(this.service.update(id, herb), HttpStatus.CREATED);
	//public Herb update(@RequestBody Herb herb, @PathVariable("id") Long id) {
	//	return service.update(id, herb);
	}
	
	@DeleteMapping("/delete/{index}")
	public Boolean delete(@PathVariable("index") Long index) {
		return service.delete(index);
	}


}

