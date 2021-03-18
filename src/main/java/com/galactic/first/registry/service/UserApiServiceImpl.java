package com.galactic.first.registry.service;


import com.galactic.first.registry.exception.TemplateNotFoundException;
import com.galactic.first.registry.model.User;
import com.galactic.first.registry.monitoring.Monitoring;
import com.galactic.first.registry.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;



@Service
public class UserApiServiceImpl implements UserApiService
{
    @Autowired
    private Monitoring monitoring;

    @Autowired
    private final UserRepositoryImpl userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public static final String USER_NOT_FOUND = "User not found";



    public UserApiServiceImpl(UserRepositoryImpl mongoDBRepository )
    {
        this.userRepository = mongoDBRepository;
    }



    @Override
    public List<User> findAll()
    {

        List<User> users = new ArrayList<>(this.userRepository.findAll());
        monitoring.log(Monitoring.OK, "Returned all users.");
        return users;
    }



    @Override
    public User save(User user)
    {
        if( user.getId() == null )
        {
            user.makeId();
            user.setCreated(new Date());
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
        else
        {
            user.setModified(new Date());
            User temp = this.findById(user.getId());
            user.setCreated(temp.getCreated());
        }

        monitoring.log(Monitoring.OK, "Saved user with id: " + user.getId());
        return this.userRepository.save(user);
    }



    @Override
    public User edit(User user)
    {
        User temp = this.findById(user.getId()); //throw exception if it doesn't exist.

        user.setModified(new Date());
        user.setCreated(temp.getCreated());

        monitoring.log(Monitoring.OK, "Edited user with id: " + user.getId());
        return this.userRepository.save(user);
    }



    @Override
    public User findById(UUID id )
    {
        User user = this.userRepository.findById(id);

        if( user != null )
        {
            monitoring.log(Monitoring.OK, "Found user with id: " + user.getId());
            return user;
        }
        else
        {
            monitoring.log(Monitoring.ERROR, "User not found with id " + id);
            throw new TemplateNotFoundException(HttpStatus.NOT_FOUND, "user not found with id " + id,"id");
        }
    }



    @Override
    public User delete(UUID id )
    {
        User user = this.userRepository.findById(id);

        if( user != null )
        {
            this.userRepository.deleteById(user.getId());
            monitoring.log(Monitoring.OK, "Deleted user with id: " + user.getId());
            return user;
        }
        else
        {
            monitoring.log(Monitoring.ERROR, "User not found with id " + id);
            throw new TemplateNotFoundException(HttpStatus.NOT_FOUND, "user not found with id " + id,"id");
        }
    }


}
