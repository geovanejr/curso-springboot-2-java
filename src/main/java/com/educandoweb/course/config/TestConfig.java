package com.educandoweb.course.config;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        User u3 = new User(null, "Geovane Junior", "geovane.gjunior@gmail.com", "11999468056", "1234");

        Order o1 = new Order(null, LocalDateTime.now().toInstant(ZoneOffset.UTC), OrderStatus.CANCELED, u1);

        Thread.sleep(500);
        Order o2 = new Order(null, LocalDateTime.now().toInstant(ZoneOffset.UTC), OrderStatus.DELIVERED, u2);
        Thread.sleep(500);
        Order o3 = new Order(null, LocalDateTime.now().toInstant(ZoneOffset.UTC), OrderStatus.PAID, u1);
        Thread.sleep(500);
        Order o4 = new Order(null, LocalDateTime.now().toInstant(ZoneOffset.UTC), OrderStatus.WAITING_PAYMENT, u3);

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

    }
}
