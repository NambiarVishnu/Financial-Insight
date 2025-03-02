package com.NetWorth.Transaction.Controller;


import com.NetWorth.Transaction.Service.LoginService;
import com.NetWorth.Transaction.dto.UserDTO;
import com.NetWorth.Transaction.response.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("auth/v1/login")
    public ResponseEntity<?> loginController(@RequestBody UserDTO userDTO){
        try{
            String token = String.valueOf(loginService.loginService(userDTO));
            return ResponseEntity.ok(new RegistrationResponse("User Login successfully",token));

        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getStackTrace());
        }
    }
}
