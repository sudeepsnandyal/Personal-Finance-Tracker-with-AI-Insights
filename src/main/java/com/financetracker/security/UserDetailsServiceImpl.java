package com.financetracker.security;
import com.financetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.financetracker.model.User user = userRepo.findByEmail(email)
            .orElseThrow(()->new UsernameNotFoundException("User not found: "+email));
        return User.withUsername(user.getEmail())
            .password(user.getPassword())
            .authorities("USER")
            .build();
    }
}
