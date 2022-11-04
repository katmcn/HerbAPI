package com.example.demo.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.Herb;
import com.example.demo.repository.HerbRepo;
import com.example.demo.service.HerbService;

@SpringBootTest
public class HerbServiceTest {
	
	@Autowired
	private HerbService service;
	
	@MockBean
	private HerbRepo repo; 
	
	@Test
	void testadd() {
		final long id = 3L;
		Herb testHerb = new Herb(id, "newHerb", "newLatin", "newPart");
		Mockito.when(this.repo.save(testHerb)).thenReturn(testHerb);
		assertEquals(testHerb, this.service.add(testHerb));
		Mockito.verify(this.repo, Mockito.times(1)).save(testHerb);
	}
	
	@Test
	void testaddMultiple() {
		List<Herb> testHerbList = new ArrayList<Herb>();
		testHerbList.add(new Herb(4L, "herb4", "latin4", "part4"));
		testHerbList.add(new Herb(5L, "herb5", "latin5", "part5"));
		Mockito.when(this.repo.saveAll(testHerbList)).thenReturn(testHerbList);
		assertEquals(testHerbList, this.service.addMultiple(testHerbList));
		Mockito.verify(this.repo, Mockito.times(1)).saveAll(testHerbList);
	}
	
	@Test
	void testgetAll() {
		List<Herb> testHerbList = new ArrayList<Herb>();
		testHerbList.add(new Herb(4L, "herb4", "latin4", "part4"));
		testHerbList.add(new Herb(5L, "herb5", "latin5", "part5"));
		Mockito.when(this.repo.findAll()).thenReturn(testHerbList);
		assertEquals(testHerbList, this.service.findAll());
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testgetById() {
		final long id = 7L;
		Herb testHerb = new Herb(id, "oldHerb", "oldLatin", "oldPart");
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(new Herb(id, "oldHerb", "oldLatin", "oldPart")));
		assertEquals(testHerb, this.service.getById(id));
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testupdate() {
		final long id = 2L;
		Herb testHerb = new Herb(id, "newHerb", "newLatin", "newPart");
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(new Herb(id, "oldHerb", "oldLatin", "oldPart")));
		Mockito.when(this.repo.save(testHerb)).thenReturn(testHerb);
		assertEquals(testHerb, this.service.update(id, testHerb));
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(testHerb);
		
	}
	
	@Test
	void testdelete() {
		final long id = 1L;
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		assertTrue(this.service.delete(id));
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
}
