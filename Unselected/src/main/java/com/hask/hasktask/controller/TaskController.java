package com.hask.hasktask.controller;

import com.hask.hasktask.model.Task;
import com.hask.hasktask.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Task Management")
public class TaskController {

    final private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Endpoint to create a new task
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task body) {
        taskService.create(body); // Save task to the database

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Endpoint to complete a task
    @PostMapping("/{taskId}/complete")
    public ResponseEntity<?> completeTask(@PathVariable Long taskId) {
        taskService.completeTask(taskId);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{userId}/user")
    public ResponseEntity<List<Task>> getTasks(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByUserId(userId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);  // Delete task from the database

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        taskService.updateTask(taskId, task);  // Update task in the database

        return ResponseEntity.noContent().build();
    }
}

