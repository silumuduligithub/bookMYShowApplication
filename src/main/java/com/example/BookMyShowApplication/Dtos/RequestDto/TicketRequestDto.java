package com.example.BookMyShowApplication.Dtos.RequestDto;

import com.example.BookMyShowApplication.Model.ShowSeat;
import lombok.Data;

import java.util.List;

@Data
public class TicketRequestDto {
    private int userId;
    private int showId;
    List<String> requestedSeats;
}
