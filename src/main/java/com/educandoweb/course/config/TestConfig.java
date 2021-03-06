package com.educandoweb.course.config;

import com.educandoweb.course.entities.*;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.*;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

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

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "https://www.amazon.com.br/Lord-Rings-J-R-Tolkien/dp/0618640150");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll((Arrays.asList(p1, p2, p3, p4, p5)));

        p1.getCategories().add(cat2);
        p2.getCategories().addAll(Arrays.asList(cat1, cat3));
        p3.getCategories().addAll(Arrays.asList(cat3));
        p4.getCategories().addAll(Arrays.asList(cat3));
        p5.getCategories().addAll(Arrays.asList(cat2));

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Instant payInstant = o1.getMoment().plusSeconds(600);
        Payment pay1 = new Payment(null, payInstant, o1);

        o1.setPayment(pay1);
        orderRepository.save(o1);

    }
}
