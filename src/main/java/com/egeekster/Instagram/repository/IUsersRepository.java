package com.egeekster.Instagram.repository;

import com.egeekster.Instagram.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUsersRepository extends JpaRepository<Users,Long> {

    Users findFirstByEmail(String usersEmail);

    Users findByUserId(Long usersId);
}
