package com.example.BookMyShowApplication.Repositorys;

import com.example.BookMyShowApplication.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
