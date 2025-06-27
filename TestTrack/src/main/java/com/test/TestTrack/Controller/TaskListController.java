package com.test.TestTrack.Controller;


import com.test.TestTrack.Serivces.TaskListServices;
import com.test.TestTrack.domain.dto.TaskListDto;
import com.test.TestTrack.domain.entity.TaskList;
import com.test.TestTrack.mappers.TaskListMapper;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {


    private final TaskListServices taskListServices;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListServices taskListServices, TaskListMapper taskListMapper) {
        this.taskListServices = taskListServices;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping()
    public List<TaskListDto> getTaskLists() {
           return taskListServices.listTaskLists()
                    .stream()
            .map(taskListMapper::toDto)
                    .toList();

    }
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList= taskListServices.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }
    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable("task_list_id")UUID id) {

        return taskListServices.getTaskListById(id)
                .map(taskListMapper::toDto);
    }

}
