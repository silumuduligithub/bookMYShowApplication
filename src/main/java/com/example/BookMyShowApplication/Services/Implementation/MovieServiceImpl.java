package com.example.BookMyShowApplication.Services.Implementation;

import com.example.BookMyShowApplication.Dtos.RequestDto.CollectionDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.MovieEntryDto;
import com.example.BookMyShowApplication.Exceptions.MovieNotFound;
import com.example.BookMyShowApplication.Model.Movie;
import com.example.BookMyShowApplication.Model.Show;
import com.example.BookMyShowApplication.Model.Theater;
import com.example.BookMyShowApplication.Model.Ticket;
import com.example.BookMyShowApplication.Repositorys.MovieRepository;
import com.example.BookMyShowApplication.Repositorys.TheaterRepository;
import com.example.BookMyShowApplication.Services.MovieService;
import com.example.BookMyShowApplication.Transfermers.MovieTransefermers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Override
    public void addMovie(MovieEntryDto movieEntryDto) {
        Movie movie = MovieTransefermers.convertMovieDtoToEntity(movieEntryDto);
        movieRepository.save(movie);
    }
    @Override
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
    @Override
    public int totalCollectionOfAParticularMovie(int movieId) throws MovieNotFound{
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if(movieOptional.isEmpty()){
            throw new MovieNotFound("invalid movie id");
        }
        Movie movie = movieOptional.get();
       List<Show> showList = movie.getShowList();
       int totalPrice = 0;
       for(Show show : showList){
           List<Ticket> ticketList = show.getTicketList();
           for(Ticket ticket : ticketList){
               totalPrice += ticket.getTotalPrice();
           }
       }
       return totalPrice;
    }
    @Override
    public String movieHitOrFlop(CollectionDto collectionDto)throws MovieNotFound {
        try {
            int totalCollection = totalCollectionOfAParticularMovie(collectionDto.getMovieId());
            if(totalCollection < collectionDto.getBudjet()) {
                return "flop";
            }else if(totalCollection == collectionDto.getBudjet()){
                return "average";
            }else{
                return "hit";
            }
        }catch (Exception e){
           throw new MovieNotFound(e.getMessage());
        }
    }
}
