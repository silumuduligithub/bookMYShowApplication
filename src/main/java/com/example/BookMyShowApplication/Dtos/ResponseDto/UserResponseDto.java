package com.example.BookMyShowApplication.Dtos.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String name;
    private Integer age;
    private String mobileNo;
    private String email;
    private String statusCode;
    private String statusMethod;
}
