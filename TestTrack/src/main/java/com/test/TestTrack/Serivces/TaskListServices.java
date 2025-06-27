package com.test.TestTrack.Serivces;

import com.test.TestTrack.domain.entity.TaskList;
import java.util.Optional;

import java.util.List;
import java.util.UUID;

public interface TaskListServices {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskListById(UUID id);
    TaskList updateTaskList(TaskList taskList,UUID id);
    void deleteTaskListById(UUID id);
}
