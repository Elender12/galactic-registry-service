package com.galactic.first.registry.repository;


import com.galactic.first.registry.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;



@Repository
public interface UserRepository
{

    public User save(User user);


    public void deleteById( UUID id );


    public User findById(UUID id );


    public List<User> findAll();
    User findByUsername(String username);

}
