package com.test.TestTrack.domain.dto;

import com.test.TestTrack.domain.entity.TaskPriority;
import com.test.TestTrack.domain.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status


) {
}
