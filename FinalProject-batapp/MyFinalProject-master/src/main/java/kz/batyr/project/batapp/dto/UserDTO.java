package kz.batyr.project.batapp.dto;

import kz.batyr.project.batapp.model.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private int age;
    private String email;
    private String fullName;
    private String password;
    private List<Permission> permissionList;
}
