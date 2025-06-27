package com.test.TestTrack.mappers;

import com.test.TestTrack.domain.dto.TaskListDto;
import com.test.TestTrack.domain.entity.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
