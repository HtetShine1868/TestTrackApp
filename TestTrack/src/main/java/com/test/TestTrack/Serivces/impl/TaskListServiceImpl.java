package com.test.TestTrack.Serivces.impl;

import com.test.TestTrack.Repository.TaskListRepository;
import com.test.TestTrack.Serivces.TaskListServices;
import com.test.TestTrack.domain.entity.TaskList;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListServices {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null!=taskList.getId()){
            throw new IllegalArgumentException("tasklist already has an id ");

        }
        if(null==taskList.getTitle() || taskList.getTitle().isEmpty()){
            throw new IllegalArgumentException("tasklist title is null or empty");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        return taskListRepository.findById(id);
    }
}
