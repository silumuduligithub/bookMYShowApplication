package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDto.TheaterEntryDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.TheaterSeatsEntryDto;
import com.example.BookMyShowApplication.Exceptions.TheaterNotFoundException;
import com.example.BookMyShowApplication.Model.Theater;

import java.util.List;

public interface TheaterService {
    void addTheater(TheaterEntryDto theaterEntryDto);
    void addTheaterSeatInsideTheTheater(TheaterSeatsEntryDto theaterSeatsEntryDto)throws TheaterNotFoundException;
    public int uniqueLocationsOfAtheater(int theaterId);
    public List<Theater> listOfTheatersShowingAparticularTime(int showId);
}
