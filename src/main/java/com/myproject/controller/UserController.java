package com.myproject.controller;

import com.myproject.annotation.DefaultExceptionMessage;
import com.myproject.annotation.ExecutionTime;
import com.myproject.dto.UserDTO;
import com.myproject.entity.ResponseWrapper;
import com.myproject.exception.TicketingProjectException;
import com.myproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "UserController",description = "User API")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @ExecutionTime
    @GetMapping
    @RolesAllowed("Admin")
    @Operation(summary = "Get Users")
    public ResponseEntity<ResponseWrapper> getUsers(){
        List<UserDTO> userDTOList = userService.listAllUsers();
        return ResponseEntity.ok(new ResponseWrapper("Users are successfully retrieved",userDTOList, HttpStatus.OK));
    }
    @ExecutionTime
    @GetMapping("/{userName}")
    @RolesAllowed("Admin")
    @Operation(summary = "Get Users by username")
    public ResponseEntity<ResponseWrapper> getUserByName(@PathVariable("userName") String userName){
        UserDTO user = userService.findByUserName(userName);
return ResponseEntity.ok((new ResponseWrapper("User is successfully retrieved",user,HttpStatus.OK)));
    }

    @PostMapping
    @RolesAllowed("Admin")
    @Operation(summary = "Create Users")
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user){
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("User is successfully created",HttpStatus.CREATED));
    }

    @PutMapping
    @RolesAllowed("Admin")
    @Operation(summary = "Update User")
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO user) {
        userService.update(user);
        return ResponseEntity.ok(new ResponseWrapper("User is successfully updated", user,HttpStatus.OK));
    }

    @DeleteMapping("/{userName}")
    @RolesAllowed("Admin")
    @Operation(summary = "Delete User")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete user")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("userName")String userName) throws TicketingProjectException {
        userService.delete(userName);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseWrapper("User is successfully deleted",HttpStatus.CREATED));
        return ResponseEntity.ok(new ResponseWrapper("User is successfully deleted",HttpStatus.CREATED));
    }





}
