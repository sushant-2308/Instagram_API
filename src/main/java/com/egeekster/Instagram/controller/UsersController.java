package com.egeekster.Instagram.controller;


import com.egeekster.Instagram.dto.SignUpInput;
import com.egeekster.Instagram.dto.SignUpOutput;
import com.egeekster.Instagram.dto.SingInOutput;
import com.egeekster.Instagram.model.Users;
import com.egeekster.Instagram.service.AuthenticationService;
import com.egeekster.Instagram.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    AuthenticationService authenticationService;

    //Sign Up
    @PostMapping("/signup")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpDto)
    {
         return usersService.signUp(signUpDto);
    }

    //Sign in
    @PostMapping("/signin")
    public SingInOutput signIn(@RequestBody SignUpInput signInDto)
    {
        return usersService.signIn(signInDto);
    }

    @PutMapping("/update/{email}/{token}")
    public ResponseEntity<String> updateUser(@PathVariable String email , @PathVariable String token , @RequestBody Users users){
        HttpStatus status;
        String msg=null;

        if(authenticationService.authenticate(email,token))
        {
            try{
                usersService.updateUser(users , token);
                status = HttpStatus.OK;
                msg = "User updated sucessfully";
            }catch (Exception e){
                msg = "Enter valid information";
                status = HttpStatus.BAD_REQUEST;
            }

        }
        else
        {
            msg = "Invalid User";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }
}
