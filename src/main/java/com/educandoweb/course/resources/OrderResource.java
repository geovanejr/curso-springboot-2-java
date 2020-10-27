package com.educandoweb.course.resources;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findall() {

        List<Order> list = orderService.findall();

        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {

        Order order = orderService.findById(id);
        return ResponseEntity.ok().body(order);

    }
}
