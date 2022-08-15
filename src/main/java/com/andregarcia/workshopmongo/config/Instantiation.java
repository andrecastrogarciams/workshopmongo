package com.andregarcia.workshopmongo.config;

import com.andregarcia.workshopmongo.domain.Post;
import com.andregarcia.workshopmongo.domain.User;
import com.andregarcia.workshopmongo.dto.AuthorDTO;
import com.andregarcia.workshopmongo.dto.CommentDTO;
import com.andregarcia.workshopmongo.repository.PostRepository;
import com.andregarcia.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.xml.stream.events.Comment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        LocalDate date = LocalDate.parse("21/03/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Post post1 = new Post(null, date, "Partiu Viagem", "Vou viajar para São Paulo. Abraços!",new AuthorDTO(maria));
        Post post2 = new Post(null, date.plusDays(2), "Bom dia", "Acordei feliz hoje!",new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!",date,new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!",date.minusDays(1),new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!",date.plusDays(2),new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
