package com.example.BookMyShowApplication.Dtos.RequestDto;

import com.example.BookMyShowApplication.Model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketListDto {
    List<Ticket> ticketList;
    String statusMessage;
    int statusCode;
}
