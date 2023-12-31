package com.example.BookMyShowApplication.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table
@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movieName;
    private String theaterName;
    private String location;
    private Date date;
    private LocalTime time;
    private int totalSeats;
    private String bookedSeatsNumber;
    private List<String> showSeatList;
    private int totalPrice;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Show show;
}
