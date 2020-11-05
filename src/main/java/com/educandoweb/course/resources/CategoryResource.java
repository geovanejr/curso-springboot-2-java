package com.educandoweb.course.resources;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findall() {

        List<Category> list = categoryService.findall();

        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);

    }
    
    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody Category category) {

        category = categoryService.insert(category);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").buildAndExpand(category).toUri();
        return ResponseEntity.created(uri).body(category);
    }
}
