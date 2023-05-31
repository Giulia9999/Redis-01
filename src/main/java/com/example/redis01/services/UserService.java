package com.example.redis01.services;

import com.example.redis01.entities.UserJPA;
import com.example.redis01.entities.UserRedis;
import com.example.redis01.repositories.UserJPARepo;
import com.example.redis01.repositories.UserRedisRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserJPARepo userJPARepo;
    UserRedisRepo userRedisRepo;
    public UserRedis convertData(UserJPA user){
        UserRedis userRedis = new UserRedis();
        BeanUtils.copyProperties(user, userRedis);
        return userRedis;
    }

    public UserJPA convertData(UserRedis user){
        UserJPA userRedis = new UserJPA();
        BeanUtils.copyProperties(user, userRedis);
        return userRedis;
    }

    public UserJPA create(UserJPA user) {
        if(user == null) return null;
        user.setId(null);
        return userJPARepo.save(user);
    }

    public List<UserJPA> readAll() {
        return userJPARepo.findAll();
    }

    public UserJPA readOne(Long id) {
        Optional<UserRedis> userRedis = userRedisRepo.findById(id);
        if(userRedis.isPresent()){
            return convertData(userRedis.get());
        }else {
            UserJPA userFromDB = userJPARepo.getReferenceById(id);

            userRedisRepo.save(convertData(userFromDB));
            return userFromDB;
        }
    }

    public UserJPA update(Long id,UserJPA user) {
        if(user == null) return null;
        user.setId(id);
        userJPARepo.save(user);

        Optional<UserRedis> userRedis = userRedisRepo.findById(id);
        if(userRedis.isPresent()) {
            userRedisRepo.deleteById(id);
        }

        return user;
    }

    public void delete(Long id) {
        userJPARepo.deleteById(id);
        userRedisRepo.deleteById(id);
    }
}
