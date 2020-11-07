package com.educandoweb.course.service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.service.exceptions.DatabaseException;
import com.educandoweb.course.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findall() {

        return userRepository.findAll();
    }

    public User findById(Long id) {

        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insertUser(User user) {

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {

        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw  new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User updateUser(Long id, User user) {

        User entity = userRepository.getOne(id);
        updateData(entity, user);
        return userRepository.save(entity);
    }

    private void updateData(User entity, User user) {

        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }
}
