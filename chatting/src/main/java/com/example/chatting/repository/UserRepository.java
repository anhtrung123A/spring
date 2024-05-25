package com.example.chatting.repository;

import com.example.chatting.domain.User;
import com.example.chatting.utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
