package com.hkdemircan.restcrudwithjpa.repository;

import com.hkdemircan.restcrudwithjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    User findFirstById(Long id);
}
