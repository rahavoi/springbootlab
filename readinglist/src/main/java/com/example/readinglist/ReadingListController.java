package com.example.readinglist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/books")
public class ReadingListController {
	private ReadingListRepository readingListRepository;
	
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}
	
	@RequestMapping(value="/{reader}", method = RequestMethod.GET)
	public String getReadingList(@PathVariable String reader, Model model) {
		List<Book> books = readingListRepository.findByReader(reader);
		
		if(books != null) {
			model.addAttribute("books", books);
		}
		
		return "readinglist";
	}
	
	@RequestMapping(value = "/{reader}", method = RequestMethod.POST)
	public String addToReadingList(@PathVariable String reader, Book book){
		book.setReader(reader);
		
		readingListRepository.save(book);
		
		return "redirect:/{reader}";
	}
	
}
