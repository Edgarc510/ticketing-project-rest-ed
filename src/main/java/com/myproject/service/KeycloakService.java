package com.myproject.service;

import com.myproject.dto.UserDTO;

import javax.ws.rs.core.Response;

public interface KeycloakService {

    Response userCreate(UserDTO userDTO);

    void delete(String userName);

}
