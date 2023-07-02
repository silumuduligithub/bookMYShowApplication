package com.example.BookMyShowApplication.Services.Implementation;

import com.example.BookMyShowApplication.Dtos.RequestDto.ShowEntryDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.ShowSeatDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.ShowTimeDto;
import com.example.BookMyShowApplication.Dtos.ResponseDto.TimeResponseDto;
import com.example.BookMyShowApplication.Enums.SeatType;
import com.example.BookMyShowApplication.Exceptions.MovieNotFound;
import com.example.BookMyShowApplication.Exceptions.ShowNotFound;
import com.example.BookMyShowApplication.Exceptions.TheaterNotFoundException;
import com.example.BookMyShowApplication.Model.*;
import com.example.BookMyShowApplication.Repositorys.MovieRepository;
import com.example.BookMyShowApplication.Repositorys.ShowRepository;
import com.example.BookMyShowApplication.Repositorys.TheaterRepository;
import com.example.BookMyShowApplication.Services.ShowService;
import com.example.BookMyShowApplication.Transfermers.ShowTransfermers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ShowRepository showRepository;
    @Override
    public void addShow(ShowEntryDto showEntryDto)throws MovieNotFound, TheaterNotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(showEntryDto.getMovieId());
        if(movieOptional.isEmpty()){
            throw  new MovieNotFound("Enter a valid movie name");
        }
        Optional<Theater> theaterOptional = theaterRepository.findById(showEntryDto.getTheaterId());
        if(theaterOptional.isEmpty()){
            throw new TheaterNotFoundException("enter a valid theater name");
        }
        Movie movie = movieOptional.get();
        Theater theater = theaterOptional.get();

        Show show = ShowTransfermers.convertShowDtoToShowEntity(showEntryDto);
        // sets the fourign key
        show.setMovie(movie);
        show.setTheater(theater);
        showRepository.save(show);

        movie.getShowList().add(show);
        movieRepository.save(movie);

        theater.getShowList().add(show);
        theaterRepository.save(theater);
    }
    @Override
    public void associateSeats(ShowSeatDto showSeatDto)throws ShowNotFound{
        Optional<Show> showOptional = showRepository.findById(showSeatDto.getShowId());
        if(showOptional.isEmpty()){
            throw new ShowNotFound("show not found");
        }
        Show show = showOptional.get();
        Theater theater = show.getTheater();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatsList();
        List<ShowSeat> showSeatList =show.getShowSeatList();
        for(TheaterSeat theaterSeat : theaterSeatList){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setId(theaterSeat.getId());
            showSeat.setSeatType(theaterSeat.getSeatType());
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            if(showSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showSeatDto.getPriceOfClassicSeat());
            }
            if(showSeat.getSeatType().equals(SeatType.PREMIUM)){
                showSeat.setPrice(showSeatDto.getPriceOfPremimumSeat());
            }
            showSeat.setShow(show);
            showSeat.setAvailable(true);
            showSeat.setFoodAttached(false);
            showSeatList.add(showSeat);
        }
        showRepository.save(show);
    }

    public String getMovieName(ShowEntryDto showEntryDto) {
        Movie movie = movieRepository.findById(showEntryDto.getMovieId()).get();
        return movie.getMovieName();
    }

    public TimeResponseDto getshowTimeOfAParticularMovieInaParticularTheater(ShowTimeDto showTimeDto)throws TheaterNotFoundException, MovieNotFound, ShowNotFound {
        Optional<Theater> theaterOptional = theaterRepository.findById(showTimeDto.getTheaeterId());
        if(theaterOptional.isEmpty()){
            throw new TheaterNotFoundException("invalid theater name");
        }
        Optional<Movie> movieOptional = movieRepository.findById(showTimeDto.getShowId());
        if(movieOptional.isEmpty()){
            throw new MovieNotFound("invalid movie name");
        }
        Theater theater = theaterOptional.get();
        Movie movie = movieOptional.get();
        List<Show> showList = theater.getShowList();
        if(showList.contains(movie)){
            for(Show show : showList) {
                if (show.getMovie().getMovieId() == movie.getMovieId()) {
                    return ShowTransfermers.convertShowTimeDtoToEntity(show);
                }
            }
        }
        throw new ShowNotFound("show not available");
    }
}
