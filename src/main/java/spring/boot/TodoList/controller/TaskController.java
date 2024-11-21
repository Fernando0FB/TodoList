package spring.boot.TodoList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.TodoList.dto.PutTaskDTO;
import spring.boot.TodoList.dto.RegisterTaskDTO;
import spring.boot.TodoList.models.Task;
import spring.boot.TodoList.services.TaskService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) Map<String, String> filters) {
        return ResponseEntity.ok().body(taskService.getAll(filters));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> createTask(@PathVariable Long id) {
        Optional<Task> task = taskService.findById(id);
        return task.isPresent() ? ResponseEntity.ok(task.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody RegisterTaskDTO task) {
        taskService.createTask(task);
        return ResponseEntity.ok("Salvo com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask (@PathVariable Long id) {
        Optional<Task> task = taskService.findById(id);
        return task.isPresent() ? ResponseEntity.ok(taskService.delete(id)) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> putTask (@PathVariable Long id, @RequestBody PutTaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.update(id, taskDTO));
    }
}
