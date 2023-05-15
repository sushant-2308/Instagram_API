package com.egeekster.Instagram.repository;

import com.egeekster.Instagram.model.AuthenticationToken;
import com.egeekster.Instagram.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRepository extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findFirstByToken(String token);
}
