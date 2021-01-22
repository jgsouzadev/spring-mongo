package com.gui.workshopmongo.resource;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gui.workshopmongo.domain.Post;
import com.gui.workshopmongo.domain.User;
import com.gui.workshopmongo.dto.UserDTO;
import com.gui.workshopmongo.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insertUser(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);	
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = service.findById(id);

		return ResponseEntity.ok().body(obj.getPosts());
	}
	
}
