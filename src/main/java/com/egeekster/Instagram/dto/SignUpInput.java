package com.egeekster.Instagram.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {

    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private Integer userAge;
    private String userPassword;
    private String userContact;

}
