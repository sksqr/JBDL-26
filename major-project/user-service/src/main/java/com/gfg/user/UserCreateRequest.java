package com.gfg.user;


import com.gfg.user.dbentities.User;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserCreateRequest {

    private String email;
    private String name;
    private String phone;
    private String kycId;


    public User toUser(){
        return User.builder()
                .email(email)
                .name(name)
                .phone(phone)
                .kycId(kycId)
                .build();
    }

}
