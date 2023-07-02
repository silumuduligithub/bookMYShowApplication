package com.example.BookMyShowApplication.Services.Implementation;

import com.example.BookMyShowApplication.Dtos.RequestDto.TheaterEntryDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.TheaterSeatsEntryDto;
import com.example.BookMyShowApplication.Enums.SeatType;
import com.example.BookMyShowApplication.Exceptions.TheaterNotFoundException;
import com.example.BookMyShowApplication.Model.Show;
import com.example.BookMyShowApplication.Model.Theater;
import com.example.BookMyShowApplication.Model.TheaterSeat;
import com.example.BookMyShowApplication.Repositorys.ShowRepository;
import com.example.BookMyShowApplication.Repositorys.TheaterRepository;
import com.example.BookMyShowApplication.Services.TheaterService;
import com.example.BookMyShowApplication.Transfermers.TheaterTransfermers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;

@Service
public class TheaterServiceImpl implements TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ShowRepository showRepository;
    @Override
    public void addTheater(TheaterEntryDto theaterEntryDto) {
        Theater theater = TheaterTransfermers.convertTheaterDtoToEntity(theaterEntryDto);
        theaterRepository.save(theater);
    }
    @Override
    public void addTheaterSeatInsideTheTheater(TheaterSeatsEntryDto theaterSeatsEntryDto)throws TheaterNotFoundException {
        Optional<Theater> theaterOptional = theaterRepository.findByLocation(theaterSeatsEntryDto.getTheaterLocation());
        if(theaterOptional.isEmpty()){
            throw new TheaterNotFoundException("please enter a valid theater location");
        }
        Theater theater = theaterOptional.get();
        int numberOfClassicSeats = theaterSeatsEntryDto.getNumberOfClassicSeats();
        int numberOfPremiumSeats = theaterSeatsEntryDto.getNumberOfpremiumSeats();
        int numberOfColumns = theaterSeatsEntryDto.getNumberOfColums();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatsList();
        int counter = 1;
        char ch = 'A';
        for(int count = 0; count < numberOfClassicSeats; count++){
            String seatNumber = counter +""+ ch;
            ch++;
            if((ch - 'A') == numberOfColumns){
                counter++;
                ch = 'A';
            }
            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatNo(seatNumber);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeatList.add(theaterSeat);
        }
        counter = 1;
        ch = 'A';
        for(int count = 0; count < numberOfPremiumSeats; count++){
            String seatNumber = counter + ""+ ch;
            ch++;
            if((ch - 'A') == numberOfColumns){
                counter++;
                ch = 'A';
            }
            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatNo(seatNumber);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeatList.add(theaterSeat);
        }
        theaterRepository.save(theater);
    }
    @Override
    public int uniqueLocationsOfAtheater(int theaterId) {
        Set<Integer> shows = new HashSet<>();
        List<Show> showList = theaterRepository.findById(theaterId).get().getShowList();
        int count = 0;
        for(Show show : showList){
            if(!shows.contains(show.getMovie().getMovieId())){
                count++;
            }
            shows.add(show.getMovie().getMovieId());
        }
        return count;
    }
    @Override
    public List<Theater> listOfTheatersShowingAparticularTime(int showId) {
        Show show = showRepository.findById(showId).get();
        List<Theater> theaterList = theaterRepository.findAll();
        List<Theater> ans = new ArrayList<>();
        for(Theater theater : theaterList) {
            List<Show> showList = theater.getShowList();
            for (Show show1 : showList) {
                if (show.getTime() == show1.getTime()) {
                    ans.add(theater);
                    break;
                }
            }
        }
        return ans;
    }
}
