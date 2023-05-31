package com.example.redis01.repositories;

import com.example.redis01.entities.UserRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisRepo extends CrudRepository<UserRedis, Long> {
}
