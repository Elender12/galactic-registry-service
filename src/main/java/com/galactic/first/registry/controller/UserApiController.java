package com.galactic.first.registry.controller;


import com.galactic.first.registry.model.User;
import com.galactic.first.registry.monitoring.Monitoring;
import com.galactic.first.registry.security.JwtProvider;
import com.galactic.first.registry.security.JwtRequest;
import com.galactic.first.registry.security.JwtResponse;
import com.galactic.first.registry.security.JwtUserDetailService;
import com.galactic.first.registry.service.UserApiServiceImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class UserApiController implements UserApi
{
    @Autowired
    private Monitoring monitoring;

    @Autowired
    private UserApiServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    JwtUserDetailService userDetailsService;

    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtProvider.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @Override
    public ResponseEntity<JwtResponse> authenticate(@Valid JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtProvider.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<User> addUser(@ApiParam(value = "User object", required = true) @Valid @RequestBody User body )
    {
        monitoring.log(Monitoring.INFO, "Received addUser request");
        return ResponseEntity.ok().body(userService.save(body));
    }



    public ResponseEntity<User> deleteUser(@ApiParam(value = "User object to update.", required = true) @PathVariable("id") UUID id )
    {
        monitoring.log(Monitoring.INFO, "Received addUser request");
        return ResponseEntity.ok().body(userService.delete(id));
    }



    public ResponseEntity<List<User>> getAllUsers()
    {
        monitoring.log(Monitoring.INFO, "Received getAllUsers request");
        return ResponseEntity.ok().body(userService.findAll());
    }



    public ResponseEntity<User> getUserByID(@ApiParam(value = "", required = true) @PathVariable("id") UUID id )
    {
        monitoring.log(Monitoring.INFO, "Received getUserByID request");
        return ResponseEntity.ok().body(userService.findById(id));
    }



    public ResponseEntity<User> updateUser(@ApiParam(value = "User object to update.", required = true) @Valid @RequestBody User body )
    {
        monitoring.log(Monitoring.INFO, "Received updateUser request");
        return ResponseEntity.ok().body(userService.edit(body));
    }



}
