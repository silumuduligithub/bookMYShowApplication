package com.example.BookMyShowApplication.Dtos.RequestDto;

import lombok.Data;

@Data
public class TheaterSeatsEntryDto {
    private String theaterLocation;
    private int numberOfClassicSeats;
    private int numberOfpremiumSeats;
    private int numberOfColums;
}
