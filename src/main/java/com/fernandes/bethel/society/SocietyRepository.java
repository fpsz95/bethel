package com.fernandes.bethel.society;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Long> {
    //Optional<Society> findBySocietyName(String societyName);
    Optional<Society> findById(Long id);

    @Query("SELECT societyName FROM Societies")
    List findAllSocietyNames();
}
