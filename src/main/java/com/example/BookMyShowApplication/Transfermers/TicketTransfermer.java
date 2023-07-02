package com.example.BookMyShowApplication.Transfermers;

import com.example.BookMyShowApplication.Dtos.ResponseDto.TicketResponseDto;
import com.example.BookMyShowApplication.Model.Ticket;

public class TicketTransfermer {
    public static TicketResponseDto convertTicketResponseDtoTOTicketResponseEntity(Ticket ticket) {
        TicketResponseDto ticketResponseDto = TicketResponseDto.builder()
                .bookedSeats(ticket.getBookedSeatsNumber())
                .movieName(ticket.getMovieName())
                .theaterName(ticket.getTheaterName())
                .location(ticket.getLocation())
                .date(ticket.getDate())
                .time(ticket.getTime())
                .build();
        return ticketResponseDto;
    }
}
