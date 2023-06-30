package com.example.BookMyShowApplication.Controllers;

import com.example.BookMyShowApplication.Dtos.RequestDto.UserRequestDto;
import com.example.BookMyShowApplication.Dtos.ResponseDto.UserResponseDto;

import com.example.BookMyShowApplication.Services.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto userRequestDto){
        try{
            userService.addUser(userRequestDto);
            return new ResponseEntity<>("user added successfullu", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getOldestUser")
    public ResponseEntity<UserResponseDto> getOldestUser(){
        try {
            UserResponseDto oldestUser = userService.getOldestUser();
            oldestUser.setStatusCode("200");
            oldestUser.setStatusMethod("SUCCESS");
            return new ResponseEntity<>(oldestUser, HttpStatus.OK);
        }catch (Exception e){
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setStatusCode("404");
            userResponseDto.setStatusMethod("Not Found");
            return new ResponseEntity<>(userResponseDto, HttpStatus.NOT_FOUND);
        }
    }
}
