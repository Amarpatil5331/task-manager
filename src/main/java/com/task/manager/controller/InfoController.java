package com.task.manager.controller;

import com.task.manager.dto.ApiInfo;
import com.task.manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
@CrossOrigin("*")
public class InfoController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<ApiInfo> getInfo(){
        return ResponseEntity.ok(taskService.getInfo());
    }
}
