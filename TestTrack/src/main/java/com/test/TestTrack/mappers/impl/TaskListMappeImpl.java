package com.test.TestTrack.mappers.impl;

import com.test.TestTrack.domain.dto.TaskListDto;
import com.test.TestTrack.domain.entity.Task;
import com.test.TestTrack.domain.entity.TaskList;
import com.test.TestTrack.domain.entity.TaskStatus;
import com.test.TestTrack.mappers.TaskListMapper;
import com.test.TestTrack.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Component
public class TaskListMappeImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMappeImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                 null

        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList()
                        ).orElse(null)


        );
    }
    private Double calculateTaskListProgress(List<Task>tasks) {

        if(null == tasks || tasks.isEmpty()){
            return null;
        }
        long closedTaskCount = tasks.stream().filter(task->
                TaskStatus.CLOSED ==task.getStatus()).count();

        return (double)closedTaskCount/tasks.size();
    }
}
