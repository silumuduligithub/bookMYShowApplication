package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDto.ShowEntryDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.ShowSeatDto;
import com.example.BookMyShowApplication.Exceptions.MovieNotFound;
import com.example.BookMyShowApplication.Exceptions.ShowNotFound;
import com.example.BookMyShowApplication.Exceptions.TheaterNotFoundException;

public interface ShowService {
    public void addShow(ShowEntryDto showEntryDto)throws MovieNotFound, TheaterNotFoundException;
    public void associateSeats(ShowSeatDto showSeatDto)throws ShowNotFound;
    public String getMovieName(ShowEntryDto showEntryDto);
}
