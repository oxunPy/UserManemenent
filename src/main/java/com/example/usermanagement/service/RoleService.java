package com.example.usermanagement.service;

import com.example.usermanagement.models.Permission;
import com.example.usermanagement.models.Role;
import com.example.usermanagement.models.User;
import com.example.usermanagement.repositories.PermissionRepository;
import com.example.usermanagement.repositories.RoleRepository;
import com.example.usermanagement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }


    // save new role
    public Role save(Role role){
        // check if exists
        if(roleRepository.existsByName(role.getName())){
            return null;
        }
        return roleRepository.save(role);
    }

    // get role by its role_name
    public Role getRoleByName(String name){
        return roleRepository.findByName(name);
    }

    public Role getRoleById(Long id){
        return roleRepository.findById(id).get();
    }

//    public boolean addNewUser(Long roleId, Long userId){        // get role by id then add user
//        Optional<Role> role = roleRepository.findById(roleId);
//        Optional<User> user = userRepository.findById(userId);   // getting user by id
//
//        if(role.isPresent() && user.isPresent()) {
//            role.get().getUsers().add(user.get());      // add user to usersSet
//            roleRepository.save(role.get());
//            return true;
//        }
//        return false;
//    }

    public boolean addNewPermission(Long roleId, Long permissionId){
        Optional<Role> role = roleRepository.findById(roleId);
        Optional<Permission> permission = permissionRepository.findById(permissionId);

        if(role.isPresent() && permission.isPresent()){
            role.get().getPermissions().add(permission.get());
            roleRepository.save(role.get());
            return true;
        }
        return false;
    }

//    public boolean deleteUser(Long roleId, Long userId){
//        Optional<Role> role = roleRepository.findById(roleId);
//        Optional<User> user = userRepository.findById(userId);   // getting user by id
//
//        if(role.isPresent() && user.isPresent()) {
//            role.get().getUsers().remove(user.get());      // add user to usersSet
//            roleRepository.save(role.get());
//            return true;
//        }
//        return false;
//    }

    public boolean deletePermission(Long roleId, Long permissionId){
        Optional<Role> role = roleRepository.findById(roleId);
        Optional<Permission> permission = permissionRepository.findById(permissionId);

        if(role.isPresent() && permission.isPresent()){
            role.get().getPermissions().remove(permission.get());
            roleRepository.save(role.get());
            return true;
        }
        return false;
    }

    public boolean delete(Long id){         // deleting role by its id
        Role role = getRoleById(id);
        if(role != null){
            roleRepository.delete(role);
            return true;
        }
        return false;

//        Optional<Role> role = roleRepository.findById(id);
//        role.ifPresent(roleRepository :: delete);
    }

    public boolean existsById(Long id){
        return roleRepository.existsById(id);
    }

}
