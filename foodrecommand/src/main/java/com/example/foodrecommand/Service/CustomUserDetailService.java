package com.example.foodrecommand.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.foodrecommand.Model.CustomUserDetail;
import com.example.foodrecommand.Model.User;
import com.example.foodrecommand.Repository.IUserrepository;

public class CustomUserDetailService implements UserDetailsService {
     @Autowired
    private IUserrepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return new CustomUserDetail(user, userRepository);
    }
}
