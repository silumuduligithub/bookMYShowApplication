package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDto.TicketRequestDto;
import com.example.BookMyShowApplication.Dtos.ResponseDto.TicketResponseDto;
import com.example.BookMyShowApplication.Exceptions.SeatNotAvailable;
import com.example.BookMyShowApplication.Exceptions.ShowNotFound;
import com.example.BookMyShowApplication.Exceptions.TicketNotFoundException;
import com.example.BookMyShowApplication.Exceptions.UserNotfound;

public interface TicketService {
    public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto)throws UserNotfound, ShowNotFound, SeatNotAvailable;
    public void cancelTicket(int ticketId)throws TicketNotFoundException;
}
