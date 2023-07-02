package com.example.BookMyShowApplication.Dtos.RequestDto;

import lombok.Data;

@Data
public class ShowSeatDto {
    private int showId;
    private int priceOfClassicSeat;
    private int priceOfPremimumSeat;
}
