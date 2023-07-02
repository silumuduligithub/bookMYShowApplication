package com.example.BookMyShowApplication.Controllers;

import com.example.BookMyShowApplication.Dtos.RequestDto.CollectionDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.MovieEntryDto;
import com.example.BookMyShowApplication.Services.Implementation.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieServiceImpl movieService = new MovieServiceImpl();

    @PostMapping("/add_movie")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto) {
        movieService.addMovie(movieEntryDto);
        return new ResponseEntity<>("movie has been added successfully", HttpStatus.OK);
    }

    @GetMapping("/movieWithMaximumNumberOfShows")
    public ResponseEntity<String> movieWithMaximumNumberOfShows() {
        String movieName = movieService.movieWithMaximumNumberOfShows();
        return new ResponseEntity<>(movieName, HttpStatus.OK);
    }

    @GetMapping("/totalCollectionOfAparticularMovie")
    public ResponseEntity<String> totalCollectionOfAParticularMovie(@RequestParam int movieId) {
        try {
            int totalCollection = movieService.totalCollectionOfAParticularMovie(movieId);
            return new ResponseEntity<>(totalCollection+"", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/movieHitOrFlop")
    public ResponseEntity<String> movieHitOrFlop(@RequestBody CollectionDto collectionDto){
        try {
            String res = movieService.movieHitOrFlop(collectionDto);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
