package com.hadt.ehust.service

import com.hadt.ehust.entities.User
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.response.ProjectResponse
import com.hadt.ehust.response.ScheduleModel
import com.hadt.ehust.response.UserResponse
import com.hadt.ehust.security.JwtUtils
import com.hadt.ehust.security.UserDetailsImpl
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils
) {
    fun signIn(id: Int, password: String): Map<String,Any> {
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(id, password))
        return jwtUtils.generateAuthToken(authentication.principal as UserDetailsImpl)
    }

    fun findUserById(id: Int): ResponseEntity<UserResponse> {
        return userRepository.findById(id).map {
            ResponseEntity.ok(
                UserResponse(
                    id = it.id,
                    fullName = it.fullName,
                    instituteOfManagement = it.instituteOfManagement,
                    gender = it.gender,
                    grade = it.grade,
                    course = it.course,
                    email = it.email,
                    cadreStatus = it.cadreStatus ?: "",
                    unit = it.unit,
                    roleId = it.role.ordinal,
                    imageAvatar = it.imageAvatar,
                    imageBackground = it.imageBackground
                )
            )
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findUserByFullName(fullName: String): ResponseEntity<UserResponse> {
        return userRepository.findUserByFullName(fullName).map {
            ResponseEntity.ok(
                UserResponse(
                    id = it.id,
                    fullName = it.fullName,
                    instituteOfManagement = it.instituteOfManagement,
                    gender = it.gender,
                    grade = it.grade,
                    course = it.course,
                    email = it.email,
                    cadreStatus = it.cadreStatus ?: "",
                    unit = it.unit,
                    roleId = it.role.ordinal,
                    imageAvatar = it.imageAvatar,
                    imageBackground = it.imageBackground
                )
            )
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findProfileById(id: Int): ResponseEntity<UserResponse> {
        return userRepository.findById(id).map {
            ResponseEntity.ok(
                UserResponse(
                    id = it.id,
                    fullName = it.fullName,
                    instituteOfManagement = it.instituteOfManagement,
                    gender = it.gender,
                    grade = it.grade,
                    course = it.course,
                    email = it.email,
                    cadreStatus = it.cadreStatus ?: "",
                    unit = it.unit,
                    roleId = it.role.ordinal,
                    imageAvatar = it.imageAvatar,
                    imageBackground = it.imageBackground
                )
            )
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findALlStudentInClass(grade: String): ResponseEntity<List<UserResponse>> {
        val listUser = mutableListOf<UserResponse>()
        return userRepository.getListStudentInClass(grade).map { users ->
            users.forEach {
                listUser.add(
                    UserResponse(
                        id = it.id,
                        fullName = it.fullName,
                        instituteOfManagement = it.instituteOfManagement,
                        gender = it.gender,
                        grade = it.grade,
                        course = it.course,
                        email = it.email,
                        cadreStatus = it.cadreStatus ?: "",
                        unit = it.unit,
                        roleId = it.role.ordinal,
                        imageAvatar = it.imageAvatar,
                        imageBackground = it.imageBackground
                    )
                )
            }
            ResponseEntity.ok(listUser.toList())
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findAllProjectsByIdStudent(id: Int): ResponseEntity<List<ProjectResponse>> {
        val projects = mutableListOf<ProjectResponse>()
        return userRepository.findById(id).map { user ->
            user.likedClasses.toList().forEach {
                if (it.isProjectSubject) {
                    val item = ProjectResponse(
                        codeClass = it.codeClass,
                        codeCourse = it.codeCourse,
                        nameCourse = it.nameCourse,
                        semester = it.semester,
                        nameTeacher = it.nameTeacher ?: "",
                        studyForm = it.studyForm
                    )
                    projects.add(item)
                }
            }
            ResponseEntity.ok(projects.toList().sortedByDescending { it.semester })
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findByScheduleByIdStudent(id: Int): ResponseEntity<List<ScheduleModel>>{
        val classStudents = mutableListOf<ScheduleModel>()
       return userRepository.findById(id).map {user ->
            val likedClasses =  user.likedClasses.toList()
            val semesterCurrent= likedClasses.maxOf { it.semester }
            likedClasses.filter { !it.isProjectSubject && it.semester == semesterCurrent }.forEach {
                classStudents.add(
                    ScheduleModel(
                        it.startTime,
                        it.finishTime,
                        it.dateStudy,
                        it.dateFinishCourse,
                        it.dateStartCourse,
                        it.nameCourse
                    )
                )
            }
           ResponseEntity.ok(classStudents.toList())
        }.orElse(ResponseEntity.notFound().build())
    }
}