package com.example.BookMyShowApplication.Controllers;

import com.example.BookMyShowApplication.Dtos.RequestDto.ShowEntryDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.ShowSeatDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.ShowTimeDto;
import com.example.BookMyShowApplication.Dtos.ResponseDto.TimeResponseDto;
import com.example.BookMyShowApplication.Services.Implementation.ShowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowServiceImpl showService;
    @PostMapping("/addShow")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto){
        try {
            showService.addShow(showEntryDto);
            return new ResponseEntity<>("show has been added successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/associateSeats")
    public ResponseEntity<String> associatedSeats(@RequestBody ShowSeatDto showSeatDto){
        try{
            showService.associateSeats(showSeatDto);
            return new ResponseEntity<>("showSeat associated successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getMovieName")
    public ResponseEntity<String> getMovieName(@RequestBody ShowEntryDto showEntryDto){
        String movieName = showService.getMovieName(showEntryDto);
        return new ResponseEntity<>(movieName, HttpStatus.OK);
    }
    @GetMapping("/getshowTimeOfAmovieInATheater")
    public ResponseEntity<TimeResponseDto> getshowTimeOfAParticularMovieInaParticularTheater(ShowTimeDto showTimeDto){
        try {
            TimeResponseDto time = showService.getshowTimeOfAParticularMovieInaParticularTheater(showTimeDto);
            return new ResponseEntity<>(time, HttpStatus.OK);
        }catch (Exception e){
            TimeResponseDto timeResponseDto = new TimeResponseDto();
            timeResponseDto.setStatusMessage(e.getMessage());
            timeResponseDto.setStatusCode(400);
            return new ResponseEntity<>(timeResponseDto, HttpStatus.BAD_REQUEST);
        }
    }
}
