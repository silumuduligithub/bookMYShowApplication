package com.example.BookMyShowApplication.Transfermers;

import com.example.BookMyShowApplication.Dtos.RequestDto.TheaterEntryDto;
import com.example.BookMyShowApplication.Model.Theater;

public class TheaterTransfermers {
    public static Theater convertTheaterDtoToEntity(TheaterEntryDto theaterEntryDto){
        Theater theater = Theater.builder().theaterName(theaterEntryDto.getTheaterName())
                .location(theaterEntryDto.getLocation()).build();
        return theater;
    }
}
