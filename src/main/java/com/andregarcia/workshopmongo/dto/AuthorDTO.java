package com.andregarcia.workshopmongo.dto;

import com.andregarcia.workshopmongo.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthorDTO implements Serializable {
    private String id;
    private String name;

    public AuthorDTO() {
    }
    public AuthorDTO(User user){
        id = user.getId();
        name = user.getName();
    }
}
