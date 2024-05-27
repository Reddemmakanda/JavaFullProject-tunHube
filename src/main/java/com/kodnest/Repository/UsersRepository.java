package com.kodnest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.entity.User;

public interface UsersRepository extends JpaRepository<User, Integer>{

public User findByEmail(String email);

	
}