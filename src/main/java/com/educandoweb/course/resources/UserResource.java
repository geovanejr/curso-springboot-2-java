package com.educandoweb.course.resources;

import com.educandoweb.course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users", method = RequestMethod.GET)
public class UserResource {

    @GetMapping
    public ResponseEntity<User> findall() {

        User user = new User(1L, "Geovane Junior", "geovane.gjunior@gmail.com", "11999468056", "123");

        System.out.println(user);
        return ResponseEntity.ok().body(user);
    }
}
