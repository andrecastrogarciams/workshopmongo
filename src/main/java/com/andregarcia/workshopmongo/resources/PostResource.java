package com.andregarcia.workshopmongo.resources;

import com.andregarcia.workshopmongo.domain.Post;
import com.andregarcia.workshopmongo.resources.util.URL;
import com.andregarcia.workshopmongo.services.PostService;
import org.bson.codecs.jsr310.LocalDateCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Post> postList = service.findByTitle(text);
        return ResponseEntity.ok().body(postList);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate
    ){
        text = URL.decodeParam(text);
        LocalDate min = URL.convertDate(minDate, LocalDate.ofEpochDay(0L));
        LocalDate max = URL.convertDate(maxDate, LocalDate.now());
        List<Post> postList = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(postList);
    }




}
