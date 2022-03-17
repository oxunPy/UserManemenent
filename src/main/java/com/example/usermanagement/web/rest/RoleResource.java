package com.example.usermanagement.web.rest;

import com.example.usermanagement.models.Permission;
import com.example.usermanagement.models.Role;
//import com.example.usermanemenent.service.PermissionService;
import com.example.usermanagement.service.PermissionService;
import com.example.usermanagement.service.RoleService;
import com.example.usermanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleResource {

    private final RoleService roleService;
    private final PermissionService permissionService;

    public RoleResource(RoleService roleService, PermissionService permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @GetMapping("/roles")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity findOne(@PathVariable Long id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PostMapping("/roles")
    public ResponseEntity addRole(@RequestBody Role role){
        return ResponseEntity.ok(roleService.save(role));
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity updateRole(@PathVariable Long id, @RequestBody Role role){
        if(roleService.existsById(id)){
            Role roleToUpdate = roleService.getRoleById(id);
            roleToUpdate.setName(role.getName());
            return ResponseEntity.ok(roleToUpdate);
        }
        return ResponseEntity.ok("Such Role doesn't exist");
    }

//    @PostMapping("/roles/{role_id}/users/{user_id}")
//    public ResponseEntity addUser(@PathVariable Long role_id, @PathVariable Long user_id){
//        if(roleService.addNewUser(role_id, user_id)){
//            Role role = roleService.getRoleById(role_id);
//            return ResponseEntity.ok(role);
//        }
//        return ResponseEntity.ok("Such role or user doesn't exists");
//    }
//
//    @DeleteMapping("/roles/{role_id}/users/{user_id}")
//    public ResponseEntity deleteUser(@PathVariable Long role_id, @PathVariable Long user_id){
//        if(roleService.deleteUser(role_id, user_id)){
//            Role role = roleService.getRoleById(role_id);
//            return ResponseEntity.ok(role);
//        }
//        return ResponseEntity.ok("Such role or user doesn't exists");
//    }


    @PostMapping("/roles/{role_id}/permissions/{permission_id}")
    public ResponseEntity addPermission(@PathVariable Long role_id, @PathVariable Long permission_id){
        if(roleService.addNewPermission(role_id, permission_id)){
            Role role = roleService.getRoleById(role_id);
            Permission permission = permissionService.getPermission(permission_id);

            roleService.save(role);
            permissionService.save(permission);
            return ResponseEntity.ok(role);
        }
        return ResponseEntity.ok("Such role or permission doesn't exists");
    }

    @DeleteMapping("roles/{role_id}/permissions/{permission_id}")
    public ResponseEntity deletePermission(@PathVariable Long role_id, @PathVariable Long permission_id){
        if(roleService.deletePermission(role_id, permission_id)){
            Role role = roleService.getRoleById(role_id);
            Permission permission = permissionService.getPermission(permission_id);

            roleService.save(role);
            permissionService.save(permission);
            return ResponseEntity.ok(role);
        }
        return ResponseEntity.ok("Such role or permission doesn't exists");
    }
}
