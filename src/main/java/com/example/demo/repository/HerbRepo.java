package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Herb;

import java.util.Optional;


@Repository
public interface HerbRepo extends JpaRepository<Herb, Long>{

	Herb findHerbByName(String name); 
	 
	 @Query(value = "SELECT * FROM HERB WHERE name=?1 LIMIT 1", nativeQuery = true)
		Optional<Herb> getByName(String name);
	 
	

}



