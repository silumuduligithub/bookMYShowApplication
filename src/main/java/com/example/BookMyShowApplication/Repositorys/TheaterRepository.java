package com.example.BookMyShowApplication.Repositorys;

import com.example.BookMyShowApplication.Model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Optional<Theater> findByLocation(String location);
}
