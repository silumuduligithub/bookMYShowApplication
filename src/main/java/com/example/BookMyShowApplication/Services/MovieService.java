package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDto.CollectionDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.MovieEntryDto;
import com.example.BookMyShowApplication.Exceptions.MovieNotFound;

public interface MovieService {
    public void addMovie(MovieEntryDto movieEntryDto);
    public String movieWithMaximumNumberOfShows();
    public int totalCollectionOfAParticularMovie(int movieId) throws MovieNotFound;
    public String movieHitOrFlop(CollectionDto collectionDto)throws MovieNotFound ;
}
