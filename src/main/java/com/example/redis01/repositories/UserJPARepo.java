package com.example.redis01.repositories;

import com.example.redis01.entities.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface UserJPARepo extends JpaRepository<UserJPA, Long> {
}
