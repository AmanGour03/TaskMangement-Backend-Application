package com.Aman.SecureTaskManagmentAPI.repo;

import com.Aman.SecureTaskManagmentAPI.model.Task;
import com.Aman.SecureTaskManagmentAPI.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Integer> {
    List<Task> findByUser(Users user);
}
