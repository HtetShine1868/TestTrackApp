package com.test.TestTrack.mappers;

import com.test.TestTrack.domain.dto.TaskDto;
import com.test.TestTrack.domain.entity.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);


    TaskDto toDto(Task task);
}
