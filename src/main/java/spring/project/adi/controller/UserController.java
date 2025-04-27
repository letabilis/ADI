package spring.project.adi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.project.adi.dto.CreateUserDTO;
import spring.project.adi.dto.GetUserDTO;
import spring.project.adi.dto.UpdateUserDTO;
import spring.project.adi.model.User;
import spring.project.adi.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;







@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO createUserDTO) {
        
        var userId = this.userService.createUser(createUserDTO);
        return ResponseEntity.created(URI.create("/users/" + userId.toString())).build();
    }
    
    @GetMapping()
    public ResponseEntity<List<GetUserDTO>> listUsers() {
        var userDTOList = this.userService.listUsers();
        return userDTOList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(userDTOList);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<GetUserDTO>> getUserById(@PathVariable("userId") UUID userId) {
        var userDTO = this.userService.getUserById(userId);
        
        return userDTO.isPresent() ? ResponseEntity.ok(userDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") UUID userId, @RequestBody UpdateUserDTO updateUserDTO) {
        this.userService.updateUserById(userId, updateUserDTO); 
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") UUID userId) {
        this.userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}   
