package com.hadt.ehust.service

import com.hadt.ehust.entities.News
import com.hadt.ehust.entities.Task
import com.hadt.ehust.entities.Topic
import com.hadt.ehust.model.Role
import com.hadt.ehust.model.StatusNotification
import com.hadt.ehust.model.StatusTask
import com.hadt.ehust.model.TypeNotification
import com.hadt.ehust.repository.NewsRepository
import com.hadt.ehust.repository.TaskRepository
import com.hadt.ehust.repository.TopicRepository
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.utils.Utils
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class NewsService(
    private val newsRepository: NewsRepository,
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository,
    private val topicRepository: TopicRepository
) {
    fun findAllNews(type: TypeNotification): List<News> {
        val news =
             when (type == TypeNotification.TYPE_PROJECT) {
                true -> {
                    var idTeacherTopic: Int ?= null
                   val list = mutableListOf<News>()
                    newsRepository.findByType(type).filter { it.idUserPost != Utils.getCurrentUserId() }.let {
                        it.forEach {
                            taskRepository.findById(it.idTask?:0).map { idTeacherTopic = it.topics?.idTeacher }
                            if (idTeacherTopic == Utils.getCurrentUserId())
                                list.add(it)
                        }
                    }
                    return list

                }
                false -> {
                   return newsRepository.findByType(type)
                }
            }
    }

    fun updateStatusNews(id: Int, status: StatusNotification, type: TypeNotification): ResponseEntity<List<News>> {
        newsRepository.findById(id).map {
            val news = it.copy(status = status)
            newsRepository.save(news)
        }
        return ResponseEntity.ok().body(newsRepository.findByType(type))
    }

    fun postNotificationNewTask(news: News): ResponseEntity<HttpStatus> {
        newsRepository.save(news)
        return ResponseEntity.ok().body(HttpStatus.OK)
    }

    fun notificationUpdateTask(task: Task): ResponseEntity<HttpStatus> {
        var oldTask: Task? = null
        taskRepository.findById(task.id ?: 0).map { oldTask = it }
        var information: News? = null
        var title = "task ${task.title} được ${task.assignee} cập nhật"
        if (task.status != oldTask?.status) {
            val status = when (task.status) {
                StatusTask.IN_PROGRESS -> "đang thực hiện"
                StatusTask.CANCEL -> "huỷ task"
                StatusTask.NEW -> "tạo mới task"
                StatusTask.FINISHED -> "hoàn thành task"
                else -> {}
            }
            title = "${title} trạng thái: ${status},"
        }
        if (task.description != oldTask?.description) {
            title = " ${title} mô tả thành ${task.description}, "
        }
        if (task.estimateTime != oldTask?.estimateTime) {
            title = "${title} thời gian ước lượng hoàn thành task: ${task.estimateTime}, "
        }
        if (task.spendTime != oldTask?.spendTime) {
            title = "${title} thời gian thực tế hoàn thành task: ${task.spendTime} "
        }
        if (task.startDate != oldTask?.startDate || task.dueDate != oldTask?.dueDate) {
            title =
                "${title} thời gian bắt đầu thực hiện, hoàn thành task: ${task.startDate} - ${task.dueDate}, "
        }
        if (task.progress != oldTask?.progress) {
            title = "${title} tiến độ: ${task.progress?.percent},"
        }
        if (!task.attachments.equals(oldTask?.attachments)) {
            title = "${title} thêm bình luận kèm theo file,"
        }
        if (task.commentsTask?.equals(oldTask?.commentsTask) == false) {
            title = "${title} thêm bình luận"
        }
        val dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val datePost = dtf.format(LocalDate.now())
        val idUserPost = Utils.getCurrentUserId()
        information = News(
            title = "${title} task ${task.title}",
            content = "",
            datePost = datePost,
            type = TypeNotification.TYPE_PROJECT,
            status = StatusNotification.STATUS_UNREAD,
            idUserPost = idUserPost,
            idTask = task.id
        )
        newsRepository.save(information!!)
        return ResponseEntity.ok().body(HttpStatus.OK)
    }

    fun clearNotificationRead(newsReads: List<News>): ResponseEntity<HttpStatus> {

        newsRepository.deleteAll(newsReads)
        return ResponseEntity.ok().body(HttpStatus.OK)
    }


}
private val Float.percent: String
    get() = "${(this * 100).toInt()}"