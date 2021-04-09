package com.ne.jp.shumipro_api.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.ne.jp.shumipro_api.dto.TaskDto
import java.text.SimpleDateFormat
import java.util.*

data class TaskResponse(
    var id: Int? = 0
    , var username: String? = ""
    , var task: String? = ""
    , var priority: Int? = 0
    , var status: Int? = 0
    , var description: String? = ""
    , var planDate: String? = ""
    , var doneDate: String? = ""
) {
    fun setTaskResponse(taskDto: TaskDto): TaskResponse{
        val df = SimpleDateFormat("yyyy/MM/dd")
        this.id = taskDto.id
        this.username = taskDto.username
        this.task = taskDto.task
        this.priority = taskDto.priority
        this.status = taskDto.status
        this.description = taskDto.description
        this.planDate = if (Objects.nonNull(taskDto.planDate)) df.format(taskDto.planDate) else ""
        this.doneDate = if (Objects.nonNull(taskDto.doneDate)) df.format(taskDto.doneDate) else ""
        return this
    }
}