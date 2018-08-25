package com.example.demouserproject.model.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserType {

    REGULAR_USER(1),
    PRIVILEGED_USER(2),
    ADMIN_USER(3)
    ;

    private Integer id;

    UserType(Integer id) {
        this.id = id;
    }

    public static UserType getById(Integer id) {
        if  (id == null) {
            return null;
        }

        for (UserType userType : UserType.values()) {
            if (userType.getId().equals(id)) {
                return userType;
            }
        }

        return null;
//        return Arrays
//                .stream(UserType.values())
//                .filter(x -> x.getId().equals(id))
//                .findFirst()
//                .orElse(null);
    }

}
