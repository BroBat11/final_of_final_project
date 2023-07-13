package kz.batyr.project.batapp.mapper;

import kz.batyr.project.batapp.dto.UserDTO;
import kz.batyr.project.batapp.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    User toModel(UserDTO userDTO);

    List<UserDTO> toDtoList(List<User> userList);

    List<User> toModelList(List<UserDTO> userDtoList);
}
