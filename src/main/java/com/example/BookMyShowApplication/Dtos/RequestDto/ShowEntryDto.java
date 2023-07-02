package com.example.BookMyShowApplication.Dtos.RequestDto;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
@Data
public class ShowEntryDto {
    private  int movieId;
    private int theaterId;
    private LocalTime startTime;
    private Date date;
}
