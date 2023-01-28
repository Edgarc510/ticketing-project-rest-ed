package com.myproject.service;


import com.myproject.dto.UserDTO;
import com.myproject.exception.TicketingProjectException;

import java.util.List;

public interface UserService{

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO dto);
    UserDTO update(UserDTO dto);
    void deleteByUserName(String username);
    void delete(String username) throws TicketingProjectException;
    List<UserDTO> listAllByRole(String role);





}
