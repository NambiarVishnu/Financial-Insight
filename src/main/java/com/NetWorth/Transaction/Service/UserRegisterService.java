package com.NetWorth.Transaction.Service;

import com.NetWorth.Transaction.dto.UserDTO;
import com.NetWorth.Transaction.jwt.JwtTokenProvider;
import com.NetWorth.Transaction.model.UserInfo;
import com.NetWorth.Transaction.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String registerUserService(UserDTO userDTO) throws IllegalAccessException {
        if(userInfoRepo.findByUsername(userDTO.getUsername()).isPresent()){
            throw  new IllegalAccessException("Username Already exists");
        }
        if (userInfoRepo.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        UserInfo user= new UserInfo();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        userInfoRepo.save(user);

        return jwtTokenProvider.generateToken(user.getUsername());

    }
}
