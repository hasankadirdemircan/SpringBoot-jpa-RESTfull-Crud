package com.hkdemircan.restcrudwithjpa.controller;

import com.hkdemircan.restcrudwithjpa.model.User;
import com.hkdemircan.restcrudwithjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id){
       User user = userRepository.findFirstById(id);
        System.out.println(user.getFirstName()+user.getLastName());
        if(null == user){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();

    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User userDetails) {
        User user = userRepository.findFirstById(id);
        if(null == user) {
            return ResponseEntity.notFound().build();
        }
        User updatedUser = userRepository.save(userDetails);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {
        User user = userRepository.findFirstById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
