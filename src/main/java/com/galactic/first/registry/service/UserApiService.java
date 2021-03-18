package com.galactic.first.registry.service;



import com.galactic.first.registry.model.User;

import java.util.List;
import java.util.UUID;

public interface UserApiService
{

   List<User> findAll();


     User save(User user);


    User edit(User user);


    User findById(UUID id );


    User delete(UUID id );

}
