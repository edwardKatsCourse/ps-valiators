package com.example.demouserproject.model.web;

import com.example.demouserproject.model.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;

    private UserType userType;

}
