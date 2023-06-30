package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDto.TheaterEntryDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.TheaterSeatsEntryDto;
import com.example.BookMyShowApplication.Exceptions.TheaterNotFoundException;

public interface TheaterService {
    void addTheater(TheaterEntryDto theaterEntryDto);
    void addTheaterSeatInsideTheTheater(TheaterSeatsEntryDto theaterSeatsEntryDto)throws TheaterNotFoundException;
}
