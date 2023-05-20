package com.example.apidemo.services;

import com.example.apidemo.dtos.auth.UsersRequest;
import com.example.apidemo.models.Users;

import java.util.List;

public interface UsersService {
    public List<Users> GetAllUsers();

    public Users AddUser(UsersRequest user);
}
