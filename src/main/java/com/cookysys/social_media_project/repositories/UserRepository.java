package com.cookysys.social_media_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cookysys.social_media_project.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
