package com.example.corporate.dto;
import com.example.corporate.entity.Role; import java.util.Set;
public record UserResponse(Long id,String username,String fullName,Set<Role> roles,boolean enabled){}
