package com.jashan.queue_forge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jashan.queue_forge.models.Users;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUserName(String username);

}
