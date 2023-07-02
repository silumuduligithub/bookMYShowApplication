package com.example.BookMyShowApplication.Controllers;

import com.example.BookMyShowApplication.Dtos.RequestDto.TheaterEntryDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.TheaterSeatsEntryDto;
import com.example.BookMyShowApplication.Model.Theater;
import com.example.BookMyShowApplication.Services.Implementation.TheaterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    private TheaterServiceImpl theaterService;
    @PostMapping("/addTheater")
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDto theaterEntryDto){
        theaterService.addTheater(theaterEntryDto);
        return new ResponseEntity<>("theater has been added succcessfully", HttpStatus.OK);
    }
    @PostMapping("/addTheaterSeats")
    public ResponseEntity<String> addTheaterSeatInsideTheTheater(@RequestBody TheaterSeatsEntryDto theaterSeatsEntryDto){
        try{
            theaterService.addTheaterSeatInsideTheTheater(theaterSeatsEntryDto);
            return new ResponseEntity<>("theaterSeats has been added success fully inside the theater", HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/uniqueLocationsOfATheater")
    public ResponseEntity<Integer> uniqueLocationsOfAtheater(@RequestParam int theaterId){
        int uniqueLocations = theaterService.uniqueLocationsOfAtheater(theaterId);
        return new ResponseEntity<>(uniqueLocations, HttpStatus.OK);
    }
    @GetMapping("/listOfTheatersShowintAparticularTime")
    public ResponseEntity<List<Theater>> listOfTheatersShowingAparticularTime(@RequestParam int showId){
        List<Theater> theaterList = theaterService.listOfTheatersShowingAparticularTime(showId);
        return new ResponseEntity<>(theaterList, HttpStatus.OK);
    }
}
