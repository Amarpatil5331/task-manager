package com.task.manager.controller;

import com.task.manager.dto.ApiInfo;
import com.task.manager.dto.StatusRequest;
import com.task.manager.service.TaskService;
import com.task.manager.dto.TaskRequest;
import com.task.manager.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin("*")//for learning
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest request){
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll(){
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable long id){
        return ResponseEntity.ok(taskService.getById(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody StatusRequest request){
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
