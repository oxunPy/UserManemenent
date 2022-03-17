package com.example.usermanagement.web.rest;

import com.example.usermanagement.models.Role;
import com.example.usermanagement.models.User;
import com.example.usermanagement.service.RoleService;
import com.example.usermanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final UserService userService;
    private final RoleService roleService;

    public UserResource(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity addNewUser(@RequestBody User user) {

        if(userService.existsByLogin(user.getLogin())){
            return ResponseEntity.ok("This user is already existed");
        }

        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User newUser) {
        User user = userService.getUserById(id);
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setLogin(newUser.getLogin());
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    // adding new role to the user
    @PostMapping("/users/{user_id}/roles/{role_id}")
    public ResponseEntity assignRoleToUser(@PathVariable Long user_id, @PathVariable Long role_id) {
//        if (user_id == null ||
//                user_id <= 0 ||
//                role_id == null ||
//                role_id <= 0) return null;  // params are not valid
//
//        User user = userService.getUserById(user_id);
//        if (user == null)
//            return null;//user is not exit
//
//        Role role = roleService.getRoleById(role_id);
//        if (role == null)
//            return null;//role is not exist

//        role.getUsers().add(user);
//        roleService.save(role);

//        user.getRoles().add(role);
//        userService.save(user);

        if (userService.addNewRole(user_id, role_id)) {
            User user = userService.getUserById(user_id);
            Role role = roleService.getRoleById(role_id);
            userService.save(user);     // update this user
            roleService.save(role);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.ok("Xatolik yuz berdi.");
    }

    // remove role from the user;
    @DeleteMapping("/users/{user_id}/roles/{role_id}")
    public ResponseEntity deleteRoleFromUser(@PathVariable Long user_id, @PathVariable Long role_id) {

        if (userService.deleteRole(user_id, role_id)) {
            User user = userService.getUserById(user_id);
            Role role = roleService.getRoleById(role_id);
            userService.save(user);
            roleService.save(role);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.ok("Xatolik yuz berdi.");
    }
}
