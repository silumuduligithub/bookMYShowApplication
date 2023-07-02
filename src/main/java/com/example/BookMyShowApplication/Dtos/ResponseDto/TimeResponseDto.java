package com.example.BookMyShowApplication.Dtos.ResponseDto;

import com.example.BookMyShowApplication.Model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeResponseDto {
    private LocalTime time;
    private String statusMessage;
    private int statusCode;

}
