package com.example.BookMyShowApplication.Services.Implementation;

import com.example.BookMyShowApplication.Dtos.RequestDto.UserRequestDto;
import com.example.BookMyShowApplication.Dtos.ResponseDto.UserResponseDto;
import com.example.BookMyShowApplication.Exceptions.EmailIdNotFound;
import com.example.BookMyShowApplication.Exceptions.UserNotfound;
import com.example.BookMyShowApplication.Model.User;
import com.example.BookMyShowApplication.Repositorys.UserRepository;
import com.example.BookMyShowApplication.Services.UserService;
import com.example.BookMyShowApplication.Transfermers.UserTransfermer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void addUser(UserRequestDto userRequestDto)throws EmailIdNotFound {
        if(userRequestDto.getEmail() == null)throw new EmailIdNotFound("enter a valid email id");
        User user = UserTransfermer.convertUserDtoToEntity(userRequestDto);
        userRepository.save(user);
    }

    @Override
    public UserResponseDto getOldestUser()throws UserNotfound {
        List<User> usersList = userRepository.findAll();
        int curAge = 0;
        User OldestUser = null;
        for(User user : usersList){
            if(user.getAge() > curAge){
                curAge = user.getAge();
                OldestUser = user;
            }
        }
        if(OldestUser == null){
            throw new UserNotfound("User Not Found");
        }
        UserResponseDto userResponseDto = UserTransfermer.convertEntityToUserResponseDto(OldestUser);
        return userResponseDto;
    }
}
