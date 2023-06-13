package com.example.foodrecommand.Service;

import org.hibernate.metamodel.model.domain.internal.PluralAttributeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodrecommand.Model.User;
import com.example.foodrecommand.Repository.IRoleRepository;
import com.example.foodrecommand.Repository.IUserrepository;

@Service
public class UserService {
     @Autowired
    private IUserrepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    public void addUser(User user){
        userRepository.save(user);
    }
    public User finduserbyname(String name){
        return userRepository.findByUsername(name);
    }

    public User finduserbyId(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public void save(User user) {
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if (roleId != 0 && userId != 0) {
            userRepository.addRoleToUser(userId, roleId);
        }
    }
}
