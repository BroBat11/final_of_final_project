package kz.batyr.project.batapp.service;

import kz.batyr.project.batapp.model.Permission;
import kz.batyr.project.batapp.model.User;
import kz.batyr.project.batapp.repository.PermissionRepository;
import kz.batyr.project.batapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;


public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user!=null){
            return user;
        }else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){

        User checkUser = userRepository.findByEmail(user.getEmail());
        if(checkUser==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Permission defaultPermission = permissionRepository.findByRole("ROLE_USER");

            if (defaultPermission == null) {
                defaultPermission = new Permission();
                defaultPermission.setRole("ROLE_USER");
                defaultPermission = permissionRepository.save(defaultPermission);
            }
            user.setPermissionList(Collections.singletonList(defaultPermission));
            return userRepository.save(user);
        }
        return null;
    }

    public User addSeller(User user){
        User checkSeller = userRepository.findByEmail(user.getEmail());
        if(checkSeller==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Permission defaultPermission = permissionRepository.findByRole("ROLE_SELLER");

            if (defaultPermission == null) {
                defaultPermission = new Permission();
                defaultPermission.setRole("ROLE_SELLER");
                defaultPermission = permissionRepository.save(defaultPermission);
            }
            user.setPermissionList(Collections.singletonList(defaultPermission));
            return userRepository.save(user);
        }
        return null;
    }

    public User updatePassword(String newPassword, String oldPassword){
        User currentUser = getCurrentSessionUser();
        if(passwordEncoder.matches(oldPassword, currentUser.getPassword())){
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(currentUser);
        }
        return null;
    }

    public User getCurrentSessionUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User user = (User) authentication.getPrincipal();
            if(user != null){
                return user;
            }
        }
        return null;
    }

}
