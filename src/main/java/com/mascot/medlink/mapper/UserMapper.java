package com.mascot.medlink.mapper;

import com.mascot.medlink.model.dto.UserDTO;
import com.mascot.medlink.model.entity.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user){
        //Convert User entity to userDTO
        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getEmail(),
                user.getContact(),
                user.getRoles(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }



}
