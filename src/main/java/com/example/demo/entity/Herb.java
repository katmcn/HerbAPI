package com.example.demo.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Herb {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String name;
	
	private String latin;
	
	private String uses;
	
	
	
	public Herb() {
		
	}

	
	public Herb(Long id, String name, String latin, String uses) {
		super();
		Id = id;
		this.name = name;
		this.latin = latin;
		this.uses = uses;
	}


	public Herb(String name, String latin, String uses) {
		super();
		this.name = name;
		this.latin = latin;
		this.uses = uses;
	}
	
	public Long getId() {
		return Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLatin() {
		return latin;
	}
	public void setLatin(String latin) {
		this.latin = latin;
	}
	public String getUses() {
		return uses;
	}
	public void setUses(String uses) {
		this.uses = uses;
	}
	
	
}

