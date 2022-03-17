package com.example.usermanagement.service;

import com.example.usermanagement.models.Permission;
import com.example.usermanagement.models.Role;
//import com.example.usermanagement.models.RolePermission;
import com.example.usermanagement.repositories.PermissionRepository;
//import com.example.usermanagement.repositories.RolePermissionRepository;
import com.example.usermanagement.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;
//    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;
    public PermissionService(PermissionRepository permissionRepository, RoleRepository roleRepository) {
        this.permissionRepository = permissionRepository;
//        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
    }

    public List<Permission> findAll(){
        return permissionRepository.findAll();
    }

    // save new permission
    public Permission save(Permission permission){
        return permissionRepository.save(permission);
    }

    public Permission getPermission(Long id){           // get permission by id
        return permissionRepository.existsById(id) ? permissionRepository.findById(id).get() : null;
    }

    public boolean delete(Long id){
        if(permissionRepository.existsById(id)){
            permissionRepository.delete(permissionRepository.findById(id).get());
            return true;
        }
        return false;
    }

//    public boolean addPermissionRole(Long permissionId, Long rolePermissionId){
//        Optional<Permission> permission = permissionRepository.findById(permissionId);
//        Optional<RolePermission> rolePermission = rolePermissionRepository.findById(rolePermissionId);
//
//        if(permission.isPresent() && rolePermission.isPresent()){
//            permission.get().getRolePermissions().add(rolePermission.get());
//            rolePermission.get().setPermission(permission.get());
//            return true;
//        }
//        return false;
//    }
//
//    public boolean deletePermissionRole(Long permissionId, Long rolePermissionId){
//        Optional<Permission> permission = permissionRepository.findById(permissionId);
//        Optional<RolePermission> rolePermission = rolePermissionRepository.findById(rolePermissionId);
//
//        if(permission.isPresent() && rolePermission.isPresent()){
//            permission.get().getRolePermissions().remove(rolePermission.get());
//            rolePermission.get().setPermission(null);
//            return true;
//        }
//        return false;
//    }

//    public boolean addRole(Long permissionId, Long roleId){
//        Optional<Permission> permission = permissionRepository.findById(permissionId);
//        Optional<Role> role = roleRepository.findById(roleId);
//
//        if(role.isPresent() && !permission.isEmpty()){
//            permission.get().getRoles().add(role.get());
//            permissionRepository.save(permission.get());
//            return true;
//        }
//        return false;
//    }

//    public boolean removeRole(Long permissionId, Long roleId){
//        Optional<Permission> permission = permissionRepository.findById(permissionId);
//        Optional<Role> role = roleRepository.findById(roleId);
//
//        if(role.isPresent() && permission.isPresent()){
//            permission.get().getRoles().remove(role.get());
//            permissionRepository.save(permission.get());
//            return true;
//        }
//        return false;
//    }

    public boolean existsById(Long id){
        return permissionRepository.existsById(id);
    }
}
