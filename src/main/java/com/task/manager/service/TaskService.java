package com.task.manager.service;

import com.task.manager.dto.ApiInfo;
import com.task.manager.dto.StatusRequest;
import com.task.manager.dto.TaskRequest;
import com.task.manager.entity.Task;
import com.task.manager.enums.TaskStatus;
import com.task.manager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final Environment environment;

    @Transactional
    public Task createTask(TaskRequest request){
        Task task=new Task();
        task.setTitle(request.getTitle());
        task.setStatus(TaskStatus.TODO);
        return taskRepository.save(task);
    }

    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    public Task getById(long id){
        return findTaskOrThrow(id);
    }
    @Transactional
    public Task updateTask(long id, StatusRequest request){
        Task existingTask = findTaskOrThrow(id);
        existingTask.setStatus(request.getStatus());
        return existingTask; // dirty checking
    }

    @Transactional
    public void deleteTask(long id){
        Task existingTask = findTaskOrThrow(id);
        taskRepository.delete(existingTask);
    }

    public ApiInfo getInfo() {
        String[] profiles = environment.getActiveProfiles();
        String profile = profiles.length > 0 ? profiles[0] : "default";

        String url = environment.getProperty("spring.datasource.url");
        return new ApiInfo(profile, extractHost(url));
    }

    private String extractHost(String url) {
        if (url == null) return "unknown";
        // jdbc:mysql://localhost:3306/test_db?...
        String afterSlashes = url.substring(url.indexOf("//") + 2); // localhost:3306/test_db?...
        String hostAndPort = afterSlashes.split("/")[0];            // localhost:3306
        return hostAndPort.split(":")[0];                           // localhost
    }

    private Task findTaskOrThrow(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Task not found: " + id));
    }
}
