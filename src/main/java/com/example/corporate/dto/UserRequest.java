package com.example.corporate.dto;
import com.example.corporate.entity.Role; import java.util.Set;
public record UserRequest(String username,String password,String fullName,Set<Role> roles,Boolean enabled){}
