package com.huce.ltudm.doan_tra_cu_dulich.service.impl;

import com.huce.ltudm.doan_tra_cu_dulich.entity.User;
import com.huce.ltudm.doan_tra_cu_dulich.repository.User_Repository;
import com.huce.ltudm.doan_tra_cu_dulich.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private User_Repository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByTai_khoan(s);
        if (user != null) {
            return new CustomUserDetails(user);
        } else {
            throw new UsernameNotFoundException("tai khoan k chính xac");
        }
    }
}
