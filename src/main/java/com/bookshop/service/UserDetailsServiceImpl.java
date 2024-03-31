package com.bookshop.service;

import com.bookshop.domain.User;
import com.bookshop.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = repository.findByUsername(username).isPresent() ? repository.findByUsername(username).get() : null;
       UserBuilder builder = null;
       if(user != null) {
       builder = org.springframework.security.core.userdetails.User.withUsername(username);
       builder.password(user.getPassword());
       builder.roles(user.getRole());
       } else {
         throw new UsernameNotFoundException("User not found.");
       }
       return builder.build();
    }
}
