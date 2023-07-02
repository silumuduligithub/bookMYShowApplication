package com.example.BookMyShowApplication.Transfermers;

import com.example.BookMyShowApplication.Dtos.RequestDto.ShowEntryDto;
import com.example.BookMyShowApplication.Dtos.ResponseDto.TimeResponseDto;
import com.example.BookMyShowApplication.Model.Movie;
import com.example.BookMyShowApplication.Model.Show;

public class ShowTransfermers {

    public static Show convertShowDtoToShowEntity(ShowEntryDto showEntryDto) {
        Show show = Show.builder()
                .time(showEntryDto.getStartTime())
                .date(showEntryDto.getDate())
            .build();
        return show;
    }

    public static TimeResponseDto convertShowTimeDtoToEntity(Show show) {
        TimeResponseDto timeResponseDto = TimeResponseDto.builder()
                .time(show.getTime()).build();
        return timeResponseDto;
    }
}
