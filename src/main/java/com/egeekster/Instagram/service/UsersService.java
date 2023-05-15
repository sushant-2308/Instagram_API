package com.egeekster.Instagram.service;

import com.egeekster.Instagram.dto.SignUpInput;
import com.egeekster.Instagram.dto.SignUpOutput;
import com.egeekster.Instagram.dto.SingInOutput;
import com.egeekster.Instagram.model.AuthenticationToken;
import com.egeekster.Instagram.model.Users;
import com.egeekster.Instagram.repository.ITokenRepository;
import com.egeekster.Instagram.repository.IUsersRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UsersService {

    @Autowired
    IUsersRepository usersRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ITokenRepository tokenRepository;


    public SignUpOutput signUp(SignUpInput signUpDto){

        //Check if user exists or not based on email
        Users users = usersRepository.findFirstByEmail(signUpDto.getUserEmail());

        if(users!=null){
            throw new IllegalStateException("User already exist!!!! Sign up instead");
        }

        // encryption
        String encryptedPassword=null;
        try {
            encryptedPassword = encryptPassword(signUpDto.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        // save the user

        users = new Users(signUpDto.getUserFirstName(),signUpDto.getUserLastName(),signUpDto.getUserAge(),
                          signUpDto.getUserEmail(),encryptedPassword,signUpDto.getUserContact());

        usersRepository.save(users);

        //token creation and saving

        AuthenticationToken token = new AuthenticationToken(users);
        authenticationService.saveToken(token);

        return new SignUpOutput("User registered","User created successfully");
    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

       String hash = DatatypeConverter.printHexBinary(digested);
       return hash;
    }

    public SingInOutput signIn(SignUpInput signInDto) {

        //get email

        Users users = usersRepository.findFirstByEmail(signInDto.getUserEmail());

        if(users == null)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        //encrypt the password

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getUserPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }


        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(users.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        //figure out the token

        AuthenticationToken authToken = new AuthenticationToken(users);
        tokenRepository.save(authToken);

        //set up output response

        return new SingInOutput("Authentication Successfull !!!",authToken.getToken());
    }

    public void updateUser(Users users, String token) {
        Users users1=tokenRepository.findFirstByToken(token).getUsers();

        users1.setFirstName(users.getFirstName());
        users1.setLastName(users.getLastName());
        users1.setEmail(users.getEmail());
        users1.setAge(users.getAge());
        users1.setPassword(users.getPassword());
        users1.setPhoneNumber(users.getPhoneNumber());
        usersRepository.save(users1);
    }
}
