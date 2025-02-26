package com.NetWorth.Transaction.Service;

import com.NetWorth.Transaction.model.UserInfo;
import com.NetWorth.Transaction.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomUserDetailService extends UserInfo implements UserDetailsService{

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userInfoRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User Not FOUND: " +username));
    }




}
