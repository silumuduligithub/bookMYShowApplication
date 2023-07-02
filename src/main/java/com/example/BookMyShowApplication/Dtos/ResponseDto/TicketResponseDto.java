package com.example.BookMyShowApplication.Dtos.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {
    private String bookedSeats;
    private String movieName;
    private String theaterName;
    private String location;
    private Date date;
    private LocalTime time;
    private int price;
    private String statusMessage;
    private int statusCode;
}
