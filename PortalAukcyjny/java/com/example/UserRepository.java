package com.example;
import com.example.User;

import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<User, String> {
	
}
