package com.educandoweb.course.resources;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/users", method = RequestMethod.GET)
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findall() {

        List<User> list = userService.findall();

        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {

        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);

    }
}
