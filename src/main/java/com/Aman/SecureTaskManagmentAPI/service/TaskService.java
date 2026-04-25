package com.Aman.SecureTaskManagmentAPI.service;

import com.Aman.SecureTaskManagmentAPI.model.Task;
import com.Aman.SecureTaskManagmentAPI.model.Users;
import com.Aman.SecureTaskManagmentAPI.repo.TaskRepo;
import com.Aman.SecureTaskManagmentAPI.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    public TaskRepo taskRepo;
    @Autowired
    public UserRepo userRepo;


    public Task addTask(Task task, String username) {
        Users user=userRepo.findByUsername(username);
        task.setUser(user);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepo.save(task);
    }

    public List<Task> getAllUserTask(String username) {
        Users user=userRepo.findByUsername(username);
        return taskRepo.findByUser(user);
    }

    public Task updateTask(Integer id, String username) {
        Task task =taskRepo.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
        if(!task.getUser().getUsername().equals(username)){
            throw new RuntimeException("Unauthorized to update the task");
        }
        task.setTitle(task.getTitle());
        task.setDescription(task.getDescription());
        task.setStatus(task.getStatus());
        task.setPriority(task.getPriority());
        task.setDeadline(task.getDeadline());
        task.setUpdatedAt(task.getUpdatedAt());
        return taskRepo.save(task);
    }

    public void deleteTask(Integer id, String username) {
        Task task=taskRepo.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
        if(!task.getUser().getUsername().equals(username)){
            throw new RuntimeException("Unauthorized to delete the task");
        }
        taskRepo.deleteById(id);
    }
}
