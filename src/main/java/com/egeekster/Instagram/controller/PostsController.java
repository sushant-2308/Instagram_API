package com.egeekster.Instagram.controller;


import com.egeekster.Instagram.model.AuthenticationToken;
import com.egeekster.Instagram.model.Posts;
import com.egeekster.Instagram.model.Users;
import com.egeekster.Instagram.service.AuthenticationService;
import com.egeekster.Instagram.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    PostsService postsService;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/getPosts/{email}/{token}")
    public ResponseEntity<List<Posts>> getAllPosts(@PathVariable String email, @PathVariable String token){
        HttpStatus status;
        List<Posts> postsList=null;
        if(authenticationService.authenticate(email,token)){
            postsList=postsService.getAllUserPosts(token);
            status=HttpStatus.OK;
        }else{
            status=HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<List<Posts>>(postsList,status);
    }

    @PostMapping("/postsData/{email}/{token}")
    public ResponseEntity<String> postData(@PathVariable String email,@PathVariable String token,@RequestBody Posts posts){
        HttpStatus status;
        String msg = "";
        if(authenticationService.authenticate(email,token))
        {
            postsService.postUserData(posts);
            msg = " Post posted successfully";
            status = HttpStatus.OK;
        }
        else
        {
            msg = "Invalid user";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }
}
