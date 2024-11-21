package spring.boot.TodoList.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterTaskDTO {
    private String title;
    private String description;
    private Boolean completed;
}
