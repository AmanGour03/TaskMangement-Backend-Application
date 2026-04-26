package com.Aman.SecureTaskManagmentAPI.controller;

import com.Aman.SecureTaskManagmentAPI.model.Task;
import com.Aman.SecureTaskManagmentAPI.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping
    public ResponseEntity<Task>addTask(@RequestBody @Valid Task task, Principal principal){
        return new ResponseEntity<>(service.addTask(task,principal.getName()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllUserTask(@Valid Principal principal){
        List<Task> tasks = service.getAllUserTask(principal.getName());
        return ResponseEntity.ok(tasks);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String>deleteTask(@PathVariable @Valid Integer id,Principal principal){
        service.deleteTask(id,principal.getName());
        return ResponseEntity.ok("Task has been deleted successfully");
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<Task>updateTask(@PathVariable @Valid Integer id,Principal principal){

        return ResponseEntity.ok(service.updateTask(id,principal.getName()));
    }


}
