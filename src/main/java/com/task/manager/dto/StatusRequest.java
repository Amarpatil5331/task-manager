package com.task.manager.dto;

import com.task.manager.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusRequest {
    private TaskStatus status;
}
