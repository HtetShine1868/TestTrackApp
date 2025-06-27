package com.test.TestTrack.Serivces;

import com.test.TestTrack.domain.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskId);

    Task createTask(UUID taskId, Task task);

    Optional<Task> getTaskById(UUID taskListId, UUID taskId);

    Task updateTask(UUID taskId, UUID taskListId, Task task);

    void deleteTask(UUID taskId, UUID taskListId);
}
