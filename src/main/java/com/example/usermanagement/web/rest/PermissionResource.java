package com.example.usermanagement.web.rest;

import com.example.usermanagement.models.Permission;
import com.example.usermanagement.models.Role;
//import com.example.usermanagement.models.RolePermission;
import com.example.usermanagement.service.PermissionService;
//import com.example.usermanagement.service.RolePermissionService;
import com.example.usermanagement.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PermissionResource {

    private final PermissionService permissionService;
//    private final RolePermissionService rolePermissionService;
    private final RoleService roleService;

    public PermissionResource(PermissionService permissionService, RoleService roleService) {
        this.permissionService = permissionService;
//        this.rolePermissionService = rolePermissionService;
        this.roleService = roleService;
    }

    @GetMapping("/permissions")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(permissionService.findAll());
    }

    @GetMapping("/permissions/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        if(permissionService.existsById(id)){
            return ResponseEntity.ok(permissionService.getPermission(id));
        }
        return ResponseEntity.ok("No such Permission exists");
    }

    @PostMapping("/permissions")
    public ResponseEntity save(@RequestBody Permission permission){
        return ResponseEntity.ok(permissionService.save(permission));
    }

    @PutMapping("/permissions/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Permission permission){
        Permission permissionToUpdate = permissionService.getPermission(id);
        permissionToUpdate.setCode(permission.getCode());
        permissionToUpdate.setDescription(permission.getDescription());
        permissionService.save(permissionToUpdate);
        return ResponseEntity.ok(permissionToUpdate);
    }

    @DeleteMapping("/permissions/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        if(permissionService.delete(id)){
            return ResponseEntity.ok("Permission has been deleted successfully");
        }
        return ResponseEntity.ok("Permission doesn't exists");
    }

    // add permission_roles
//    @PostMapping("permissions/{permissionId}/roles/{permissionRoleId}")
//    public ResponseEntity assignNewPermissionRole(@PathVariable Long permissionId, @PathVariable Long permissionRoleId){
//        if(permissionService.addPermissionRole(permissionId, permissionRoleId)){
//            Permission permission = permissionService.getPermission(permissionId);
//            RolePermission rolePermission = rolePermissionService.findById(permissionRoleId);
//            permissionService.save(permission); // update permission
//            rolePermissionService.save(rolePermission); // update rolePermission
//            return ResponseEntity.ok(permission);
//        }
//        return ResponseEntity.ok("Such permission or permissionRole doesn't exists");
//    }


    // delete permission_roles
//    @DeleteMapping("permissions/{permissionId}/roles/{permissionRoleId}")
//    public ResponseEntity deletePermissionRole(@PathVariable Long permissionId, @PathVariable Long permissionRoleId){
//        if(permissionService.deletePermissionRole(permissionId, permissionRoleId)){
//            Permission permission = permissionService.getPermission(permissionId);
//            RolePermission rolePermission = rolePermissionService.findById(permissionRoleId);
//            permissionService.save(permission);
//            rolePermissionService.save(rolePermission);
//            return ResponseEntity.ok(permission);
//        }
//        return ResponseEntity.ok("Such permission or permissionRole doesn't exists");
//    }

    // add role which has access to the specified (id) permission
//    @PostMapping("/permission/{permissionId}/roles/{roleId}")
//    public ResponseEntity assignNewRole(@PathVariable Long permissionId, @PathVariable Long roleId){
//        if(permissionService.addRole(permissionId, roleId)){
//            Permission permission = permissionService.getPermission(permissionId);
//            return ResponseEntity.ok(permission);
//        }
//        return ResponseEntity.ok("Such permission or role doesn't exists");
//    }
//
//    @DeleteMapping("/permissions/{permissionId}/roles/{roleId}")
//    public ResponseEntity deleteRole(@PathVariable Long permissionId, @PathVariable Long roleId){
//        if(permissionService.removeRole(permissionId, roleId)){
//            Permission permission = permissionService.getPermission(permissionId);
//            return ResponseEntity.ok(permission);
//        }
//        return ResponseEntity.ok("Such permission or role doesn't exists");
//    }


}
