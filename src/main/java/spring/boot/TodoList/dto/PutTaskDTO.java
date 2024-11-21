package spring.boot.TodoList.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PutTaskDTO {
    private String description;
    private Boolean completed;
}
