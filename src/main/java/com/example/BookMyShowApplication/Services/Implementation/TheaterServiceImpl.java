package com.example.BookMyShowApplication.Services.Implementation;

import com.example.BookMyShowApplication.Dtos.RequestDto.TheaterEntryDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.TheaterSeatsEntryDto;
import com.example.BookMyShowApplication.Enums.SeatType;
import com.example.BookMyShowApplication.Exceptions.TheaterNotFoundException;
import com.example.BookMyShowApplication.Model.Theater;
import com.example.BookMyShowApplication.Model.TheaterSeat;
import com.example.BookMyShowApplication.Repositorys.TheaterRepository;
import com.example.BookMyShowApplication.Services.TheaterService;
import com.example.BookMyShowApplication.Transfermers.TheaterTransfermers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterServiceImpl implements TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;
    public void addTheater(TheaterEntryDto theaterEntryDto) {
        Theater theater = TheaterTransfermers.convertTheaterDtoToEntity(theaterEntryDto);
        theaterRepository.save(theater);
    }

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
}
