package com.gui.workshopmongo.resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gui.workshopmongo.domain.Post;
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
	
	
}
