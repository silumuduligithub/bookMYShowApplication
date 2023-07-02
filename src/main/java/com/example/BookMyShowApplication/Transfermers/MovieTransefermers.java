package com.example.BookMyShowApplication.Transfermers;

import com.example.BookMyShowApplication.Dtos.RequestDto.MovieEntryDto;
import com.example.BookMyShowApplication.Model.Movie;

public class MovieTransefermers {
    public static Movie convertMovieDtoToEntity(MovieEntryDto movieEntryDto) {
        Movie movie = Movie.builder().movieName(movieEntryDto.getMovieName())
                .duration(movieEntryDto.getDuration())
                .ratting(movieEntryDto.getRatting())
                .releaseDate(movieEntryDto.getReleaseDate())
                .genre(movieEntryDto.getGenre())
                .language(movieEntryDto.getLanguage())
                .build();
        return movie;
    }
}
