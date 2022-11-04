package com.example.demo.entity;
import java.util.Objects;

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
	
	private String partsUsed;
	
	
	
	public Herb() {
		
	}

	
	public Herb(Long id, String name, String latin, String partsUsed) {
		super();
		Id = id;
		this.name = name;
		this.latin = latin;
		this.partsUsed = partsUsed;
	}


	public Herb(String name, String latin, String partsUsed) {
		super();
		this.name = name;
		this.latin = latin;
		this.partsUsed = partsUsed;
	}
	

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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

	public String getPartsUsed() {
		return partsUsed;
	}

	public void setPartsUsed(String partsUsed) {
		this.partsUsed = partsUsed;
	}


	@Override
	public int hashCode() {
		return Objects.hash(Id, latin, name, partsUsed);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Herb other = (Herb) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(latin, other.latin) && Objects.equals(name, other.name)
				&& Objects.equals(partsUsed, other.partsUsed);
	}


	



	
	
	
	
	
}

