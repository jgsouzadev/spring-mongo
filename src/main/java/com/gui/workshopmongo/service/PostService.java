package com.gui.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gui.workshopmongo.domain.Post;
import com.gui.workshopmongo.repository.PostRepository;
import com.gui.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {

		Optional<Post> postOptional = repo.findById(id);

		if (postOptional.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}

		Post post = postOptional.get();

		return post;
	}

	
	public List<Post> findByTitle(String text) {
		return repo.findByTitle(text);
	}
}
