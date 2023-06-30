package com.example.BookMyShowApplication.Model;

import com.example.BookMyShowApplication.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class TheaterSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String  seatNo;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    private  Theater theater;
}
