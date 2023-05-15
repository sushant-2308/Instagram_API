package com.egeekster.Instagram.service;

import com.egeekster.Instagram.model.AuthenticationToken;
import com.egeekster.Instagram.model.Posts;
import com.egeekster.Instagram.model.Users;
import com.egeekster.Instagram.repository.IPostsRepository;
import com.egeekster.Instagram.repository.ITokenRepository;
import com.egeekster.Instagram.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Service
public class PostsService {

    @Autowired
    IPostsRepository postsRepository;

    @Autowired
    IUsersRepository usersRepository;

    @Autowired
    ITokenRepository tokenRepository;

    public void postUserData(Posts posts) {
        Long UsersId=posts.getUsers().getUserId();
        Users users = usersRepository.findByUserId(UsersId);

        posts.setUsers(users);
        postsRepository.save(posts);
    }

    public List<Posts> getAllUserPosts(String token) {
        Users users=tokenRepository.findFirstByToken(token).getUsers();
        List<Posts> postsList=postsRepository.findByUsers(users);
        return postsList;
    }

}
