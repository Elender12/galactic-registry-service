package com.galactic.first.registry.controller;


import com.galactic.first.registry.model.User;
import com.galactic.first.registry.security.JwtRequest;
import com.galactic.first.registry.security.JwtResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@Api(value = "Users", description = "the User API")
public interface UserApi {

    @ApiOperation(value = "Authenticate", nickname = "authenticate", notes = " .", response = JwtResponse.class, tags = {"Users",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK.", response = User.class),
            @ApiResponse(code = 400, message = "Invalid User.", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized.", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden.", response = Error.class),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Server error.", response = Error.class)})
    @RequestMapping(value = "/authenticate",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)

    ResponseEntity<JwtResponse> authenticate(@ApiParam(value = "User object", required = true) @Valid @RequestBody JwtRequest authenticationRequest
    ) throws Exception;


    @ApiOperation(value = "Add a new User.", nickname = "addUser", notes = "Takes a User object, saves it, and returns it with the saved id.", response = User.class, tags = {"Users",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = User.class),
        @ApiResponse(code = 400, message = "Invalid User.", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized.", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden.", response = Error.class),
        @ApiResponse(code = 404, message = "Not found.", response = Error.class),
        @ApiResponse(code = 500, message = "Server error.", response = Error.class)})
    @RequestMapping(value = "/users",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST)
    ResponseEntity<User> addUser(@ApiParam(value = "User object", required = true) @Valid @RequestBody User body
    );


    @ApiOperation(value = "Delete an existing User by id.", nickname = "deleteUsers", notes = "Takes an existing User, deletes it, and returns the new object.", response = User.class, tags = {"Users",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = User.class),
        @ApiResponse(code = 401, message = "Unauthorized.", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden.", response = Error.class),
        @ApiResponse(code = 404, message = "Not found.", response = Error.class),
        @ApiResponse(code = 500, message = "Server error.", response = Error.class)})
    @RequestMapping(value = "/users/{id}",
        produces = {"application/json"},
        method = RequestMethod.DELETE)
    ResponseEntity<User> deleteUser(@ApiParam(value = "User object to update.", required = true) @PathVariable("id") UUID id
    );


    @ApiOperation(value = "Get all Users.", nickname = "getAllUsers", notes = "Returns all Users.", response = User.class, responseContainer = "List", tags = {"Users",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = User.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized.", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden.", response = Error.class),
        @ApiResponse(code = 404, message = "Not found.", response = Error.class),
        @ApiResponse(code = 500, message = "Server error.", response = Error.class)})
    @RequestMapping(value = "/users",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<List<User>> getAllUsers();


    @ApiOperation(value = "Get a User by id.", nickname = "getUsersByID", notes = "Returns one User by id.", response = User.class, tags = {"Users",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = User.class),
        @ApiResponse(code = 401, message = "Unauthorized.", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden.", response = Error.class),
        @ApiResponse(code = 404, message = "Not found.", response = Error.class),
        @ApiResponse(code = 500, message = "Server error.", response = Error.class)})
    @RequestMapping(value = "/users/{id}",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<User> getUserByID(@ApiParam(value = "", required = true) @PathVariable("id") UUID id
    );


    @ApiOperation(value = "Update an existing User.", nickname = "updateUsers", notes = "Takes an existing User, updates it, and returns the new object.", response = User.class, tags = {"Users",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = User.class),
        @ApiResponse(code = 400, message = "Invalid User.", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized.", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden.", response = Error.class),
        @ApiResponse(code = 404, message = "Not found.", response = Error.class),
        @ApiResponse(code = 500, message = "Server error.", response = Error.class)})
    @RequestMapping(value = "/users",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.PUT)
    ResponseEntity<User> updateUser(@ApiParam(value = "User object to update.", required = true) @Valid @RequestBody User body
    );
}

