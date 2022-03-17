package com.example.usermanagement.service;

import com.example.usermanagement.models.Role;
import com.example.usermanagement.models.User;
import com.example.usermanagement.repositories.RoleRepository;
import com.example.usermanagement.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return !userRepository.existsByLogin(user.getLogin()) ? userRepository.save(user) : null;
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User getUserByLogin(String login) {
        return userRepository.existsByLogin(login) ? userRepository.findByLogin(login) : null;
    }

    public boolean addNewRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if (user.isPresent() && role.isPresent()) {
            user.get().getRoles().add(role.get());
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    public boolean deleteRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if (user.isPresent() && role.isPresent()) {
            user.get().getRoles().remove(role.get());
            userRepository.save(user.get());
            return true;
        }
        return false;
    }


    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }
}
