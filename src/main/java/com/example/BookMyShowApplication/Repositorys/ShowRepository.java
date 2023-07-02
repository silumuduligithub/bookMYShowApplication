package com.example.BookMyShowApplication.Repositorys;

import com.example.BookMyShowApplication.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Integer> {
}
