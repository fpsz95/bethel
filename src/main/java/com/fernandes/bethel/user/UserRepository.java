package com.fernandes.bethel.user;

import com.fernandes.bethel.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAllBySocietyId(Long societyId);
    //User findById(Long id);
   // String save(String userProfileImageLink);
    //User findByUsername(String username);


}