package com.example.BookMyShowApplication.Services.Implementation;

import com.example.BookMyShowApplication.Dtos.RequestDto.MovieEntryDto;
import com.example.BookMyShowApplication.Model.Movie;
import com.example.BookMyShowApplication.Repositorys.MovieRepository;
import com.example.BookMyShowApplication.Services.MovieService;
import com.example.BookMyShowApplication.Transfermers.MovieTransefermers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public void addMovie(MovieEntryDto movieEntryDto) {
        Movie movie = MovieTransefermers.convertMovieDtoToEntity(movieEntryDto);
        movieRepository.save(movie);
    }

    public String movieWithMaximumNumberOfShows() {
        List<Movie> movieList = movieRepository.findAll();
        String movieName = "";
        int length = 0;
        for(Movie movie : movieList){
            if(movie.getShowList().size() > length){
                length = movie.getShowList().size();
                movieName = movie.getMovieName();
            }
        }
        return movieName;
    }
}
