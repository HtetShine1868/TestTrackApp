package com.test.TestTrack.Serivces.impl;

import com.test.TestTrack.Repository.TaskListRepository;
import com.test.TestTrack.Repository.TaskRepository;
import com.test.TestTrack.Serivces.TaskService;
import com.test.TestTrack.domain.entity.Task;
import com.test.TestTrack.domain.entity.TaskList;
import com.test.TestTrack.domain.entity.TaskPriority;
import com.test.TestTrack.domain.entity.TaskStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
@Service
public class TaskServiceImpl implements TaskService {


    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskId) {
        return taskRepository.findByTaskListId(taskId);
    }

    @Override
    public Task createTask(UUID taskId, Task task) {
        if(null!=task.getId()){
            throw new IllegalArgumentException("Task already exists");
        }
        if(null==task.getTitle()){
            throw new IllegalArgumentException("Task title required");
        }
        TaskPriority taskPriority  =Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);
        TaskStatus status = TaskStatus.OPEN;

        TaskList tasklist =taskListRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task does not exist"));
        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                status,
                taskPriority,
                now,
                now


        );
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTaskById(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);

    }

    @Override
    public Task updateTask(UUID taskId, UUID taskListId, Task task) {
       if(null == task.getId()){
           throw new IllegalArgumentException("Task does not exist");
       }
        if(Objects.equals(taskId, task.getId())){
            throw new IllegalArgumentException("Task already exists");
        }
        if(null==task.getPriority()){
            throw new IllegalArgumentException("Task priority required");
        }
        if(null==task.getStatus()){
            throw new IllegalArgumentException("Task status required");
        }
        Task existingtask =taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task does not exist"));
        existingtask.setPriority(task.getPriority());
        existingtask.setStatus(task.getStatus());
        existingtask.setDescription(task.getDescription());
        existingtask.setDueDate(task.getDueDate());
        existingtask.setTitle(task.getTitle());
        existingtask.setUpdated(LocalDateTime.now());
        return taskRepository.save(existingtask);
    }
    @Transactional
    @Override
    public void deleteTask(UUID taskId, UUID taskListId) {
        taskRepository.deleteByTaskListIdAndId(taskListId,taskId);
    }


}
