package com.NetWorth.Transaction.Service;


import com.NetWorth.Transaction.dto.UserDTO;
import com.NetWorth.Transaction.jwt.JwtTokenProvider;
import com.NetWorth.Transaction.model.UserInfo;
import com.NetWorth.Transaction.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private  UserInfo userInfo;


    public String loginService(UserDTO userDTO) {
         if(userInfoRepo.existsByEmail(userDTO.getEmail())){
             UserInfo userInfo=userInfoRepo.findByEmail(userDTO.getEmail());
             if(userInfoRepo.existsByPassword(userDTO.getPassword())){
                 return jwtTokenProvider.generateToken(userInfo.getUsername());

             }throw new IllegalArgumentException("Wrong Password!");
         } throw new IllegalArgumentException("New User->Register");


    }
}
