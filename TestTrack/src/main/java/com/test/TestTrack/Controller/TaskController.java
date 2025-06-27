package com.test.TestTrack.Controller;

import com.test.TestTrack.Serivces.TaskService;
import com.test.TestTrack.domain.dto.TaskDto;
import com.test.TestTrack.domain.entity.Task;
import com.test.TestTrack.mappers.TaskMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;


    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }
    @GetMapping
    public List<TaskDto> getTaskList(@PathVariable("task_list_id") UUID task_list_id) {

        return taskService.listTasks(task_list_id)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
    @PostMapping
    public TaskDto createTask(
            @PathVariable("task_list_id") UUID task_list_id
            ,@RequestBody TaskDto taskDto) {
        Task createdTask = taskService.createTask(
                task_list_id,
                taskMapper.fromDto(taskDto)
        ) ;
        return taskMapper.toDto(createdTask);
    }
    @GetMapping(path="/{task_id}")
    public TaskDto getTask(
            @PathVariable("task_list_id") UUID task_list_id,
            @PathVariable("task_id")UUID task_id) {
        return taskService.getTaskById(task_id, task_list_id).map(taskMapper::toDto).orElse(null);
    }

    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(
            @PathVariable("task_list_id") UUID taskListid,
            @PathVariable("task_id")UUID taskid,
            @RequestBody TaskDto taskDto
    ){
    Task updatedtask =taskService.updateTask(
            taskListid,
            taskid,
            taskMapper.fromDto(taskDto)

    );
    return taskMapper.toDto(updatedtask);
    }


    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(
            @PathVariable("task_list_id") UUID taskListid,
            @PathVariable("task_id")UUID taskid
    ){
        taskService.deleteTask(taskListid, taskid);
    }
}
