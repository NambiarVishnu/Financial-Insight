package com.NetWorth.Transaction.Controller;

import com.NetWorth.Transaction.Service.UserRegisterService;
import com.NetWorth.Transaction.dto.UserDTO;
import com.NetWorth.Transaction.model.UserInfo;
import com.NetWorth.Transaction.response.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping("auth/v1/register")
    public ResponseEntity<?> registerUser(@RequestBody  UserDTO userDTO){
        try{
            String token =userRegisterService.registerUserService(userDTO);
            return ResponseEntity.ok(new RegistrationResponse("User registered successfully",token));

        }catch(IllegalAccessException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }





}



