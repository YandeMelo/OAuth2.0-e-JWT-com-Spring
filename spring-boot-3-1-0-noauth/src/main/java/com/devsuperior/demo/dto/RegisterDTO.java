package com.devsuperior.demo.dto;

import com.devsuperior.demo.entities.UserRole;

public record RegisterDTO(String email, String name, String password, UserRole role) {
    
}
