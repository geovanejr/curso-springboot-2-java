package com.educandoweb.course.service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findall() {

        return orderRepository.findAll();
    }

    public Order findById(Long id) {

        Optional<Order> order = orderRepository.findById(id);

        return order.get();
    }
}
