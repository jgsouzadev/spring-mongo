package com.gui.workshopmongo.resource;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gui.workshopmongo.domain.Post;
import com.gui.workshopmongo.resource.util.URL;
import com.gui.workshopmongo.service.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		
		Post postObj = service.findById(id);
		
		return ResponseEntity.ok().body(postObj);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text
			,@RequestParam(value="minDate", defaultValue="") String minDate
			,@RequestParam(value="maxDate", defaultValue="") String maxDate
			
			) {
		
			text = URL.decodeParam(text);
			Date min = URL.convertDate(minDate, new Date(0L));
			Date max = URL.convertDate(maxDate, new Date());
			List<Post> list = service.fullSearch(text, min, max);

			return ResponseEntity.ok().body(list);
	}
}
