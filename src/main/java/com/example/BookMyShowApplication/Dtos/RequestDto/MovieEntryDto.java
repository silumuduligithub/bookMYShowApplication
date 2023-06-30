package com.example.BookMyShowApplication.Dtos.RequestDto;

import com.example.BookMyShowApplication.Enums.Genre;
import com.example.BookMyShowApplication.Enums.LANGUAGE;
import lombok.Data;

import java.util.Date;
@Data
public class MovieEntryDto {
    private String movieName;
    private double duration;
    private double ratting;
    private Date releaseDate;
    private Genre genre;
    private LANGUAGE language;
}
