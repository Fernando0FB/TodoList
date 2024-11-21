package spring.boot.TodoList.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.TodoList.dto.PutTaskDTO;
import spring.boot.TodoList.dto.RegisterTaskDTO;
import spring.boot.TodoList.models.Task;
import spring.boot.TodoList.repositories.Specifications.TaskSpecification;
import spring.boot.TodoList.repositories.TaskRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(RegisterTaskDTO task) {
        Task newTask = new Task();
        newTask.setCompleted(task.getCompleted());
        newTask.setDescription(task.getDescription());
        newTask.setTitle(task.getTitle());
        taskRepository.save(newTask);
        return newTask;
    }

    public List<Task> getAll(Map<String, String> filters) {
        return taskRepository.findAll(TaskSpecification.columnEqual(filters));
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public String delete(Long id) {
        taskRepository.deleteById(id);
        return "Task with id " + id + " sucessfully deleted!";
    }

    public Task update(Long id, PutTaskDTO task) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(taskOptional.isPresent()) {
            Task newTask = taskOptional.get();
            newTask.setCompleted(task.getCompleted());
            newTask.setDescription(task.getDescription());
            taskRepository.save(newTask);
            return newTask;
        }
        return null;
    }
}
