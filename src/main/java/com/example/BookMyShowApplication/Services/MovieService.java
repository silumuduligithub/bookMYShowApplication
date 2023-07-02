package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDto.MovieEntryDto;

public interface MovieService {
    public void addMovie(MovieEntryDto movieEntryDto);
    public String movieWithMaximumNumberOfShows();
}
