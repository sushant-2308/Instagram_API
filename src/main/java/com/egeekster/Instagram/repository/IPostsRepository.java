package com.egeekster.Instagram.repository;

import com.egeekster.Instagram.model.Posts;
import com.egeekster.Instagram.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostsRepository extends JpaRepository<Posts,Long> {
    List<Posts> findByUsers(Users users);
}
