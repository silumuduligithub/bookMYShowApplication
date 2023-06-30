package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDto.UserRequestDto;
import com.example.BookMyShowApplication.Dtos.ResponseDto.UserResponseDto;
import com.example.BookMyShowApplication.Exceptions.EmailIdNotFound;
import com.example.BookMyShowApplication.Exceptions.UserNotfound;

public interface UserService {
    void addUser(UserRequestDto userRequestDto)throws EmailIdNotFound;
    UserResponseDto getOldestUser()throws UserNotfound;
}
