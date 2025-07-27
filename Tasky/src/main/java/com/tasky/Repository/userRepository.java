package com.tasky.Repository;

import com.tasky.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public interface userRepository extends MongoRepository<User,Object>
{
    Optional<User> findByuserName(String userName);
    void deleteByuserName(String Name);
}
