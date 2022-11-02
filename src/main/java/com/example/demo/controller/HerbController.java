package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Herb;
import com.example.demo.exceptions.HerbNotFound;
import com.example.demo.service.HerbService;


import java.util.*;

import javax.websocket.server.PathParam;


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
	public Herb add(@RequestBody Herb herb) {
		return service.add(herb);
	}
	
	@PostMapping("/addMultiple")
	public List<Herb> addMultiple(@RequestBody List<Herb> herbs) {
		return service.addMultiple(herbs);

	}
	@GetMapping("/getAll")
	public List<Herb> getAll(){
		return service.getAll();
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
	public Herb update(@RequestBody Herb herb, @PathVariable("id") Long id) {
		return service.update(id, herb);
	}
	
	@DeleteMapping("/delete/{index}")
	public Boolean delete(@PathVariable("index") Long index) {
		return service.delete(index);
	}


}

