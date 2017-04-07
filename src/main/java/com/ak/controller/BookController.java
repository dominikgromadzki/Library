package com.ak.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ak.model.Book;
import com.ak.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	//1a - dodanie do modelu "pustego" obiektu ksiazki (ktorej dane b�d� wype�niane w formularzu do dodawania/edycji ksi�zki)
	//1b - przekierowanie na strone zwiazana z dodawaniem/edycja ksiazki
	
	@RequestMapping(value="/book/create", method = RequestMethod.GET)
	public String getCreateBookForm(Model model)
	{
		//przygotowanie "pustego obiektu modelu - ksiazki" do edycji
		model.addAttribute("book", new Book());
		
		return "book-create";  //przekierowanie na strone "book-create.jsp"
	}
	
	//metoda ktora "przygotowuje" formularz do edycji ksiazki
	@RequestMapping(value="/book/edit/{id}", method = RequestMethod.GET)
	public String getEditBookForm(Model model, @PathVariable Long id)
	{
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		
		return "book-create";  //przekierowanie na strone "book-create.jsp"
	}
	
	@RequestMapping(value="/book/save", method = RequestMethod.POST)
	public String postCreateBook(@ModelAttribute @Valid Book book, BindingResult result ) {
		if(result.hasErrors()) {
			return "book-create";
		}
		
		bookService.save(book);
		
		return "redirect:/books";
	}
	
	//getBooksPage - przekierowuje na strone z lista wszystkich ksiazek (books.jsp)
	//(ustawic odpowiednio model)
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public String getBooksPage(Model model) {
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		
		return "books";  
	}
	
	@RequestMapping(value="/book/delete/{id}", method = RequestMethod.DELETE)
	public String postDeleteBook(@PathVariable Long id)
	{
		bookService.delete(id);
		return "redirect:/books";
	}
	

	
	
	
	
	
	
	
	
}
