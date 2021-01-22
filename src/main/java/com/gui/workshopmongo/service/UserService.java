package com.gui.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gui.workshopmongo.domain.User;
import com.gui.workshopmongo.dto.UserDTO;
import com.gui.workshopmongo.repository.UserRepository;
import com.gui.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		
		Optional<User> userOptional = repo.findById(id);

		if(userOptional.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}

		
		User user = userOptional.get();

		
		return user;
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		this.findById(id);
		repo.deleteById(id);
	}
	
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
