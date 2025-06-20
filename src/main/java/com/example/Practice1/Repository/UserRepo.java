package com.example.Practice1.Repository;

import com.example.Practice1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    List<User> findByRole(String role);
}
