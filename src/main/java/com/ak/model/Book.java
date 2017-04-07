package com.ak.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{
	
//	@Column(name="title")
	@NotNull
	@Size(min=3, max=255, message = "Podaj dlugosc od {min} do {max}")
	private String title;
	
	@NotNull
	@Size(min=3, max=255)
	private String author;
	
	@Min(0)
	private Integer available;  //liczba dostepnych ksiazek
	
	public Book() {
		
	}
	
	public void decrementAvailable() {
		available--;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}
	
	
}