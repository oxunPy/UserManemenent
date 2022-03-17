//package com.example.usermanemenent.web.rest;
//
//import com.example.usermanemenent.models.Permission;
//import com.example.usermanemenent.models.RolePermission;
//import com.example.usermanemenent.repositories.RolePermissionRepository;
//import com.example.usermanemenent.service.PermissionService;
//import com.example.usermanemenent.service.RolePermissionService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//public class RolePermissionResource {
//    private final RolePermissionService rolePermissionService;
//    private final PermissionService permissionService;
//
//    public RolePermissionResource(RolePermissionService rolePermissionService, PermissionService permissionService) {
//        this.rolePermissionService = rolePermissionService;
//        this.permissionService = permissionService;
//    }
//
//
//    @GetMapping("/rolePermissions")
//    public ResponseEntity findAll(){
//        return ResponseEntity.ok(rolePermissionService.findAll());
//    }
//
//    @GetMapping("/rolePermissions/{id}")
//    public ResponseEntity findById(@PathVariable Long id){
//        return rolePermissionService.existsById(id) ?
//                ResponseEntity.ok(rolePermissionService.findById(id)) :
//                ResponseEntity.ok("Such rolePermission doesn't exists");
//    }
//
//    @PostMapping("/rolePermissions")
//    public ResponseEntity save(@RequestBody RolePermission rolePermission){
//        return ResponseEntity.ok(rolePermissionService.save(rolePermission));
//    }
//
//    @PutMapping("/rolePermissions/{id}")
//    public ResponseEntity update(@PathVariable Long id, @RequestBody RolePermission rolePermission){
//
//        if(!rolePermissionService.existsById(id)){
//            return ResponseEntity.ok("Such rolePermission doesn't exists !");
//        }
//
//        RolePermission rolePermissionToUpdate = rolePermissionService.findById(id);
//        rolePermissionToUpdate.setPermission(rolePermission.getPermission());
//        rolePermissionToUpdate.setName(rolePermission.getName());
//        rolePermissionService.save(rolePermissionToUpdate);
//
//        return ResponseEntity.ok(rolePermissionToUpdate);
//    }
//
//    @PostMapping("/rolePermissions/{rolePermissionId}/permissions/{permissionId}")
//    public ResponseEntity setPermission(@PathVariable Long rolePermissionId, @PathVariable Long permissionId){
//        if(rolePermissionService.setPermission(rolePermissionId, permissionId)){
//            RolePermission rolePermission = rolePermissionService.findById(rolePermissionId);
//            Permission permission = permissionService.getPermission(permissionId);
//
//            rolePermissionService.save(rolePermission);
//            permissionService.save(permission);
//            return ResponseEntity.ok(rolePermission);
//        }
//        return ResponseEntity.ok("Such rolePermission or permission doesn't exists");
//    }
//
//}
