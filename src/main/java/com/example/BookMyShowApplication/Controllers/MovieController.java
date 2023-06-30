package com.example.BookMyShowApplication.Controllers;

import com.example.BookMyShowApplication.Dtos.RequestDto.MovieEntryDto;
import com.example.BookMyShowApplication.Services.Implementation.MovieServiceImpl;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieServiceImpl movieService = new MovieServiceImpl();
    @PostMapping("/add_movie")
    public ResponseEntity<String> addMovie(MovieEntryDto movieEntryDto){

        return new ResponseEntity<>("movie has been added successfully", HttpStatus.OK);
    }
}
