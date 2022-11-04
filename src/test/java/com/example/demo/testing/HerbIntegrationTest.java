package com.example.demo.testing;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.entity.Herb;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:herb-schema.sql", "classpath:herb-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class HerbIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testAdd() throws Exception {
		Herb testHerb = new Herb("ginger", "zingiber officinale", "root");
		String testHerbAsJSON = this.mapper.writeValueAsString(testHerb);
		
		RequestBuilder req = post("/herbs/add").contentType(MediaType.APPLICATION_JSON).content(testHerbAsJSON);
		
		testHerb.setId(2L);
		String responseBody = this.mapper.writeValueAsString(testHerb);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(responseBody);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testAddMultiple() throws Exception{
		List<Herb> testHerbList = new ArrayList<Herb>();
		testHerbList.add(new Herb(2L, "herb2", "latin2", "part2"));
		testHerbList.add(new Herb(3L, "herb3", "latin3", "part3"));
		
		String testHerbListAsJSON = this.mapper.writeValueAsString(testHerbList);
	
		RequestBuilder req = post("/herbs/addMultiple").contentType(MediaType.APPLICATION_JSON).content(testHerbListAsJSON);
		
		String responseBody = this.mapper.writeValueAsString(testHerbList);
		
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(responseBody);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	
	@Test
	void testGet() throws Exception {
		Herb testHerb = new Herb(1L, "herb1", "latin1", "part1");
		this.mvc.perform(get("/herbs/getById/1")).andExpect(status().isOk())
				.andExpect(content().json(this.mapper.writeValueAsString(testHerb)));
		
		
	}
	@Test
	void testGetAll() throws Exception {
		List<Herb> testGetAllList = new ArrayList<Herb>();
		testGetAllList.add(new Herb(1L, "herb1", "latin1", "part1"));
		this.mvc.perform(get("/herbs/getAll")).andExpect(status().isOk())
				.andExpect(content().json(this.mapper.writeValueAsString(testGetAllList)));
		
	}
	
	@Test
	void testUpdate() throws Exception {
		//new input
		Herb testUpdate = new Herb("herb4", "latin4", "part4");
		String testUpdateAsJSON = this.mapper.writeValueAsString(testUpdate);
		//request body
		RequestBuilder req = put("/herbs/update/1").contentType(MediaType.APPLICATION_JSON).content(testUpdateAsJSON);
		
		testUpdate.setId(1L);
		String responseBody = this.mapper.writeValueAsString(testUpdate);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(responseBody);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test 
	void testDelete() throws Exception {
		this.mvc.perform(delete("/herbs/delete/1")).andExpect(status().isOk());
	

	}
}


