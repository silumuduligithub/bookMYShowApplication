package com.example.BookMyShowApplication.Controllers;

import com.example.BookMyShowApplication.Dtos.RequestDto.TicketListDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.TicketRequestDto;
import com.example.BookMyShowApplication.Dtos.ResponseDto.TicketResponseDto;
import com.example.BookMyShowApplication.Services.Implementation.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketServiceImpl ticketService;
    @PostMapping("/bookTicket")
    public ResponseEntity<TicketResponseDto> bookTicket(@RequestBody TicketRequestDto ticketRequestDto){
        try {
            TicketResponseDto ticketResponseDto = ticketService.bookTicket(ticketRequestDto);
            return new ResponseEntity<>(ticketResponseDto , HttpStatus.OK);
        }catch (Exception e){
            TicketResponseDto ticketResponseDto = new TicketResponseDto();
            ticketResponseDto.setStatusMessage(e.getMessage());
            ticketResponseDto.setStatusCode(400);
            return new ResponseEntity<>(ticketResponseDto, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/cancelTicket")
    public ResponseEntity<String> cancelTicket(@RequestParam int ticketId){
        try {
            ticketService.cancelTicket(ticketId);
            return new ResponseEntity<>("ticket cancelled successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAllTheTicketBookedByParticularUser")
    public ResponseEntity<TicketListDto> getAllTheTicketBookedByParticularUser(@RequestParam int userId){
        try {
            TicketListDto ticketListDto = ticketService.getAllTheTicketBookedByParticularUser(userId);
            return new ResponseEntity<>(ticketListDto, HttpStatus.OK);
        }catch (Exception e){
            TicketListDto ticketListDto = new TicketListDto();
            ticketListDto.setStatusMessage(e.getMessage());
            ticketListDto.setStatusCode(400);
            return new ResponseEntity<>(ticketListDto, HttpStatus.BAD_REQUEST);
        }
    }
}
