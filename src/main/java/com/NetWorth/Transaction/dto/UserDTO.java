package com.NetWorth.Transaction.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {


    private String username;

    private String email;

    private String password;
}
