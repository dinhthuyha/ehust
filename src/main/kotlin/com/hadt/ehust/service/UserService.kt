package com.hadt.ehust.service

import com.hadt.ehust.entities.ClassStudent
import com.hadt.ehust.entities.User
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.security.JwtUtils
import com.hadt.ehust.security.UserDetailsImpl
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import javax.management.relation.Role

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

    fun findUserByIdAndRole(id: Int, roleId: com.hadt.ehust.model.Role): ResponseEntity<User> {
        return userRepository.findById(id).map {
            if (it.role == roleId){
                ResponseEntity.ok(
                    User(
                        id = it.id,
                        fullName = it.fullName,
                        instituteOfManagement = it.instituteOfManagement,
                        gender = it.gender,
                        grade = it.grade,
                        course = it.course,
                        email = it.email,
                        cadreStatus = it.cadreStatus ?: "",
                        unit = it.unit,
                        role = it.role,
                        imageAvatar = it.imageAvatar,
                        imageBackground = it.imageBackground
                    )
                )
            }else {
                ResponseEntity.notFound().build()
            }
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findUserByFullName(fullName: String): ResponseEntity<User> {
        return userRepository.findUserByFullName(fullName).map {
            ResponseEntity.ok(
                User(
                    id = it.id,
                    fullName = it.fullName,
                    instituteOfManagement = it.instituteOfManagement,
                    gender = it.gender,
                    grade = it.grade,
                    course = it.course,
                    email = it.email,
                    cadreStatus = it.cadreStatus ?: "",
                    unit = it.unit,
                    role = it.role,
                    imageAvatar = it.imageAvatar,
                    imageBackground = it.imageBackground
                )
            )
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findProfileById(id: Int): ResponseEntity<User> {
        return userRepository.findById(id).map {
            ResponseEntity.ok(
                User(
                    id = it.id,
                    fullName = it.fullName,
                    instituteOfManagement = it.instituteOfManagement,
                    gender = it.gender,
                    grade = it.grade,
                    course = it.course,
                    email = it.email,
                    cadreStatus = it.cadreStatus ?: "",
                    unit = it.unit,
                    role = it.role,
                    imageAvatar = it.imageAvatar,
                    imageBackground = it.imageBackground
                )
            )
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findALlStudentInClass(grade: String): ResponseEntity<List<User>> {
        val listUser = mutableListOf<User>()
        return userRepository.getListStudentInClass(grade).map { users ->
            users.forEach {
                listUser.add(
                    User(
                        id = it.id,
                        fullName = it.fullName,
                        instituteOfManagement = it.instituteOfManagement,
                        gender = it.gender,
                        grade = it.grade,
                        course = it.course,
                        email = it.email,
                        cadreStatus = it.cadreStatus ?: "",
                        unit = it.unit,
                        role = it.role,
                        imageAvatar = it.imageAvatar,
                        imageBackground = it.imageBackground
                    )
                )
            }
            ResponseEntity.ok(listUser.toList())
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findAllProjectsByIdStudent(id: Int): ResponseEntity<List<ClassStudent>> {
        val projects = mutableListOf<ClassStudent>()
        return userRepository.findById(id).map { user ->
            user.likedClasses?.toList()?.forEach {
                if (it.isProjectSubject==true) {
                    val item = ClassStudent(
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

    fun findByScheduleByIdStudent(id: Int): ResponseEntity<List<ClassStudent>>{
        val classStudents = mutableListOf<ClassStudent>()
       return userRepository.findById(id).map {user ->
            val likedClasses =  user.likedClasses?.toList()
            val semesterCurrent= likedClasses?.maxOf { it.semester!! }
            likedClasses?.filter { !it.isProjectSubject!! && it.semester == semesterCurrent }?.forEach {
                classStudents.add(
                    ClassStudent(
                        codeClass =it.codeClass,
                        startTime = it.startTime,
                        finishTime = it.finishTime,
                        dateStudy = it.dateStudy,
                        semester = it.semester,
                        dateFinishCourse = it.dateFinishCourse,
                        dateStartCourse = it.dateStartCourse,
                        nameCourse = it.nameCourse
                    )
                )
            }
           ResponseEntity.ok(classStudents.toList())
        }.orElse(ResponseEntity.notFound().build())
    }
}