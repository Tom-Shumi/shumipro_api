package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.TaskMapper
import com.ne.jp.shumipro_api.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TaskService {

    @Autowired
    lateinit var taskMapper: TaskMapper
    @Autowired
    lateinit var userMapper: UserMapper

    /**
     * タスク一覧取得
     */
    fun getTaskList(username: String, status: String?): List<TaskDto>? {
        val param = mapOf(
            "username" to username, "status" to status)
        val taskList: List<Task>? = taskMapper.getTaskByUsername(param)
        if (taskList is List<Task> && taskList.isNotEmpty()){
            return taskList.map{ it -> TaskDto().setTaskDto(it)}.toList()
        } else {
            return null
        }
    }

    /**
     * タスク登録
     */
    fun registerTask(taskDto: TaskDto): TaskDto? {
        val userCheck: User? = userMapper.getUser(taskDto.username!!)
        if (userCheck is User){
            val task = Task().setTask(taskDto)
            taskMapper.insertTask(task)
            taskDto.id = task.id
            return taskDto
        } else {
            return null
        }
    }

    /**
     * タスク更新
     */
    fun updateTask(taskDto: TaskDto): TaskDto? {
        val taskCheck = taskMapper.getTaskById(taskDto.id!!)
        return if (taskCheck is Task && taskCheck.username.equals(taskDto.username)) {
            val task = Task().setTask(taskDto)
            taskMapper.updateTask(task)
            taskDto
        } else {
            null
        }
    }

    /**
     * タスクステータス更新
     */
    fun updateStatusTask(taskDto: TaskDto): TaskDto? {
        val task = taskMapper.getTaskById(taskDto.id!!)
        return if (task is Task) {
            task.status = taskDto.status
            this.updateTask(TaskDto().setTaskDto(task))
        } else {
            null
        }
    }

    /**
     * タスク削除
     */
    fun deleteTask(taskDto: TaskDto): Int{
        val taskCheck = taskMapper.getTaskById(taskDto.id!!)
        return if (taskCheck is Task && taskCheck.username.equals(taskDto.username)) {
            taskMapper.deleteTask(taskDto.id!!)
        } else {
            0
        }
    }
}