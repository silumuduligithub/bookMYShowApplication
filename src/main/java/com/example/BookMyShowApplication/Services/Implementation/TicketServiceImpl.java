package com.example.BookMyShowApplication.Services.Implementation;

import com.example.BookMyShowApplication.Dtos.RequestDto.TicketRequestDto;
import com.example.BookMyShowApplication.Dtos.ResponseDto.TicketResponseDto;
import com.example.BookMyShowApplication.Exceptions.SeatNotAvailable;
import com.example.BookMyShowApplication.Exceptions.ShowNotFound;
import com.example.BookMyShowApplication.Exceptions.TicketNotFoundException;
import com.example.BookMyShowApplication.Exceptions.UserNotfound;
import com.example.BookMyShowApplication.Model.*;
import com.example.BookMyShowApplication.Repositorys.ShowRepository;
import com.example.BookMyShowApplication.Repositorys.TicketRepository;
import com.example.BookMyShowApplication.Repositorys.UserRepository;
import com.example.BookMyShowApplication.Services.TicketService;
import com.example.BookMyShowApplication.Transfermers.TicketTransfermer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private TicketRepository ticketRepository;
//    @Autowired
//    private JavaMailSender mailSender;
    @Override
    public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto)throws UserNotfound, ShowNotFound, SeatNotAvailable {
        Optional<User> userOptional = userRepository.findById(ticketRequestDto.getUserId());
        if(userOptional.isEmpty()){
            throw new UserNotfound("User not preset");
        }
        Optional<Show> showOptional = showRepository.findById(ticketRequestDto.getShowId());
        if(showOptional.isEmpty()){
            throw new ShowNotFound("show not present");
        }
        User user = userOptional.get();
        Show show = showOptional.get();
        boolean isValid = validateShowSeat(show, ticketRequestDto.getRequestedSeats());
        if(isValid == false){
            throw new SeatNotAvailable("requested Seats are not available");
        }
        Ticket ticket = new Ticket();
        int price = calculetTotalPrice(show, ticketRequestDto.getRequestedSeats());
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setTheaterName(show.getTheater().getTheaterName());
        ticket.setLocation(show.getTheater().getLocation());
        ticket.setDate(show.getDate());
        ticket.setTime(show.getTime());
        ticket.setTotalPrice(price);
        ticket.setTotalSeats(ticketRequestDto.getRequestedSeats().size());
        ticket.setShowSeatList(ticketRequestDto.getRequestedSeats());
        ticket.setBookedSeatsNumber(convertStringListTOString(ticketRequestDto.getRequestedSeats()));
        show.getTicketList().add(ticket);
        user.getTicketList().add(ticket);

        showRepository.save(show);
        userRepository.save(user);
        ticketRepository.save(ticket);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String body = "Hi" + " " + user.getName()+"!\n"+
                "you have successfully booked a ticket. please find the following details"+"\n"+
                "total Booked Seats"+ticket.getTotalSeats() +" "+ "seats are" + ticket.getBookedSeatsNumber()+"\n"+
                "movie Name"+ticket.getMovieName()+"\n"+
                "theater name"+ticket.getTheaterName()+"\n"+
                "show date is" + ticket.getDate()+" "+"and show time is"+ticket.getTime()+"\n"+
                "price" + ticket.getTotalPrice()+"\n"+
                "enjoy the show";
        simpleMailMessage.setSubject("ticket conformation mail");
        simpleMailMessage.setFrom("projectemail@gmail.com");
        simpleMailMessage.setText(body);
        simpleMailMessage.setTo(ticket.getUser().getEmail());
//        mailSender.send(simpleMailMessage);

       return TicketTransfermer.convertTicketResponseDtoTOTicketResponseEntity(ticket);
    }

    private String convertStringListTOString(List<String> seats) {
        String ans = "";
        for(String seat : seats){
            ans = ans + "," + seat;
        }
        return ans;
    }

    private int calculetTotalPrice(Show show, List<String> requestedSeats) {
        List<ShowSeat> showSeatList = show.getShowSeatList();
        int totalPrice = 0;
        for(ShowSeat showSeat : showSeatList){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                totalPrice += showSeat.getPrice();
                showSeat.setAvailable(false);
            }
        }
        return totalPrice;
    }

    private boolean validateShowSeat(Show show, List<String> requestedSeats) {
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat : showSeatList){
            String seatNo = showSeat.getSeatNo();
            if(requestedSeats.contains(seatNo)){
                if(showSeat.isAvailable() == false){
                    return false;
                }
            }
        }
        return true;
    }

    public void cancelTicket(int ticketId)throws TicketNotFoundException {
        Optional<Ticket> ticketOpotional = ticketRepository.findById(ticketId);
        if(ticketOpotional.isEmpty()){
            throw new TicketNotFoundException("invalid ticket");
        }
        Ticket ticket = ticketOpotional.get();
        Movie movie = ticket.getShow().getMovie();
        Show show = ticket.getShow();
        List<String> ticketSeatList = ticket.getShowSeatList();
        List<Ticket> ticketList = show.getTicketList();
        for(Ticket ticket1 : ticketList){
            if(ticket1.getId() == ticket.getId()){
                ticketList.remove(ticket1);
            }
        }
        cancelShowSeat(show, ticketSeatList);
    }
    public void cancelShowSeat(Show show, List<String> seats){
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat : showSeatList){
            if(seats.contains(showSeat.getSeatNo())){
                showSeat.setAvailable(true);
                showSeat.setFoodAttached(false);
            }
        }
    }
}
