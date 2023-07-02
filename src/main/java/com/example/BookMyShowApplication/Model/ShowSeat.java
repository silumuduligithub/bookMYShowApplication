package com.example.BookMyShowApplication.Model;

import com.example.BookMyShowApplication.Enums.SeatType;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private boolean isAvailable;
    private boolean isFoodAttached;
    private int price;
    @ManyToOne
    @JoinColumn
    Show show;
}
