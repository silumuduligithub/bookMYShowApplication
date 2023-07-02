package com.example.BookMyShowApplication.Repositorys;

import com.example.BookMyShowApplication.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
