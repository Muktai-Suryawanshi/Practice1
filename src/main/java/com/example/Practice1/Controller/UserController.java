package com.example.Practice1.Controller;

import com.example.Practice1.DTO.UserDto;
import com.example.Practice1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

   /** public UserController(UserService userService){
        this.userService = userService;
    }**/

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserDto> getAllUsers(){ 
        return userService.getAllUsers();
    }

    @GetMapping("/role/{role}")
    public List<UserDto> getUserByRole(@PathVariable String role){
        return userService.getUsersByRole(role);
    }
}
