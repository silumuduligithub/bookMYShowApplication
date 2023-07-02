package com.example.BookMyShowApplication.Transfermers;

import com.example.BookMyShowApplication.Dtos.RequestDto.TicketListDto;
import com.example.BookMyShowApplication.Dtos.RequestDto.UserRequestDto;
import com.example.BookMyShowApplication.Dtos.ResponseDto.UserResponseDto;
import com.example.BookMyShowApplication.Model.Ticket;
import com.example.BookMyShowApplication.Model.User;

import java.util.List;

public class UserTransfermer {
    public static User convertUserDtoToEntity(UserRequestDto userRequestDto){
        User user = User.builder().name(userRequestDto.getName()).age(userRequestDto.getAge()).mobileNo(userRequestDto.getMobileNo()).email(userRequestDto.getEmail()).build();
        return user;
    }
    public static UserResponseDto convertEntityToUserResponseDto(User user){
        UserResponseDto userResponseDto = UserResponseDto.builder().name(user.getName())
                .age(user.getAge())
                .mobileNo(user.getMobileNo())
                .email(user.getEmail())
                .build();
        return userResponseDto;
    }

    public static TicketListDto convertListTicketToTicketListDtoEntiry(List<Ticket> ticketList) {
        TicketListDto ticketListDto = TicketListDto.builder()
                .ticketList(ticketList).build();
        return ticketListDto;
    }
}
