package com.andregarcia.workshopmongo.services;

import com.andregarcia.workshopmongo.domain.Post;
import com.andregarcia.workshopmongo.repository.PostRepository;
import com.andregarcia.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;



    public Post findById(String id){
        Post post = repo.findById(id).orElse(null);
        if (post == null){
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return post;
    }

    public List<Post> findByTitle(String text){
        return repo.findByTitleContainingIgnoreCase(text);
    }

}
