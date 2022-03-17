//package com.example.usermanemenent.service;
//
//import com.example.usermanemenent.models.Permission;
//import com.example.usermanemenent.models.RolePermission;
//import com.example.usermanemenent.repositories.PermissionRepository;
//import com.example.usermanemenent.repositories.RolePermissionRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class RolePermissionService {
//
//    private final RolePermissionRepository rolePermissionRepository;
//    private final PermissionRepository permissionRepository;
//
//    public RolePermissionService(RolePermissionRepository rolePermissionRepository, PermissionRepository permissionRepository) {
//        this.rolePermissionRepository = rolePermissionRepository;
//        this.permissionRepository = permissionRepository;
//    }
//
//    public List<RolePermission> findAll(){              // get all entities
//        return rolePermissionRepository.findAll();
//    }
//
//    public RolePermission findById(Long id){            // find by id
//        return rolePermissionRepository.findById(id).get();
//    }
//
//    public RolePermission save(RolePermission rolePermission){      // save and update.
//        return rolePermissionRepository.save(rolePermission);
//    }
//
//    public boolean delete(Long id){
//        if(existsById(id)){
//            RolePermission rolePermission = rolePermissionRepository.findById(id).get();
//            return true;
//        }
//        return false;
//    }
//
//    public boolean existsById(Long id){
//        return rolePermissionRepository.existsById(id);
//    }
//
//    public RolePermission findByName(String name){
//        return rolePermissionRepository.findByName(name);
//    }
//
//    public boolean setPermission(Long rolePermissionId, Long permissionId){
//        Optional<RolePermission> rolePermission = rolePermissionRepository.findById(rolePermissionId);
//        Optional<Permission> permission = permissionRepository.findById(permissionId);
//
//        if(rolePermission.isPresent() && permission.isPresent()){
//            rolePermission.get().setPermission(permission.get());
//            permission.get().getRolePermissions().add(rolePermission.get());
//            return true;
//        }
//        return false;
//    }
//
//}
